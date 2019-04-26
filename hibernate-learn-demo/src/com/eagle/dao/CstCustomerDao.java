package com.eagle.dao;

import com.eagle.entity.CstCustomerEntity;
import com.eagle.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CstCustomerDao {

    /**
     * 保存客户
     * @param c
     */
    public void save(CstCustomerEntity c){
        // 先获取session
        Session session = HibernateUtils.getSession();

        // 开启事务
        Transaction tr = session.beginTransaction();

        // 保存用户
        session.save(c);

        // 提交事务
        tr.commit();

        // 释放资源
        session.close();
    }
}
