package com.king;

import com.king.mapper.RoleMapper;
import com.king.pojo.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * @author hspcadmin
 */
public class Mybatis {

    public static void main(String[] args) {
        String resource = "com/king/resource/mybatis-config.xml";
        InputStream inputStream = null;
        //Reader reader = null;
        try {
            //reader = new FileReader(Resources.getResourceAsFile(resource));
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = null;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRole(1L);
            System.out.println(role.getId()+":"+role.getRoleName()+":"+role.getNote());
            //sqlSession.commit(); 调用commit清空一级缓存

            System.out.println(".................query2................");
            Role role2 = roleMapper.getRole(1L);
            System.out.println(role2.getId()+":"+role2.getRoleName()+":"+role2.getNote());
            //sqlSession.commit();  调用commit清空一级缓存

        } catch (Exception e) {
            // TODO Auto-generated catch block
            assert sqlSession != null;
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            assert sqlSession != null;
            sqlSession.close();
        }
        query(sqlSessionFactory);
    }

    public static void query(SqlSessionFactory sqlSessionFactory) {

        System.out.println(".................query3................");
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRole(1L);
            System.out.println(role.getId()+":"+role.getRoleName()+":"+role.getNote());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            assert sqlSession != null;
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            assert sqlSession != null;
            sqlSession.close();
        }
    }

}
