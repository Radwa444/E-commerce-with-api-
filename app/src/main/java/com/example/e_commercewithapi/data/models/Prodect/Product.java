package com.example.e_commercewithapi.data.models.Prodect;

import com.example.e_commercewithapi.data.models.Categories.Category;

import java.util.List;

public class Product {
//    [
//    {
//        "id": 4,
//            "title": "Handmade Fresh Table",
//            "slug": "handmade-fresh-table",
//            "price": 687,
//            "description": "Andy shoes are designed to keeping in...",
//            "category": {
//        "id": 5,
//                "name": "Others",
//                "image": "https://placehold.co/600x400",
//                "slug": "others"
//    },
//        "images": [
//        "https://placehold.co/600x400",
//                "https://placehold.co/600x400",
//                "https://placehold.co/600x400"
//    ]
//    }
//    // ...
//]
    private int id;
    private String title;
    private String slug;
    private String price;
    private String description;
    private List<Category> category;
    private List<String> images;
   public Product(int id,String slug,String price,String description,List<Category> category,List<String> images,String title){
       this.id=id;
       this.slug=slug;
       this.price=price;
       this.description=description;
       this.category=category;
       this.images=images;
       this.title=title;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
