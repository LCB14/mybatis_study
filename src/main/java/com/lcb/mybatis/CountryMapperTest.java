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

public class CountryMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    static  {
        try {
            // 通过MyBatis自带的Resources工具来加载配置文件
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            // 通过SqlSessionFactoryBuilder()来构建sqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // 打开一个session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过session对象来获取mapper
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> studentList = studentMapper.selectAll();
            System.out.println(studentList);
        } finally {
            // 记得关闭session
            sqlSession.close();
        }
    }
}