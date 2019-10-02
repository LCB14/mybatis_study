package com.lcb.beans;

/**
 * @author changbao.li Date: 2019-10-02 Time: 11:03
 * @version $
 */
public class Country {
    private Integer id;

    private String countryName;

    private String countryCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + id +
            ", countryName='" + countryName + '\'' +
            ", countryCode='" + countryCode + '\'' +
            '}';
    }
}
