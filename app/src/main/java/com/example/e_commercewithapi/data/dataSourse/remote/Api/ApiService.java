package com.example.e_commercewithapi.data.dataSourse.remote.Api;
import com.example.e_commercewithapi.utils.Config;
import com.example.e_commercewithapi.data.models.local.Categories.Category;
import com.example.e_commercewithapi.data.models.local.Login.RequestLogin;
import com.example.e_commercewithapi.data.models.local.Login.ResponseLogin;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.data.models.local.SignUp.RequestSignUp;
import com.example.e_commercewithapi.data.models.local.SignUp.ResponseSignUp;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST(Config.LOGIN_URL)
    Single<ResponseLogin> login(@Body RequestLogin requestLogin);
    @POST(Config.USERS_URL)
    @Headers("Content-Type: application/json;charset=UTF-8")
    Single<ResponseSignUp> signUp(@Body RequestSignUp requestSignUp);
    @GET(Config.TOKEN_URL)
    Single<ResponseSignUp> getUserProfile(@Header("Authorization")String token);

    @GET(Config.CATEGORIES_URL)
    Observable<List<Category>> getCategories();
    @GET(Config.CATEGORIES_URL+"/{id}")
    Single<Category> getCategoryById(@Path("id")int categoryId);
    @GET(Config.CATEGORIES_URL+"/slug/{slug}")
    Single<Category> getCategoryBySlug(@Path("slug")String categorySlug);
    @GET(Config.CATEGORIES_URL+"/{id}/products")
    Observable<List<Product>> getAllProductByCategory(@Path("id")int categoryId);
    @GET(Config.PRODUCT_URL)
    Observable<List<Product>> getAllProducts();
    @GET(Config.PRODUCT_URL+"/{id}")
    Single<Product> getProductById(@Path("id")int productId);
    @GET(Config.PRODUCT_URL+"/slug/{slug}")
    Single<Product> getProductBySlug(@Path("slug")String productSlug);
    @GET(Config.PRODUCT_URL)
    Observable<List<Product>> getProductWithPagination(@Query("offset") int offset,@Query("limit")int limit );


}
