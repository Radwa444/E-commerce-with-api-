package com.example.e_commercewithapi.data.models.Prodect;

public class Review {
   private String name;
   private String image;
   private  float rating;
   private String comment;
   public Review(String name, String image, float rating, String comment){
       this.name=name;
       this.image=image;
       this.rating=rating;
       this.comment=comment;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
