package com.elangzhi.modules.friend.dao;

import com.elangzhi.ssm.model.Friend;
import com.elangzhi.ssm.dao.LzDao;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
* 用户好友 Dao
* @author GaoXiang
* @version 1.0
*/
@Repository
public class FriendDao extends LzDao<Friend> {



    /**
    * 保存数据
    * @param friend 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(Friend friend) throws Exception {
        return insert("FriendMapper.insert",friend);
    }

    /**
    * 根据 id 修改
    * @param friend 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(Friend friend) throws Exception {
        return update("FriendMapper.updateById",friend);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return delete("FriendMapper.deleteById",id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public Friend selectById(Long id) throws Exception {
        return selectOne("FriendMapper.selectById",id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return delete("FriendMapper.deleteByIds",ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Friend> list(Map map) throws Exception {
        return list("FriendMapper.list",map);
    }

    /**
    * 查询列表 带分页
    * @param map 参数
    * @param page 页码
    * @param size 每页大小
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Friend> list(Map map,int page,int size) throws Exception {
        return list("FriendMapper.list",map,new RowBounds(page,size));
    }

    public List<Friend> listByUserId(Long userId) throws Exception {
        return arrayList("FriendMapper.listByUserId",userId);
    }

    public List<Friend> listByApplyUserId(Long userId) throws Exception {
        return arrayList("FriendMapper.listByApplyUserId",userId);
    }
}
