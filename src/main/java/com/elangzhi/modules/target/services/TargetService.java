package com.elangzhi.modules.target.services;

import com.elangzhi.modules.target.dao.TargetDao;
import com.elangzhi.ssm.model.Target;
import com.elangzhi.ssm.services.BaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* 挑战 Service
* @author GaoXiang
* @version 1.0
*/
@Service
public class TargetService extends BaseService<Target> {

    @Resource
    private TargetDao targetDao;


    /**
    * 保存数据
    * @param target 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(Target target) throws Exception {
        return targetDao.insert(target);
    }

    /**
    * 根据 id 修改
    * @param target 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(Target target) throws Exception {
        return targetDao.updateById(target);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return targetDao.deleteById(id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public Target selectById(Long id) {
        try {
            return targetDao.selectById(id);
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
        return targetDao.deleteByIds(ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Target> list(Map map) throws Exception {
        return targetDao.list(map);
    }

    /**
    * 查询列表 带分页
    * @param map 参数
    * @param page 页码
    * @param size 每页大小
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Target> list(Map map,int page,int size) throws Exception {
        return targetDao.list(map,page,size);
    }



    public PageInfo<Target> listBySupervise(Map<String,String> param, Integer page, Integer size) {
        try {
            return targetDao.listBySupervise(param,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public PageInfo<Target> listByTarget(Target target, Integer page, Integer size) {
        try {
            return targetDao.listByTarget(target,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取达到挑战天数的挑战
     * @return
     */
    public List<Target> listFinishKeep() {
        try {
            return targetDao.listFinishKeep();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
