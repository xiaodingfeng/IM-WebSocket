package com.xiaobai.websocket.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaobai.websocket.sdk.exception.BusinessRuntimeException;
import com.xiaobai.websocket.sys.user.entity.User;
import com.xiaobai.websocket.sys.user.service.UserService;
import com.xiaobai.websocket.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket的处理类。
 * 作用相当于HTTP请求
 * 中的controller
 */
@Component
@Slf4j
@ServerEndpoint("/api/pushMessage/{token}")
public class WebSocketServer {

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。*/
    private static final ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, List<String>> contentMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId = "";

    private static ApplicationContext applicationContext;


    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }

    private UserService userService;

    /**
     * 连接建立成
     * 功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("token") String token) throws IOException {
        //this.userService = applicationContext.getBean(UserService.class);
        this.session = session;
        User user = null;
        String errorMsg = "";
        try {
            user = JwtUtils.checkToken(token);
        } catch (BusinessRuntimeException e) {
            errorMsg = e.getErrorMsg();
        } catch (Exception e) {
            errorMsg = e.getMessage();
        }
        if (user == null) {
            sendMessage(errorMsg);
            this.session.close();
            return;
        }
        this.userId = user.getUserName();
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //加入set中
            webSocketMap.put(userId,this);
        }else{
            //加入set中
            webSocketMap.put(userId,this);
            //在线数加1
            addOnlineCount();
        }

        log.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());
        sendMessage("连接成功");
        // 发送残留消息
        if (contentMap.containsKey(userId)) {
            for (String msg : contentMap.get(userId)) {
                sendMessage(msg);
            }
            contentMap.remove(userId);
        }

    }

    /**
     * 连接关闭
     * 调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消
     * 息后调用的方法
     * @param message
     * 客户端发送过来的消息
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:"+userId+",报文:"+message);
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.hasText(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId", this.userId);
                String toUserId=jsonObject.getString("toUserId");

                jsonObject.remove("toUserId");
                //传送给对应toUserId用户的websocket
                if(StringUtils.hasText(toUserId) && webSocketMap.containsKey(toUserId)){
                    webSocketMap.get(toUserId).sendMessage(JSON.toJSONString(jsonObject));
                }else{
                    //否则不在这个服务器上，发送到mysql或者redis
                    log.error("请求的userId:"+toUserId+"不在该服务器上");
                    List<String> list = contentMap.get(toUserId);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(JSON.toJSONString(jsonObject));
                    contentMap.put(toUserId, list);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

        log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务
     * 器主动推送
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *发送自定
     *义消息
     **/
    public static void sendInfo(String message, String userId) {
        log.info("发送消息到:"+userId+"，报文:"+message);
        if(StringUtils.hasText(userId) && webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            log.error("用户"+userId+",不在线！");
        }
    }

    /**
     * 获得此时的
     * 在线人数
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 在线人
     * 数加1
     */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    /**
     * 在线人
     * 数减1
     */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
