package kr.badream.convenience;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

/**
 * Created by user on 16. 6. 9.
 */
public class LoginActivity extends Activity {


    public static int APP_REQUEST_CODE = 99;

    //페이스북 로그인, 콜백
    private LoginButton fbButton;
    private CallbackManager callbackManager;

    private BootstrapButton akButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //facebook 초기화
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        //accountKit 초기화
        AccountKit.initialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        akButton = (BootstrapButton) findViewById(R.id.ak_login);
        fbButton = (LoginButton) findViewById(R.id.fb_login);
        fbButton.setReadPermissions(Arrays.asList("public_profile", "user_friends", "email"));

        fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                //TODO 전화번호 인증모듈 띄우기
                final String id = loginResult.getAccessToken().getUserId();
                Log.i("aaa", "what"+id);
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
        akButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( v.getId() == R.id.ak_login ){
                    onLoginPhone(v);
                }
            }
        });


        //현재 로그인 돼었는지 확인한다.
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            //Handle Returning User
        } else {
            //Handle new or logged out user
        }
    }

    public void onLoginPhone(final View view) {
        final Intent intent = new Intent( LoginActivity.this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.CODE); // or .ResponseType.TOKEN
        // ... perform additional configuration ...
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
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }

                // If you have an authorization code, retrieve it from
                // loginResult.getAuthorizationCode()
                // and pass it to your server and exchange it for an access token.

                // Success! Start your next activity...
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
