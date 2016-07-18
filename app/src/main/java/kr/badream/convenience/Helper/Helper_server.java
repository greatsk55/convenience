package kr.badream.convenience.Helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kr.badream.convenience.LoginActivity;
import kr.badream.convenience.Menu_View.Activity_Search;
import kr.badream.convenience.Menu_View.Activity_map;
import kr.badream.convenience.R;
import kr.badream.convenience.User;
import kr.badream.convenience.View.Activity_register_review;
import kr.badream.convenience.View.View_item_info;
import kr.badream.convenience.View.View_item_list;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 16. 6. 12.
 */
public class Helper_server {
    public static final String BASE_URL = "http://52.78.10.188/";

    public static ApiInterface getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(LenientGsonConverterFactory.create())
                .build();

        final ApiInterface mInterfaceService = retrofit.create(ApiInterface.class);
        return mInterfaceService;
    }

    //회원가입 요청
    public static void registrationProcessWithRetrofit(final Activity context, final String id, int flag, String name, int gender){
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(R.attr.progressBarStyle);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<User> mService = mApiService.registration(id, flag, name, gender);
        mService.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User mLoginObject = response.body();
                String returnedResponse = mLoginObject.isLogin;
                //showProgress(false);
                if(returnedResponse.trim().equals("1")){
                    // redirect to Main Activity page

                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    int returnedUserID = mLoginObject.userID;
                    String returnedName = mLoginObject.name;

                    SharedPreferences prefs = context.getSharedPreferences("userData", context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("userID", returnedUserID);
                    editor.putString("name", returnedName.trim());
                    editor.commit();

                    context.finish();
                }
                if(returnedResponse.trim().equals("0")){
                    // use the registration button to register
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    Toast.makeText( context, "can\'t join. please retry.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Log.i("aaa", t.getMessage());
                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

    //맵데이터 요청
    public static void loadMapListWithRetrofit(final Activity context, final int storeID){
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(R.attr.progressBarStyle);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<List<Helper_mapData>> mService = mApiService.loadMapList(storeID);

        mService.enqueue(new Callback<List<Helper_mapData>>() {
            @Override
            public void onResponse(Call<List<Helper_mapData>> call, Response<List<Helper_mapData>> response) {

                List<Helper_mapData> mlistObject = response.body();

                Activity_map.map_data = new ArrayList<Helper_mapData>();
                for( Helper_mapData data : mlistObject) {
                    Activity_map.map_data.add(data);
                }
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Intent activity_map = new Intent( context , Activity_map.class);
                activity_map.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity_map.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(activity_map);
            }
            @Override
            public void onFailure(Call<List<Helper_mapData>> call, Throwable t) {
                call.cancel();

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

    //편의점 카테고리별 데이터 요청
    public static void loadStoreCategoryListWithRetrofit(final Activity context,final int userID, final int storeID, final int mainCategory, final int listIndex){
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(R.attr.progressBarStyle);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<List<Helper_itemData>> mService = mApiService.loadStoreCategoryList(userID, storeID, mainCategory);

        mService.enqueue(new Callback<List<Helper_itemData>>() {
            @Override
            public void onResponse(Call<List<Helper_itemData>> call, Response<List<Helper_itemData>> response) {

                List<Helper_itemData> mlistObject = response.body();

                //여기서 초기화 안해줘서 계속 중첩하며 아이템 증가함
                View_item_list.list = new ArrayList<Helper_itemData>();
                for( Helper_itemData data : mlistObject) {
                    View_item_list.list.add(data);
                }

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();


                View_item_list.storeID = storeID;
                View_item_list.listIndex = listIndex;
                View_item_list.mainCtg = mainCategory;

                Intent view_item_list = new Intent( context, View_item_list.class);
                context.startActivity(view_item_list);
            }
            @Override
            public void onFailure(Call<List<Helper_itemData>> call, Throwable t) {
                call.cancel();

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }
    //편의점 카테고리 리스트 갱신
    public static void refreshStoreCategoryListWithRetrofit(final Activity context,final int userID, final int storeID, final int mainCategory, final int listIndex){
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(R.attr.progressBarStyle);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<List<Helper_itemData>> mService = mApiService.loadStoreCategoryList(userID, storeID, mainCategory);

        mService.enqueue(new Callback<List<Helper_itemData>>() {
            @Override
            public void onResponse(Call<List<Helper_itemData>> call, Response<List<Helper_itemData>> response) {

                List<Helper_itemData> mlistObject = response.body();

                //여기서 초기화 안해줘서 계속 중첩하며 아이템 증가함
                View_item_list.list = new ArrayList<Helper_itemData>();
                for( Helper_itemData data : mlistObject) {
                    View_item_list.list.add(data);
                }

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Intent view_item_list = new Intent( context, View_item_list.class);

                View_item_list.mCallback.refreshMainActivity();
            }
            @Override
            public void onFailure(Call<List<Helper_itemData>> call, Throwable t) {
                call.cancel();

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

    //모든 상품 데이터 요청
    public static void loadAllItemListWithRetrofit(final Activity context,final int userID, final int storeID, int mainCategory){
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(R.attr.progressBarStyle);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<List<Helper_itemData>> mService = mApiService.loadStoreCategoryList(userID, storeID, mainCategory);


        mService.enqueue(new Callback<List<Helper_itemData>>() {
            @Override
            public void onResponse(Call<List<Helper_itemData>> call, Response<List<Helper_itemData>> response) {
                ArrayList<Helper_itemData> list;
                List<Helper_itemData> mlistObject = response.body();


                View_item_list.list = new ArrayList<Helper_itemData>();
                for( Helper_itemData data : mlistObject) {
                    View_item_list.list.add(data);
                }
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Intent activity_compare = new Intent( context , Activity_Search.class);
                activity_compare.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity_compare.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(activity_compare);
                context.finish();

            }
            @Override
            public void onFailure(Call<List<Helper_itemData>> call, Throwable t) {
                call.cancel();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

    //좋아요 누르고 갱신
    public static void setLikedWithRetrofit(final Activity context, final int userID, final int prodID){
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(R.attr.progressBarStyle);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<Helper_itemInfo> mService = mApiService.setLiked(userID, prodID);


        mService.enqueue(new Callback<Helper_itemInfo>() {
            @Override
            public void onResponse(Call<Helper_itemInfo> call, Response<Helper_itemInfo> response) {
                Helper_itemInfo mlistObject = response.body();

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                View_item_info.item = mlistObject;

                View_item_info.mCallback.refreshMainActivity();

                //Intent intent = new Intent(context, View_item_info.class);
                //intent.putExtra("item_info", mlistObject);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //context.startActivity(intent);
                //context.finish();
            }
            @Override
            public void onFailure(Call<Helper_itemInfo> call, Throwable t) {
                call.cancel();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }
    //상품 상세정보
    public static void loadItemInfoListWithRetrofit(final Activity context, final int userID, int prodID){
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(R.attr.progressBarStyle);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<Helper_itemInfo> mService = mApiService.loadItemInfoList(userID, prodID);

        mService.enqueue(new Callback<Helper_itemInfo>() {
            @Override
            public void onResponse(Call<Helper_itemInfo> call, Response<Helper_itemInfo> response) {

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                if(response.body() != null) {
                    Helper_itemInfo mlistObject = response.body();

                    for( Helper_reviewData data : mlistObject.reviewData ){
                        Log.i("aaa", data.toString());
                    }

                    // 아이템 정보 전역변수
                    View_item_info.item = mlistObject;

                    Intent activity_compare = new Intent(context, View_item_info.class);
                    activity_compare.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(activity_compare);
                }
            }
            @Override
            public void onFailure(Call<Helper_itemInfo> call, Throwable t) {
                call.cancel();

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }
    public static void refreshItemInfoListWithRetrofit(final Activity context, final int userID, int prodID){
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(R.attr.progressBarStyle);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<Helper_itemInfo> mService = mApiService.loadItemInfoList(userID, prodID);

        mService.enqueue(new Callback<Helper_itemInfo>() {
            @Override
            public void onResponse(Call<Helper_itemInfo> call, Response<Helper_itemInfo> response) {

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                if(response.body() != null) {
                    Helper_itemInfo mlistObject = response.body();

                    View_item_info.item = mlistObject;
                }
            }
            @Override
            public void onFailure(Call<Helper_itemInfo> call, Throwable t) {
                call.cancel();

                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

    //리뷰 등록시 상품 상세정보
    public static void getAllItemList_to_register_review(final Activity context,final int userID, final int storeID, int mainCategory){
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(R.attr.progressBarStyle);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<List<Helper_itemData>> mService = mApiService.loadStoreCategoryList(userID, storeID, mainCategory);


        mService.enqueue(new Callback<List<Helper_itemData>>() {
            @Override
            public void onResponse(Call<List<Helper_itemData>> call, Response<List<Helper_itemData>> response) {
                ArrayList<Helper_itemData> list;
                list = new ArrayList<Helper_itemData>();
                List<Helper_itemData> mlistObject = response.body();

                for( Helper_itemData data : mlistObject) {
                    list.add(data);
                }
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Activity_register_review.list = list;
            }
            @Override
            public void onFailure(Call<List<Helper_itemData>> call, Throwable t) {
                call.cancel();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

    //리뷰 등록 요청
    public static void postReviewWithRetrofit(final Activity context, final int userID, String userName, String prodID, String price, String contents){
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(R.attr.progressBarStyle);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<Helper_reviewData> mService = mApiService.postReview( userID, userName, prodID, price, contents);
        mService.enqueue(new Callback<Helper_reviewData>() {
            @Override
            public void onResponse(Call<Helper_reviewData> call, Response<Helper_reviewData> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();


                context.finish();
            }
            @Override
            public void onFailure(Call<Helper_reviewData> call, Throwable t) {
                call.cancel();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }

}
