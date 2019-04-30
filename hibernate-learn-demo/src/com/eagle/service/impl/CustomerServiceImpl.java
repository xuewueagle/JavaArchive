package com.eagle.service.impl;

import com.eagle.dao.CustomerDao;
import com.eagle.dao.impl.CustomerDaoImpl;
import com.eagle.entity.CstCustomerEntity;
import com.eagle.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    /**
     * 总记录数
     * @param cstCustomer
     * @return
     */
    @Override
    public long findCustomerCount(CstCustomerEntity cstCustomer) {
        CustomerDao cd = new CustomerDaoImpl();
        DetachedCriteria dc = this.spliceQueryCondition(cstCustomer);

        return cd.findCustomerCount(dc);
    }

    /**
     * 列表页
     * @param cstCustomer
     * @param firstResult
     * @param maxResults
     * @return
     */
    @Override
    public List<CstCustomerEntity> findCustomerList(CstCustomerEntity cstCustomer, int firstResult, int maxResults) {
        CustomerDao cd = new CustomerDaoImpl();
        DetachedCriteria dc =this.spliceQueryCondition(cstCustomer);

        return cd.findCustomerList(dc,firstResult,maxResults);
    }

    /**
     * 拼接查询条件公共代码
     * @param cstCustomer
     */
    private DetachedCriteria spliceQueryCondition(CstCustomerEntity cstCustomer){

        // 拼接查询条件
        DetachedCriteria dc = DetachedCriteria.forClass(CstCustomerEntity.class);
        if(cstCustomer != null){
            if(cstCustomer.getCustName() != null && !cstCustomer.getCustName().equals("")){
                // 拼接查询条件客户名称
                dc.add(Restrictions.eq("custName",cstCustomer.getCustName()));
            }
        }

        return dc;
    }

    /**
     * 添加客户
     * @param cstCustomer
     */
    @Override
    public void insertCustomer(CstCustomerEntity cstCustomer) {
        CustomerDao customerDao = new CustomerDaoImpl();
        customerDao.insert(cstCustomer);
    }

    /**
     * 修改客户
     * @param cstCustomer
     */
    @Override
    public void updateCustomer(Long custId,CstCustomerEntity cstCustomer) {
        CustomerDao customerDao = new CustomerDaoImpl();

        CstCustomerEntity cstCustomer_update = customerDao.getCustomerInfoById(custId);
        // 更新客户各信息
        cstCustomer_update.setCustName(cstCustomer.getCustName());
        cstCustomer_update.setCustLinkman(cstCustomer.getCustLinkman());
        cstCustomer_update.setCustPhone(cstCustomer.getCustPhone());
        cstCustomer_update.setCustMobile(cstCustomer.getCustMobile());
        cstCustomer_update.setCustSource(cstCustomer.getCustSource());
        cstCustomer_update.setCustIndustry(cstCustomer.getCustIndustry());
        cstCustomer_update.setCustLevel(cstCustomer.getCustLevel());

        customerDao.update(cstCustomer_update);
    }

    /**
     * 删除客户
     * @param custId
     */
    @Override
    public void deleteCustomer(Long custId) {
        CustomerDao customerDao = new CustomerDaoImpl();
        customerDao.delete(custId);
    }

    /**
     * 通过id查看客户信息
     * @param custId
     * @return
     */
    @Override
    public CstCustomerEntity findCustomerById(Long custId) {
        CustomerDao customerDao = new CustomerDaoImpl();

        return customerDao.getCustomerInfoById(custId);

    }
}
