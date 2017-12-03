public class Product extends BaseProduct {

    private String link;

    public Product(String name, String cost, String description) {
        super(name, cost, description);
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
