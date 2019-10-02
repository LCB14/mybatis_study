package com.lcb.mapper;

import com.lcb.beans.Country;
import java.util.List;

/**
 * @author changbao.li Date: 2019-10-02 Time: 11:02
 * @version $
 */
public interface CountryMapper {
    List<Country> selectAll();
}
