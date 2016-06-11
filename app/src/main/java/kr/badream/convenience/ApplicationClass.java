package kr.badream.convenience;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.facebook.FacebookSdk;
import com.facebook.accountkit.AccountKit;

/**
 * Created by user on 16. 6. 9.
 */
public class ApplicationClass  extends Application {

    public static User user = null;

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();

        //facebook 초기화
        FacebookSdk.sdkInitialize(getApplicationContext());
        AccountKit.initialize(getApplicationContext());
    }

}
