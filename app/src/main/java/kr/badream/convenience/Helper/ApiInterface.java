package kr.badream.convenience.Helper;

import org.json.JSONObject;

import java.util.List;

import kr.badream.convenience.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("index2.php/{userID}/{prodID}")
    Call<List<Helper_reviewData>> loadItemInfoList(@Path("userID") int userID, @Path("prodID") int prodID);

    @GET("index.php/{storeID}")
    Call<List<Helper_mapData>> loadMapList(@Path("storeID") int storeID);

    @GET("index.php/{userID}/{storeID}/{mainCategory}")
    Call<List<Helper_itemData>> loadStoreCategoryList(@Path("userID") int userID, @Path("storeID") int storeID, @Path("mainCategory") int mainCategory);

    @POST("index.php/{ID}/{flag}/{name}/{gender}")
    Call<User> registration(@Path("ID") String id, @Path("flag") int flag, @Path("name") String name, @Path("gender") int gender);

}