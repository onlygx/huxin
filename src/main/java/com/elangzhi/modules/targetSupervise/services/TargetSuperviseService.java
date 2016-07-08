package com.elangzhi.modules.targetSupervise.services;

import com.elangzhi.modules.targetSupervise.dao.TargetSuperviseDao;
import com.elangzhi.ssm.model.TargetSupervise;
import com.elangzhi.ssm.services.BaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* 挑战监督员 Service
* @author GaoXiang
* @version 1.0
*/
@Service
public class TargetSuperviseService extends BaseService<TargetSupervise> {

    @Resource
    private TargetSuperviseDao targetSuperviseDao;


    /**
     * 获取监督员
     * @param targetId 挑战id
     * @return 监督员列表
     */
    public List<TargetSupervise> listByTargetId(Long targetId)  {
        try {
            return targetSuperviseDao.listByTargetId(targetId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
    * 保存数据
    * @param targetSupervise 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(TargetSupervise targetSupervise) throws Exception {
        return targetSuperviseDao.insert(targetSupervise);
    }

    /**
    * 根据 id 修改
    * @param targetSupervise 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(TargetSupervise targetSupervise) throws Exception {
        return targetSuperviseDao.updateById(targetSupervise);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return targetSuperviseDao.deleteById(id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public TargetSupervise selectById(Long id) throws Exception {
        return targetSuperviseDao.selectById(id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return targetSuperviseDao.deleteByIds(ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<TargetSupervise> list(Map map) throws Exception {
        return targetSuperviseDao.list(map);
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
        return targetSuperviseDao.list(map,page,size);
    }


}
