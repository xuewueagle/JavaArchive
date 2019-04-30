package com.eagle.service;

import com.eagle.entity.CstLinkmanEntity;

import java.util.List;

public interface LinkManService {
    // 查询客户列表总记录数
    public long findLinkManCount(CstLinkmanEntity cl);

    // 查询客户列表
    public List<CstLinkmanEntity> findLinkManList(CstLinkmanEntity cl, int firstResult, int maxResults);

    // 添加客户
    public void insertLinkMan(CstLinkmanEntity cl);

    // 修改客户
    public void updateLinkMan(Long lkmId,CstLinkmanEntity cl);

    // 删除客户
    public void deleteLinkMan(Long lkmId);

    // 通过id查询客户
    public CstLinkmanEntity findLinkManById(Long lkmId);
}
