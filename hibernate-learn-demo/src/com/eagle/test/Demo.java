package com.eagle.test;

import com.alibaba.fastjson.JSONObject;
import com.eagle.entity.CstCustomerEntity;
import com.eagle.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;

/**
 * 测试Hibernate框架
 */
public class Demo {

    @Test
    public void testSave(){
        /**
         *  1. 先加载配置文件
         *  2. 创建SessionFactory对象，生成Session对象
         *  3. 创建session对象
         *  4. 开启事务
         *  5. 编写逻辑代码
         *  6. 提交事务
         *  7. 释放资源
         */

        // 1. 先加载配置文件
        //Configuration config = new Configuration();
        // 默认加载src目录下hibernate.cfg.xml的配置文件
        //config.configure();
        // 了解，手动加载
        //config.addResource("com/eagle....xx.hbm.xml");

        // 简写的方法
        Configuration config = new Configuration().configure();

        // 2. 创建SessionFactory对象
        SessionFactory factory = config.buildSessionFactory();

        // 3. 创建session对象
        Session session = factory.openSession();

        // 4. 开启事务
        Transaction tr = session.beginTransaction();

        // 5. 编写保存代码
        CstCustomerEntity c = new CstCustomerEntity();
        c.setCustName("测试框架345"); // 入库后为乱码--使用 jdbc 连接数据时在数据库名字后面添加?characterEncoding=UTF-8
        c.setCustMobile("15556000619");
        c.setCustLevel("3");
        c.setCustUserId(18L);
        // 保存数据，操作对象就相当于操作数据库的表结构
        session.save(c);

        // 6. 提交事务
        tr.commit();

        // 7. 释放资源
        session.close();
        factory.close();

    }

    /**
     * 测试工具类
     */
    @Test
    public void testSave1(){
        // 原来是：加载配置文件，获取Factory对象，获取session
        // 现在通过自己封装的HibernateUtils即可获取session
        Session session = HibernateUtils.getSession();

        Transaction tr = session.beginTransaction();

        CstCustomerEntity c = new CstCustomerEntity();
        c.setCustName("test");
        c.setCustMobile("18136000619");
        c.setCustLevel("2");
        c.setCustUserId(38L);

        session.save(c);
        tr.commit();
        session.close();

    }

    /**
     * 测试get()方法，获取查询，通过主键来查询一条记录
     */
    @Test
    public void testGet(){
        Session session = HibernateUtils.getSession();
        Transaction tr = session.beginTransaction();
        // 只返回不为空的字段
        CstCustomerEntity c = (CstCustomerEntity)session.get(CstCustomerEntity.class,3L);
        System.out.println(JSONObject.toJSON(c).toString());

        tr.commit();
        session.close();

    }

    /**
     * 测试删除的方法
     * 注意：删除或者修改，先查询再删除或者修改
     */
    @Test
    public void testDel(){
        Session session = HibernateUtils.getSession();
        Transaction tr = session.beginTransaction();
        // 测试查询的方法 2个参数：arg0查询JavaBean的class对象 arg1主键的值
        CstCustomerEntity c = (CstCustomerEntity)session.get(CstCustomerEntity.class,5L);

        // 删除客户
        session.delete(c);

        tr.commit();
        session.close();
    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdate(){
        Session session = HibernateUtils.getSession();
        Transaction tr = session.beginTransaction();
        CstCustomerEntity c = (CstCustomerEntity)session.get(CstCustomerEntity.class,4L);

        // 设置客户信息
        c.setCustName("中国西安");

        // 修改
        session.update(c);

        tr.commit();
        session.close();
    }

    /**
     * 测试添加或者修改
     */
    @Test
    public void testSaveOrUpdate(){
        Session session = HibernateUtils.getSession();

        Transaction tr = session.beginTransaction();

        CstCustomerEntity c = (CstCustomerEntity)session.get(CstCustomerEntity.class,4L);
        c.setCustName("国际港务区");

        session.saveOrUpdate(c);

        tr.commit();
        session.close();
    }

    /**
     * 测试查询的方法
     */
    @Test
    public void testSel(){
        Session session = HibernateUtils.getSession();
        Transaction tr = session.beginTransaction();
        // CstCustomerEntity是一个java实体类，这个类和某一个表对应！
        // Query query = session.createQuery("from CstCustomerEntity where custId=1");
        Query query = session.createQuery("from CstCustomerEntity where custId=1");

        List<CstCustomerEntity> list = query.list();
        for (CstCustomerEntity c : list){
            System.out.println(JSONObject.toJSON(c).toString());
        }
    }

    /**
     * 测试保存
     */
    @Test
    public void testSave2(){
        Session session = null;
        Transaction tr = null;

        try{
            session = HibernateUtils.getSession();
            tr = session.beginTransaction();
            CstCustomerEntity c = new CstCustomerEntity();
            c.setCustName("哈哈哈");
            c.setCustLevel("5");
            c.setCustMobile("15222889068");

            session.save(c);
            tr.commit();
        }catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
