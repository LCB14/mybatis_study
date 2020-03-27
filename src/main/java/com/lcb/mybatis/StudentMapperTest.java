/**
 *    Copyright 2009-2020 the original author or authors.
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
package com.lcb.mybatis;

import com.lcb.beans.Student;
import com.lcb.mapper.StudentMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

public class StudentMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        Reader reader = null;
        try {
            // 通过MyBatis自带的Resources工具来加载配置文件
            reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
            // 通过SqlSessionFactoryBuilder()来构建sqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        // 打开一个session对象
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            /**
             * 通过session对象来获取mapper
             *
             * @see DefaultSqlSession#getMapper(java.lang.Class)
             */
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> studentList = studentMapper.selectAll();
            System.out.println(studentList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 记得关闭session
            if (sqlSession != null) {
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}