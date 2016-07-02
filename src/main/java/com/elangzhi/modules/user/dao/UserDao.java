package com.elangzhi.modules.user.dao;

import com.elangzhi.ssm.model.User;
import com.elangzhi.ssm.dao.LzDao;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
* 用户 Dao
* @author GaoXiang
* @version 1.0
*/
@Repository
public class UserDao extends LzDao<User> {


    /**
     * 按条件获取用户
     * @param user 用户信息
     * @return 用户
     * @throws Exception 获取用户失败
     */
    public List<User> selectByUser(User user) throws Exception {
        return arrayList("UserMapper.selectByUser",user);
    }

    /**
    * 保存数据
    * @param user 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(User user) throws Exception {
        return insert("UserMapper.insert",user);
    }

    /**
    * 根据 id 修改
    * @param user 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(User user) throws Exception {
        return update("UserMapper.updateById",user);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return delete("UserMapper.deleteById",id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public User selectById(Long id) throws Exception {
        return selectOne("UserMapper.selectById",id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return delete("UserMapper.deleteByIds",ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<User> list(Map map) throws Exception {
        return list("UserMapper.list",map);
    }

    /**
    * 查询列表 带分页
    * @param map 参数
    * @param page 页码
    * @param size 每页大小
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<User> list(Map map,int page,int size) throws Exception {
        return list("UserMapper.list",map,new RowBounds(page,size));
    }

}
