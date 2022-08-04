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
package com.king.ibatis.cache;


import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.Reader;


import static org.junit.Assert.*;

public class BlockingCacheTest extends BaseDataTest {
  private static SqlSessionFactory sqlSessionFactory;

  @BeforeClass
  public static void setup() throws Exception {
    createBlogDataSource();
    final String resource = "com/king/ibatis/resource/mybatis-config.xml";
    final Reader reader = Resources.getResourceAsReader(resource);
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
  }

  @Test
  public void BlockingCache() throws InterruptedException {
    for (int i = 0; i < 5; i++) {
      new Thread(() -> {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
          Assert.assertEquals(2, sqlSession.selectList("getRoles").size());
        }
      }).start();
    }

    Thread.sleep(5000);
  }


}
