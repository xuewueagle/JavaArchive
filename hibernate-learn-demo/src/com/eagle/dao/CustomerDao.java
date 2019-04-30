package com.eagle.dao;

import com.eagle.entity.CstCustomerEntity;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao {

    // 自定义条件查询客户列表
    public List<CstCustomerEntity> findCustomerList(CstCustomerEntity c,int firstResult, int maxResults);
    public List<CstCustomerEntity> findCustomerList(DetachedCriteria dc,int firstResult, int maxResults);

    // 自定义查询客户列表总记录数
    public Long findCustomerCount(CstCustomerEntity c);
    public Long findCustomerCount(DetachedCriteria dc);

    // 添加客户
    public void insert(CstCustomerEntity c);

    // 更新客户
    public void update(CstCustomerEntity c);

    // 删除客户
    public void delete(Long custId);

    // 通过ID查询客户
    public CstCustomerEntity getCustomerInfoById(Long custId);



}
