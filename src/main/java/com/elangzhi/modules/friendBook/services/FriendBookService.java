package com.elangzhi.modules.friendBook.services;

import com.elangzhi.modules.friendBook.dao.FriendBookDao;
import com.elangzhi.ssm.model.FriendBook;
import com.elangzhi.ssm.services.BaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* 用户通讯录 Service
* @author GaoXiang
* @version 1.0
*/
@Service
public class FriendBookService extends BaseService<FriendBook> {

    @Resource
    private FriendBookDao friendBookDao;


    /**
    * 保存数据
    * @param friendBook 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(FriendBook friendBook) throws Exception {
        return friendBookDao.insert(friendBook);
    }

    /**
    * 根据 id 修改
    * @param friendBook 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(FriendBook friendBook) throws Exception {
        return friendBookDao.updateById(friendBook);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return friendBookDao.deleteById(id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public FriendBook selectById(Long id) throws Exception {
        return friendBookDao.selectById(id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return friendBookDao.deleteByIds(ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<FriendBook> list(Map map) throws Exception {
        return friendBookDao.list(map);
    }

    /**
    * 查询列表 带分页
    * @param map 参数
    * @param page 页码
    * @param size 每页大小
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<FriendBook> list(Map map,int page,int size) throws Exception {
        return friendBookDao.list(map,page,size);
    }


    public List<FriendBook> listByUserId(Long userId) {
        try {
            return friendBookDao.listByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
