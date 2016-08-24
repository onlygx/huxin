package com.elangzhi.modules.money.dao;

import com.elangzhi.ssm.model.Money;
import com.elangzhi.ssm.dao.LzDao;
import com.elangzhi.ssm.model.Target;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
* 钱包 Dao
* @author GaoXiang
* @version 1.0
*/
@Repository
public class MoneyDao extends LzDao<Money> {

    /**
     * 按条件获取
     * @param money {userId,status,type}
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public PageInfo<Money> listByMoney(Money money,Integer page,Integer size) throws Exception {
        return list("MoneyMapper.listByMoney",money,new RowBounds(page,size));
    }

    /**
    * 保存数据
    * @param money 实体对象
    * @return 实体id
    * @throws Exception 数据保存异常
    */
    public Integer insert(Money money) throws Exception {
        return insert("MoneyMapper.insert",money);
    }

    /**
    * 根据 id 修改
    * @param money 带id的实体对象
    * @return 受影响的行数
    * @throws Exception 数据修改异常
    */
    public Integer updateById(Money money) throws Exception {
        return update("MoneyMapper.updateById",money);
    }

    /**
    * 根据 id 删除
    * @param id 数据id
    * @return 受影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteById(Long id) throws Exception {
        return delete("MoneyMapper.deleteById",id);
    }

    /**
    * 根据 id 查找
    * @param id 实体id
    * @return 实体
    * @throws Exception 查询异常
    */
    public Money selectById(Long id) throws Exception {
        return selectOne("MoneyMapper.selectById",id);
    }

    /**
    * 根据 id 批量删除
    * @param ids 要删除的id
    * @return 影响的行数
    * @throws Exception 数据删除异常
    */
    public Integer deleteByIds(List<Long> ids) throws Exception {
        return delete("MoneyMapper.deleteByIds",ids);
    }

    /**
    * 查询列表
    * @param map 参数
    * @return 列表
    * @throws Exception 数据返回异常
    */
    public PageInfo<Money> list(Map map) throws Exception {
        return list("MoneyMapper.list",map);
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
        return list("MoneyMapper.list",map,new RowBounds(page,size));
    }


    public PageInfo<Money> listParam(Map<String, String> map, Integer page, Integer size) throws Exception {
        return list("MoneyMapper.listParam",map,new RowBounds(page,size));
    }
}
