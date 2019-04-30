package com.eagle.dao;

import com.eagle.entity.CstCustomerEntity;
import com.eagle.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    /**
     * 查询所有的客户
     * @return
     */
    public List<CstCustomerEntity> findAll(){

        Session session = HibernateUtils.getSession();
        Transaction tr = session.beginTransaction();

        // 获取全部记录
        Criteria cr = session.createCriteria(CstCustomerEntity.class);

        // 将所有记录存储在List集合中
        List<CstCustomerEntity> list = cr.list();

        tr.commit();
        session.close();

        return list;
    }

    /**
     * 带查询条件的查询所有的客户
     * @param custName
     * @return
     */
    public List<CstCustomerEntity> findAll(String custName){
        Session session = HibernateUtils.getSession();
        Transaction tr = session.beginTransaction();

        // 获取全部记录
        Criteria cr = session.createCriteria(CstCustomerEntity.class);

        // 添加查询条件
        if(custName != null && !custName.trim().isEmpty()){
            cr.add(Restrictions.like("custName","%"+custName+"%"));
        }

        // 查询
        List<CstCustomerEntity> list = cr.list();

        tr.commit();
        session.close();

        return list;

    }

}


