package kr.badream.convenience;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.accountkit.AccountKit;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

/**
 * Created by user on 16. 6. 9.
 */
public class LoginActivity extends Activity {

    //페이스북 로그인, 콜백
    private LoginButton fbButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

        //accountKit 초기화
        AccountKit.initialize(getApplicationContext());

        fbButton = (LoginButton) findViewById(R.id.login_button);
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
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //페이스북 로그인
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }
}
