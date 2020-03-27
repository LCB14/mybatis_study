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
package com.lcb.jdbc;

import java.sql.*;

/**
 * @author changbao.li Date: 2019-10-01 Time: 17:39
 * @version $
 */
public class TestJDBC {

    /**
     * <P>Applications no longer need to explicitly load JDBC drivers using <code>Class.forName()</code>. Existing programs
     *  * which currently load JDBC drivers using <code>Class.forName()</code> will continue to work without
     *  * modification.
     *
     * @see DriverManager
     *
     * 无须手动加载驱动了！！
     */
    static {
//        try {
//            // 1、加载驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 2、创建连接
            connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/test_db?useSSL=false&characterEncoding=utf-8", "root",
                               "li123456");
            // 3、预编译SQL
            preparedStatement = connection.prepareStatement("select * from student where id = ?");
            preparedStatement.setInt(1, 1);

            // 4、执行SQL
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String columnName1 = resultSet.getMetaData().getColumnName(1);
                String columnName2 = resultSet.getMetaData().getColumnName(2);
                System.out.println(columnName1 + ":" + resultSet.getInt(1));
                System.out.println(columnName2 + ":" + resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
