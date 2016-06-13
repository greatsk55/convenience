package kr.badream.convenience.Helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 16. 6. 13.
 */
public class Helper_search {
    public ArrayList<Helper_itemData> list;

    private void loadAllItemListWithRetrofit(final Context context, final int storeID, int mainCategory){

        ApiInterface mApiService = Helper_server.getInterfaceService();
        Call<List<Helper_itemData>> mService = mApiService.loadStoreCategoryList(storeID, mainCategory);


        mService.enqueue(new Callback<List<Helper_itemData>>() {
            @Override
            public void onResponse(Call<List<Helper_itemData>> call, Response<List<Helper_itemData>> response) {

                List<Helper_itemData> mlistObject = response.body();

                list = new ArrayList<Helper_itemData>();

                for( Helper_itemData data : mlistObject) {
                    list.add(data);
                }
                //TODO 여기서 SearchActivity를 호출한다. 리스트 전달을 하면서

            }
            @Override
            public void onFailure(Call<List<Helper_itemData>> call, Throwable t) {
                call.cancel();

                Toast.makeText( context, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }
}
