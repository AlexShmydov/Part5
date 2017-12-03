public class BaseProduct {
    private String name;
    private String cost;
    private String description;

    public BaseProduct(String name, String cost, String description) {
        this.cost = cost;
        this.description = description;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String link) {
        this.description = link;
    }
}
