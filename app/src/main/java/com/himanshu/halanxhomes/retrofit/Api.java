package com.himanshu.halanxhomes.retrofit;

import com.himanshu.halanxhomes.model.Houses;
import com.himanshu.halanxhomes.model.LogOut;
import com.himanshu.halanxhomes.model.LoginKey;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("rest-auth/login/")
    Call<LoginKey> userkey(@Field("username") String username,
                           @Field("password") String password);

    @GET("homes/houses/")
    Call<Houses> houselisting(@Query("furnish_type") String furnish, @Query("accomodation_allowed") String accomodation_allow
            , @Query("accomodation_type") String accom_type, @Query("rent_min") String rent_min, @Query("rent_max") String rent_max
            , @Query("latitude") String lat, @Query("longitude") String lon, @Query("radius") String radius,
                              @Query("bhk_count") String bhk_count);

    @FormUrlEncoded
    @POST("rest-auth/logout/")
    Call<LogOut> logout(@Field("key") String logkey);

}
