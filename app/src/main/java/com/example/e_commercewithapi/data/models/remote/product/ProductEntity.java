package com.example.e_commercewithapi.data.models.remote.product;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.e_commercewithapi.data.models.local.Categories.Category;

import java.util.List;
@Entity(tableName = "product_entity")
public class ProductEntity {
    @PrimaryKey
    private int id;
    private String title;
    private String slug;
    private int price;
    private String description;
    private Category category;
    private List<String> images;
    private Boolean selected;
    private int counter=1;
    private float itemPrices=1f;
    public ProductEntity(int id,String slug,int price,String description,Category category,List<String> images,String title,Boolean selected,int counter,float itemPrices){
        this.id=id;
        this.slug=slug;
        this.price=price;
        this.description=description;
        this.category=category;
        this.images=images;
        this.title=title;
        this.selected=selected;
        this.counter=counter;
        this.itemPrices=itemPrices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {


        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    public float getItemPrices(){
        return this.itemPrices;
    }
    public void setItemPrices(float itemPrices){
        this.itemPrices=itemPrices;

    }
}
