package com.elangzhi.modules.record.services;

import com.elangzhi.modules.record.dao.RecordDao;
import com.elangzhi.ssm.model.Record;
import com.elangzhi.ssm.services.BaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* 时间轴 Service
* @author GaoXiang
* @version 1.0
*/
@Service
public class RecordService extends BaseService<Record> {

    @Resource
    private RecordDao recordDao;


    /**
    * 保存数据
    * @param record 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(Record record) throws Exception {
        return recordDao.insert(record);
    }

    /**
    * 根据 id 修改
    * @param record 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(Record record) throws Exception {
        return recordDao.updateById(record);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return recordDao.deleteById(id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public Record selectById(Long id) throws Exception {
        return recordDao.selectById(id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return recordDao.deleteByIds(ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Record> list(Map map) throws Exception {
        return recordDao.list(map);
    }

    /**
    * 查询列表 带分页
    * @param map 参数
    * @param page 页码
    * @param size 每页大小
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Record> list(Map map,int page,int size) throws Exception {
        return recordDao.list(map,page,size);
    }


    public PageInfo<Record> listByUserId(Long userId, Integer page, Integer size) {
        try {
            return recordDao.listByUserId(userId,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PageInfo<Record> listByTargetId(Long targetId, Integer page, Integer size) {
        try {
            return recordDao.listByTargetId(targetId,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
