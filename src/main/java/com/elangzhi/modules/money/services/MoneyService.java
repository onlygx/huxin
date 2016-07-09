package com.elangzhi.modules.money.services;

import com.elangzhi.modules.money.dao.MoneyDao;
import com.elangzhi.ssm.model.Money;
import com.elangzhi.ssm.model.Target;
import com.elangzhi.ssm.services.BaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* 钱包 Service
* @author GaoXiang
* @version 1.0
*/
@Service
public class MoneyService extends BaseService<Money> {

    @Resource
    private MoneyDao moneyDao;


    /**
    * 保存数据
    * @param money 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(Money money) throws Exception {
        return moneyDao.insert(money);
    }

    /**
    * 根据 id 修改
    * @param money 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(Money money) throws Exception {
        return moneyDao.updateById(money);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return moneyDao.deleteById(id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public Money selectById(Long id) throws Exception {
        return moneyDao.selectById(id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return moneyDao.deleteByIds(ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Money> list(Map map) throws Exception {
        return moneyDao.list(map);
    }

    /**
    * 查询列表 带分页
    * @param map 参数
    * @param page 页码
    * @param size 每页大小
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Money> list(Map map,int page,int size) throws Exception {
        return moneyDao.list(map,page,size);
    }


    public PageInfo<Money> listByUserId(Long id, Integer page, Integer size) {
        try {
            return moneyDao.listByUserId(id,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
