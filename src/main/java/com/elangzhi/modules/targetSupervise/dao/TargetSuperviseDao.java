package com.elangzhi.modules.targetSupervise.dao;

import com.elangzhi.ssm.model.TargetSupervise;
import com.elangzhi.ssm.dao.LzDao;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
* 挑战监督员 Dao
* @author GaoXiang
* @version 1.0
*/
@Repository
public class TargetSuperviseDao extends LzDao<TargetSupervise> {


    /**
     * 获取监督员
     * @param targetId 挑战id
     * @return 监督员列表
     * @throws Exception 获取监督员列表异常
     */
    public List<TargetSupervise> listByTargetId(Long targetId) throws Exception {
        return arrayList("TargetSuperviseMapper.listByTargetId",targetId);
    }

    /**
    * 保存数据
    * @param targetSupervise 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(TargetSupervise targetSupervise) throws Exception {
        return insert("TargetSuperviseMapper.insert",targetSupervise);
    }

    /**
    * 根据 id 修改
    * @param targetSupervise 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(TargetSupervise targetSupervise) throws Exception {
        return update("TargetSuperviseMapper.updateById",targetSupervise);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return delete("TargetSuperviseMapper.deleteById",id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public TargetSupervise selectById(Long id) throws Exception {
        return selectOne("TargetSuperviseMapper.selectById",id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return delete("TargetSuperviseMapper.deleteByIds",ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<TargetSupervise> list(Map map) throws Exception {
        return list("TargetSuperviseMapper.list",map);
    }

    /**
    * 查询列表 带分页
    * @param map 参数
    * @param page 页码
    * @param size 每页大小
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<TargetSupervise> list(Map map,int page,int size) throws Exception {
        return list("TargetSuperviseMapper.list",map,new RowBounds(page,size));
    }

}
