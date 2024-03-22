package com.example.fichasumativa_joaorodrigues_n9.classes;

public class ProdutcsData {
    private String id;
    private String title;
    private String description;
    private String price;
    private String thumbnail;

    public ProdutcsData(String id, String title, String description, String price, String thumbnail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThunbnail() {
        return thumbnail;
    }

    public void setThunbnail(String thunbnail) {
        this.thumbnail = thunbnail;
    }
}
