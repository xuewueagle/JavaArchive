package com.eagle.dao.impl;

import com.eagle.dao.LinkManDao;
import com.eagle.entity.CstLinkmanEntity;
import com.eagle.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import java.util.List;

public class LinkManDaoImpl implements LinkManDao {

    @Override
    public List<CstLinkmanEntity> findLinkManList(DetachedCriteria dc, int firstResult, int maxResults) {

        Session session = HibernateUtil.getSession();
        // 离线criteria与session绑定生成可执行criteria
        Criteria cr = dc.getExecutableCriteria(session);
        cr.setFirstResult(firstResult);
        cr.setMaxResults(maxResults);

        List list = cr.list();

        return list;
    }

    @Override
    public Long findLinkManCount(DetachedCriteria dc) {

        Session session = HibernateUtil.getSession();
        // 离线criteria与session绑定生成可执行criteria
        Criteria cr = dc.getExecutableCriteria(session);
        cr.setProjection(Projections.rowCount());

        // 查询总数
        Long total = (Long) cr.uniqueResult();


        return total;
    }

    /**
     * 添加联系人
     * @param c
     */
    @Override
    public void insert(CstLinkmanEntity c) {
        Session session =HibernateUtil.getSession();

        // 开启事务
        Transaction tr = session.beginTransaction();
        try{
            session.save(c);
            tr.commit();
        }catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public void update(CstLinkmanEntity c) {

    }

    @Override
    public void delete(Long custId) {

    }

    @Override
    public CstLinkmanEntity getLinkManInfoById(Long custId) {
        return null;
    }
}
