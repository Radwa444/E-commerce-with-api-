package com.example.e_commercewithapi.data.mappers;

import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.data.models.remote.product.ProductEntity;

public class ProductMapper {
    public static ProductEntity mapToEntity(Product product){
        return new ProductEntity(
                product.getId(),
                product.getSlug(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory(),
                product.getImages(),
                product.getTitle(),
                product.getSelected(),
                product.getCounter(),
                product.getItemPrices()

        );
    }
}
