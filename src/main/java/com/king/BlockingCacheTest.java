/*
 *    Copyright 2009-2011 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.king;



import com.king.mapper.RoleMapper;
import com.king.pojo.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;


public class BlockingCacheTest {
  private static SqlSessionFactory sqlSessionFactory;


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
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      for (int i = 0; i < 5; i++) {
        new Thread(() -> {
          sqlSession.selectList("getRoles").forEach(role-> System.out.println(role));
        }).start();
      }

      Thread.sleep(3000);

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
