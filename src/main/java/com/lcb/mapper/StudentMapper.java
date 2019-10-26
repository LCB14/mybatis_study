package com.lcb.mapper;

import com.lcb.beans.Student;
import java.util.List;

/**
 * @author changbao.li Date: 2019-10-02 Time: 11:02
 * @version $
 */
public interface StudentMapper {
    List<Student> selectAll();
}
