package com.elangzhi.ssm.services;

import com.elangzhi.ssm.dao.LzDao;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 基础数据访问服务封装
 * @author GaoXiang
 * @version 2.0
 */
@Service
public class BaseService<T> {

    @Resource
    public LzDao<T> lzDao;


    /**
     * 保存数据
     * @param obj 实体对象
     * @return 实体id
     * @throws Exception 数据保存异常
     */
    public Integer insert(T obj) throws Exception {
        String statement = obj.getClass().getSimpleName()+"Mapper.insert";
        return lzDao.insert(statement,obj);
    }

    /**
     * 根据 id 修改
     * @param obj 带id的实体对象
     * @return 受影响的行数
     * @throws Exception 数据修改异常
     */
    public Integer updateById(T obj) throws Exception {
        String statement = obj.getClass().getSimpleName()+"Mapper.updateById";
        return lzDao.update(statement,obj);
    }

    /**
     * 根据 id 删除
     * @param id 数据id
     * @return 受影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteById(Long id,Class clazz) throws Exception {
        String statement = clazz.getSimpleName()+"Mapper.deleteById";
        return lzDao.delete(statement,id);
    }

    /**
     * 根据 id 查找
     * @param id 实体id
     * @return 实体
     * @throws Exception 查询异常
     */
    public T selectById(Long id,Class clazz) throws Exception {
        String statement = clazz.getSimpleName()+"Mapper.selectById";
        return lzDao.selectOne(statement,id);
    }

    /**
     * 根据 id 批量删除
     * @param ids 要删除的id
     * @return 影响的行数
     * @throws Exception 数据删除异常
     */
    public Integer deleteByIds(List<Long> ids,Class clazz) throws Exception {
        String statement = clazz.getSimpleName()+"Mapper.deleteByIds";
        return lzDao.delete(statement,ids);
    }

    /**
     * 查询列表
     * @param map 参数
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<T> list(Map map,Class clazz) throws Exception {
        String statement = clazz.getSimpleName()+"Mapper.list";
        return lzDao.list(statement,map);
    }

    /**
     * 查询列表 带分页
     * @param map 参数
     * @param page 页码
     * @param size 每页大小
     * @return 列表
     * @throws Exception 数据返回异常
     */
    public PageInfo<T> list(Map map,int page,int size,Class clazz) throws Exception {
        String statement = clazz.getSimpleName()+"Mapper.list";
        return lzDao.list(statement,map,new RowBounds(page,size));
    }

}
