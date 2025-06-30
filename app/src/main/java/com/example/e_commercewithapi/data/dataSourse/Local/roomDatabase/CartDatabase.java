package com.example.e_commercewithapi.data.dataSourse.Local.roomDatabase;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.e_commercewithapi.data.models.remote.product.ProductEntity;
import com.example.e_commercewithapi.utils.Converters;

@Database(entities = {ProductEntity.class},version = 5, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class CartDatabase extends RoomDatabase {
public abstract CartDAO cartDAO();

}
