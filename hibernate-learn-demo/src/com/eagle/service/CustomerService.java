package com.eagle.service;

import com.eagle.entity.CstCustomerEntity;

import java.util.List;

public interface CustomerService {

    // 查询客户列表总记录数
    public long findCustomerCount(CstCustomerEntity cstCustomer);

    // 查询客户列表
    public List<CstCustomerEntity> findCustomerList(CstCustomerEntity cstCustomer,int firstResult,int maxResults);

    // 添加客户
    public void insertCustomer(CstCustomerEntity cstCustomer);

    // 修改客户
    public void updateCustomer(Long custId,CstCustomerEntity cstCustomer);

    // 删除客户
    public void deleteCustomer(Long custId);

    // 通过id查询客户
    public CstCustomerEntity findCustomerById(Long custId);


}
