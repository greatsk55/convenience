package kr.badream.convenience.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;

import kr.badream.convenience.LoginActivity;
import kr.badream.convenience.User;

/**
 * Created by user on 16. 6. 12.
 */
public class LoginHelper {

    public static String getUserID(Context context){
        SharedPreferences prefs = context.getSharedPreferences("userData", context.MODE_PRIVATE);
        return prefs.getString("userID", "");
    }
    public static String getUserName(Context context){
        SharedPreferences prefs = context.getSharedPreferences("userData", context.MODE_PRIVATE);
        return prefs.getString("name", "");
    }

    public static boolean isLogin(Context context){
        AccessToken accessToken1 = AccountKit.getCurrentAccessToken();
        com.facebook.AccessToken accessToken2 = com.facebook.AccessToken.getCurrentAccessToken();

        if (accessToken1 != null ) {
            //Handle Returning User

            //로그인일때 데이터 로드하는법.
            //SharedPreferences prefs = getSharedPreferences("userData", MODE_PRIVATE);
            //String id = prefs.getString("userID", "");
            //String name = prefs.getString("name", "");

            return true;
        } else if( accessToken2 != null){
            //로그인 상태일때 액티비티를 종료한다.
            return true;
        }else{
            //로그아웃 시 유저 데이터를 삭제한다.
            SharedPreferences test = context.getSharedPreferences("userData", context.MODE_PRIVATE);
            SharedPreferences.Editor editor = test.edit();
            editor.clear();
            editor.commit();

            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);

            AccessToken access1 = AccountKit.getCurrentAccessToken();
            com.facebook.AccessToken access2 = com.facebook.AccessToken.getCurrentAccessToken();
            if( access1 != null || access2 != null ){ return true; }
            else return false;
        }
    }
}
