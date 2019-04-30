package com.eagle.service;

import com.eagle.dao.CstCustomerDao;
import com.eagle.entity.CstCustomerEntity;

import java.util.List;

public class CstCustomerService {

    public void saveCustomer(CstCustomerEntity c){
        new CstCustomerDao().save(c);
    }

    /**
     * 查询所有的客户
     * @return
     */
    public List<CstCustomerEntity> findAll(){
        return new CstCustomerDao().findAll();
    }

    /**
     * 带查询条件查询所有的客户
     * @param custname
     * @return
     */
    public List<CstCustomerEntity> findAll(String custname){
        return new CstCustomerDao().findAll(custname);
    }
}
