package kr.badream.convenience;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import kr.badream.convenience.Helper.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 16. 6. 9.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private final String TAG = "LoginActivity";
    public static final String BASE_URL = "http://52.78.10.188/";

    public static int APP_REQUEST_CODE = 99;

    //페이스북 로그인, 콜백
    private LoginButton fbButton;
    private CallbackManager callbackManager;

    private BootstrapButton akButton;
    private Button button;

    private View mProgressView;


    private PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            //Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            //Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    private void init(){
        //akButton = (BootstrapButton) findViewById(R.id.ak_login);
        button = (Button) findViewById(R.id.ak_login);
        fbButton = (LoginButton) findViewById(R.id.fb_login);
        fbButton.setText("페이스북으로 로그인");
        fbButton.setReadPermissions(Arrays.asList("public_profile", "user_friends", "email"));

        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECEIVE_SMS)
                .check();


        fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                final String id = loginResult.getAccessToken().getUserId();
                Bundle params = new Bundle();
                params.putString("fields", "id,name,gender");
                new GraphRequest(
                        com.facebook.AccessToken.getCurrentAccessToken(), //loginResult.getAccessToken(),
                        "/me",
                        params,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                try {
                                    Log.e("JSON", response.toString());
                                    JSONObject data = response.getJSONObject();

                                    final String id = data.getString("id");
                                    String name = data.getString("name");
                                    String gender = data.getString("gender");

                                    if (gender.equals("male")) {
                                        registrationProcessWithRetrofit(id, 2, name, 1);
                                    } else {
                                        registrationProcessWithRetrofit(id, 2, name, 2);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                ).executeAsync();
            }
            @Override
            public void onCancel() {
                Log.i("bhc :", "Login attempt canceled.");
            }
            @Override
            public void onError(FacebookException e) {
                Log.i("bhc :", "Login attempt failed.");
            }
        });

        //akButton.setOnClickListener(this);
        button.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        init();

        //현재 로그인 돼었는지 확인한다.
        AccessToken accessToken1 = AccountKit.getCurrentAccessToken();
        com.facebook.AccessToken accessToken2 = com.facebook.AccessToken.getCurrentAccessToken();

        if (accessToken1 != null) {
            //Handle Returning User
            //finish();
        } else if( accessToken2 != null){
            //Handle Returning User
            //finish();
        }else{
            //Handle new or logged out user
        }

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
    private ApiInterface getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiInterface mInterfaceService = retrofit.create(ApiInterface.class);
        return mInterfaceService;
    }


    private void registrationProcessWithRetrofit(final String id, int flag, String name,int gender){
        ApiInterface mApiService = this.getInterfaceService();
        Call<User> mService = mApiService.registration(id, flag, name, gender);
        mService.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.i("aaa", response.body().isLogin);

                User mLoginObject = response.body();
                String returnedResponse = mLoginObject.isLogin;
                //showProgress(false);
                if(returnedResponse.trim().equals("1")){
                    // redirect to Main Activity page
                    String returnedUserID = mLoginObject.userID;
                    String returnedName = mLoginObject.name;

                    SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("userID", returnedUserID.trim());
                    editor.putString("name", returnedName.trim());
                    editor.commit();

                }
                if(returnedResponse.trim().equals("0")){
                    // use the registration button to register
                    Toast.makeText( getApplicationContext(), "can\'t join. please retry.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Toast.makeText( getApplicationContext(), "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }
        });
    }





    public void onClick(View v) {
        if(v.getId() == R.id.ak_login){
            onLoginPhone(v);
        }
    }
    public void onLoginPhone(final View view) {
        final Intent intent = new Intent( LoginActivity.this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN)
                        .setReadPhoneStateEnabled(true)
                        .setReceiveSMS(true).setFacebookNotificationsEnabled(true);

        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //페이스북 로그인
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                //TODO 로그인 실패 액티비티
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    String id = loginResult.getAccessToken().getAccountId();
                    toastMessage = "Success:" + id;
                    registrationProcessWithRetrofit(id,1,"무명",0);
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }
                //TODO Login 후 처리
            }

            // Surface the result to your user in an appropriate way.
            Toast.makeText(
                    this,
                    toastMessage,
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
}
