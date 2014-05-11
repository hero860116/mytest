package test.common.bean;

/**
 * User: weilin.li
 * Date: 14-5-7
 * Time: 上午9:17
 */
public class CustomerDO extends BaseDO{
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Integer integral;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}
