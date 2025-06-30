package com.example.e_commercewithapi.data.dataSourse.Local.roomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.e_commercewithapi.data.models.remote.product.ProductEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable addProductInCart(ProductEntity product);
    @Delete
    Completable deleteProductInCart(ProductEntity product);
    @Update
    Completable updateProductInCart(ProductEntity product);
    @Query("DELETE FROM product_entity")
    Completable deleteAll();
    @Query("SELECT * FROM product_entity ORDER BY title ASC")
    Single<List<ProductEntity>> getProduct();

   

}
