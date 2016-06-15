package kr.badream.convenience.Helper;

import org.json.JSONObject;

import java.util.List;

import kr.badream.convenience.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("index.php/{storeID}")
    Call<List<Helper_mapData>> loadMapList(@Path("storeID") int storeID);

    @GET("index.php/{userID}/{prodID}")
    Call<Helper_itemInfo> setLiked(@Path("userID") int userID, @Path("prodID") int prodID);

    @GET("index.php/{userID}/{storeID}/{mainCategory}")
    Call<List<Helper_itemData>> loadStoreCategoryList(@Path("userID") int userID, @Path("storeID") int storeID, @Path("mainCategory") int mainCategory);

    @POST("index.php/{ID}/{flag}/{name}/{gender}")
    Call<User> registration(@Path("ID") String id, @Path("flag") int flag, @Path("name") String name, @Path("gender") int gender);

    @POST("index.php/{userID}/{userName}/{prodID}/{price}/{contents}")
    Call<Helper_reviewData> postReview(@Path("userID") int userID, @Path("userName") String userName, @Path("prodID") String prodID, @Path("price") String price,
                                       @Path("contents") String contents);

    @GET("index2.php/{userID}/{prodID}")
    Call<Helper_itemInfo> loadItemInfoList(@Path("userID") int userID, @Path("prodID") int prodID);
}






