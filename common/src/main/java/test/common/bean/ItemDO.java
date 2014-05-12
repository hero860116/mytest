package test.common.bean;

/**
 * User: weilin.li
 * Date: 14-5-12
 * Time: 下午6:02
 */
public class ItemDO extends BaseDO{
    private Long id;
    private String title;
    private String itemUrl;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
