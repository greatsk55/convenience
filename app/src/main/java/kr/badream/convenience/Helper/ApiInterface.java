package kr.badream.convenience.Helper;

import java.util.List;

import kr.badream.convenience.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    /*
    @GET("index.php/{storeID}/{mainCategory}")
    Call<List<Helper_itemData>> loadStoreCategoryList(@Path("storeID") int storeID, @Path("mainCategory") int mainCategory);
    */
    @GET("index.php/{storeID}/{mainCategory}")
    Call<Helper_itemData> loadStoreCategoryList(@Path("storeID") int storeID, @Path("mainCategory") int mainCategory);

    @POST("index.php/{ID}/{flag}/{name}/{gender}")
    Call<User> registration(@Path("ID") String id, @Path("flag") int flag, @Path("name") String name, @Path("gender") int gender);


    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Helper_itemData>> repoContributors(@Path("owner") String owner, @Path("repo") String repo);
}