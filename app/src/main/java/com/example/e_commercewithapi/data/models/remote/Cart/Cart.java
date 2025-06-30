package com.example.e_commercewithapi.data.models.remote.Cart;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.utils.Converters;
import java.util.List;

@Entity(tableName = "cart_table")
public class Cart {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @TypeConverters(Converters.class)
    private List<Product> product;

    private float itemPrices = 0;
    private float couponDiscount = 0;
    public static final float shippingPrice = 40;

    public Cart(List<Product> product, float itemPrices, float couponDiscount) {
        this.product = product;
        this.itemPrices = itemPrices;
        this.couponDiscount = couponDiscount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public float getItemPrices() {
        return itemPrices;
    }

    public void setItemPrices(float itemPrices) {
        this.itemPrices = itemPrices;
    }

    public float getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(float couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public float getTotalCost() {
        return itemPrices + shippingPrice - couponDiscount;
    }
}
