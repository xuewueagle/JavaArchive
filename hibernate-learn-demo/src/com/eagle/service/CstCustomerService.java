package com.eagle.service;

import com.eagle.dao.CstCustomerDao;
import com.eagle.entity.CstCustomerEntity;

public class CstCustomerService {

    public void saveCustomer(CstCustomerEntity c){
        new CstCustomerDao().save(c);
    }
}
