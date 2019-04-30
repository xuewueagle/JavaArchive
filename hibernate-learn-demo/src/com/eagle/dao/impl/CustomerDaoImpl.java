package com.eagle.dao.impl;

import com.eagle.dao.CustomerDao;
import com.eagle.entity.CstCustomerEntity;
import com.eagle.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public List<CstCustomerEntity> findCustomerList(CstCustomerEntity c, int firstResult, int maxResults) {

        Session session = HibernateUtil.getSession();
        Criteria cr = session.createCriteria(CstCustomerEntity.class);
        cr.setFirstResult(firstResult);
        cr.setMaxResults(maxResults);

        List list = cr.list();

        return list;
    }

    /**
     * 查询列表
     * @param dc
     * @param firstResult
     * @param maxResults
     * @return
     */
    @Override
    public List<CstCustomerEntity> findCustomerList(DetachedCriteria dc, int firstResult, int maxResults) {

        Session session = HibernateUtil.getSession();
        // 离线criteria与session绑定生成可执行criteria
        Criteria cr = dc.getExecutableCriteria(session);

        cr.setFirstResult(firstResult);
        cr.setMaxResults(maxResults);
        List list = cr.list();

        return list;
    }

    @Override
    public Long findCustomerCount(CstCustomerEntity c) {
        return null;
    }

    /**
     * 获取列表总记录数
     * @param dc
     * @return
     */
    @Override
    public Long findCustomerCount(DetachedCriteria dc) {

        Session session = HibernateUtil.getSession();
        // 离线criteria与session绑定生成可执行criteria
        Criteria cr = dc.getExecutableCriteria(session);
        cr.setProjection(Projections.rowCount());

        // 查询总数
        Long total = (Long) cr.uniqueResult();


        return total;
    }

    /**
     * 添加客户
     * @param c
     */
    @Override
    public void insert(CstCustomerEntity c) {
        Session session = HibernateUtil.getSession();
        try{
            // 开启事务
            session.beginTransaction();

            // 保存数据
            session.save(c);

            // 提交事务
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            // 回滚事务
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    /**
     * 修改客户
     * @param c
     */
    @Override
    public void update(CstCustomerEntity c) {
        Session session = HibernateUtil.getCurrentSession();
        try{
            // 开启事务
            session.beginTransaction();

            // 修改数据
            session.update(c);

            // 提交事务
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            // 回滚事务
            session.getTransaction().rollback();
        }
    }

    /**
     * 删除客户
     * @param
     */
    @Override
    public void delete(Long custId) {

        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {

            CstCustomerEntity cstCustomer = new CstCustomerEntity();
            cstCustomer.setCustId(custId);
            session.delete(cstCustomer);
            tr.commit();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
    }

    /**
     * 通过ID查询客户
     * @param custId
     * @return
     */
    @Override
    public CstCustomerEntity getCustomerInfoById(Long custId) {

        Session session = HibernateUtil.getCurrentSession();
        Transaction tr = session.beginTransaction();
        CstCustomerEntity c = (CstCustomerEntity)session.get(CstCustomerEntity.class,custId);
        tr.commit();
        return c;
    }
}
