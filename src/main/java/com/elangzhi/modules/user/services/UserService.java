package com.elangzhi.modules.user.services;

import com.elangzhi.modules.user.dao.UserDao;
import com.elangzhi.ssm.model.User;
import com.elangzhi.ssm.services.BaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* 用户 Service
* @author GaoXiang
* @version 1.0
*/
@Service
public class UserService extends BaseService<User> {

    @Resource
    private UserDao userDao;

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 用户
     */
    public User selectByUserName(String userName){
        User user = new User();
        user.setUserName(userName);
        return selectByUser(user);
    }

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return 用户
     */
    public User selectByPhone(String phone){
        User user = new User();
        user.setPhone(phone);
        return selectByUser(user);
    }

    /**
     * user信息获取user
     * @param user user参数
     * @return User对象
     */
    private User selectByUser(User user){
        try {
            List<User> userList = userDao.selectByUser(user);
            if(userList.size() > 0){
                return userList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * 保存数据
    * @param user 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(User user) throws Exception {
        return userDao.insert(user);
    }

    /**
    * 根据 id 修改
    * @param user 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(User user) throws Exception {
        return userDao.updateById(user);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return userDao.deleteById(id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public User selectById(Long id) throws Exception {
        return userDao.selectById(id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return userDao.deleteByIds(ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<User> list(Map map) throws Exception {
        return userDao.list(map);
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
        return userDao.list(map,page,size);
    }


}
