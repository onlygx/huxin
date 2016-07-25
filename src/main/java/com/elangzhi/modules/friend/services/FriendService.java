package com.elangzhi.modules.friend.services;

import com.elangzhi.modules.friend.dao.FriendDao;
import com.elangzhi.ssm.model.Friend;
import com.elangzhi.ssm.services.BaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* 用户好友 Service
* @author GaoXiang
* @version 1.0
*/
@Service
public class FriendService extends BaseService<Friend> {

    @Resource
    private FriendDao friendDao;


    /**
    * 保存数据
    * @param friend 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(Friend friend) throws Exception {
        return friendDao.insert(friend);
    }

    /**
    * 根据 id 修改
    * @param friend 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(Friend friend) throws Exception {
        return friendDao.updateById(friend);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return friendDao.deleteById(id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public Friend selectById(Long id) {
        try {
            return friendDao.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return friendDao.deleteByIds(ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Friend> list(Map map) throws Exception {
        return friendDao.list(map);
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
        return friendDao.list(map,page,size);
    }


    public List<Friend> listByUserId(Long userId) {
        try {
            return friendDao.listByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
