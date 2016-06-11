package kr.badream.convenience.Helper;

import kr.badream.convenience.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("api/{ID}/{flag}")
    Call<User> authenticate(@Path("ID") String id, @Path("flag") int flag);
    @POST("api/{ID}/{flag}/{name}/{gender}")
    Call<User> registration(@Path("ID") String id, @Path("flag") int flag, @Path("name") String name, @Path("gender") int gender);
}