package test.common.bean;

/**
 * User: weilin.li
 * Date: 14-5-7
 * Time: 上午9:18
 */
public class AddressDO {
    private Long id;
    private String Province;
    private String City;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
