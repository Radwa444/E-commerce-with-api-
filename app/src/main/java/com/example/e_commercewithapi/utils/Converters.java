package com.example.e_commercewithapi.utils;

import androidx.room.TypeConverter;

import com.example.e_commercewithapi.data.models.local.Categories.Category;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Converters {
    private static final Gson gson=new Gson();
    @TypeConverter
   public String fromImagesList(List<String> Images){
       return gson.toJson(Images);
   }
   @TypeConverter
    public List<String>  toImagesList(String json){
        return gson.fromJson(json,new TypeToken<List<String>>(){}.getType());
   }

    @TypeConverter
    public String fromProductList(List<Product> Images){
        return gson.toJson(Images);
    }
    @TypeConverter
    public List<Product>  toProductList(String json){
        return gson.fromJson(json,new TypeToken<List<Product>>(){}.getType());
    }

    @TypeConverter
    public String fromCategory(Category category){
        return gson.toJson(category);
    }
    @TypeConverter
    public Category  toCategoryList(String json){
        return gson.fromJson(json,new TypeToken<Category>(){}.getType());
    }
}
