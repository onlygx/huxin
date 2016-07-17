package com.elangzhi.modules.target.dao;

import com.elangzhi.ssm.model.Target;
import com.elangzhi.ssm.dao.LzDao;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
* 挑战 Dao
* @author GaoXiang
* @version 1.0
*/
@Repository
public class TargetDao extends LzDao<Target> {



    /**
    * 保存数据
    * @param target 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(Target target) throws Exception {
        return insert("TargetMapper.insert",target);
    }

    /**
    * 根据 id 修改
    * @param target 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(Target target) throws Exception {
        return update("TargetMapper.updateById",target);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return delete("TargetMapper.deleteById",id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public Target selectById(Long id) throws Exception {
        return selectOne("TargetMapper.selectById",id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return delete("TargetMapper.deleteByIds",ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Target> list(Map map) throws Exception {
        return list("TargetMapper.list",map);
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
        return list("TargetMapper.list",map,new RowBounds(page,size));
    }


    public PageInfo<Target> listBySupervise(Map<String,String> param, Integer page, Integer size) throws Exception{
        return list("TargetMapper.listBySupervise",param,new RowBounds(page,size));
    }


    public PageInfo<Target> listByTarget(Target target, Integer page, Integer size) throws Exception {
        return list("TargetMapper.listByTarget",target,new RowBounds(page,size));
    }

    public List<Target> listFinishKeep() throws Exception {
        return arrayList("TargetMapper.listFinishKeep");
    }
}
