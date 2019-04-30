package com.eagle.dao;

import com.eagle.entity.CstLinkmanEntity;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface LinkManDao {

    // 自定义条件查询联系人列表
    public List<CstLinkmanEntity> findLinkManList(DetachedCriteria dc, int firstResult, int maxResults);

    // 自定义查询联系人列表总记录数
    public Long findLinkManCount(DetachedCriteria dc);

    // 添加联系人
    public void insert(CstLinkmanEntity c);

    // 更新联系人
    public void update(CstLinkmanEntity c);

    // 删除联系人
    public void delete(Long custId);

    // 通过ID查询联系人
    public CstLinkmanEntity getLinkManInfoById(Long custId);
}
