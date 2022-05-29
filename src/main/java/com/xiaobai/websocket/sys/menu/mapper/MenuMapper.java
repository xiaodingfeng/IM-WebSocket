package com.xiaobai.websocket.sys.menu.mapper;

import com.xiaobai.websocket.core.service.BaseDao;
import com.xiaobai.websocket.sys.menu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseDao<Menu> {
    @Select("select m.*,c.name cName from `MENU` m left join `CATEGORY` c on m.cid = c.id where m.status = 1 and  m.display = 1" +
            " and c.status = 1")
    List<Menu> list();
}
