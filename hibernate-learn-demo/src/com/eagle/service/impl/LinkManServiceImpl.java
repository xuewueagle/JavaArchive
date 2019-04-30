package com.eagle.service.impl;

import com.eagle.dao.LinkManDao;
import com.eagle.dao.impl.LinkManDaoImpl;
import com.eagle.entity.CstLinkmanEntity;
import com.eagle.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class LinkManServiceImpl implements LinkManService {

    // 总记录数
    @Override
    public long findLinkManCount(CstLinkmanEntity cl) {

        LinkManDao linkManDao = new LinkManDaoImpl();

        DetachedCriteria dc = this.spliceQueryCondition(cl);

        Long total = linkManDao.findLinkManCount(dc);

        return total;
    }

    // 分页查询
    @Override
    public List<CstLinkmanEntity> findLinkManList(CstLinkmanEntity cl, int firstResult, int maxResults) {
        LinkManDao linkManDao = new LinkManDaoImpl();

        DetachedCriteria dc = this.spliceQueryCondition(cl);
        List<CstLinkmanEntity> list = linkManDao.findLinkManList(dc,firstResult,maxResults);

        return list;
    }

    @Override
    public void insertLinkMan(CstLinkmanEntity cl) {
        LinkManDao linkManDao = new LinkManDaoImpl();
        linkManDao.insert(cl);
    }

    @Override
    public void updateLinkMan(Long lkmId, CstLinkmanEntity cl) {

    }

    @Override
    public void deleteLinkMan(Long lkmId) {

    }

    @Override
    public CstLinkmanEntity findLinkManById(Long lkmId) {
        return null;
    }

    /**
     * 拼接查询条件公共代码
     * @param cl
     */
    private DetachedCriteria spliceQueryCondition(CstLinkmanEntity cl){

        // 拼接查询条件
        DetachedCriteria dc = DetachedCriteria.forClass(CstLinkmanEntity.class);
        if(cl != null){
            if(cl.getLkmName() != null && !cl.getLkmName().equals("")){
                // 拼接查询条件客户名称
                dc.add(Restrictions.eq("lkmName",cl.getLkmName()));
            }
        }

        return dc;
    }
}
