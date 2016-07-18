package kr.badream.convenience.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.security.MessageDigest;
import java.util.ArrayList;

import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.R;

public class Activity_conView extends AppCompatActivity implements View.OnClickListener {

    ImageView cu ;
    ImageView gs25 ;
    ImageView mini;
    ImageView seven ;
    ImageView with ;

    View drawerView;
    DrawerLayout dlDrawer;

    //TODO 지도 퍼미션 추가하기
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

    @Override
    protected void onResume() {
        super.onResume();
        setCustomActionbar();
    }

    public void getAppKeyHash() {
        try {
            String something="";
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
            Log.d("lol key : ", something);
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conview);

        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECEIVE_SMS, Manifest.permission.ACCESS_COARSE_LOCATION
                ,Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

        cu = (ImageView) findViewById(R.id.cu_image);
        gs25 = (ImageView) findViewById(R.id.ge25_image);
        mini = (ImageView) findViewById(R.id.ministop_image);
        seven = (ImageView) findViewById(R.id.seven_image);
        with = (ImageView) findViewById(R.id.withme_image);

        cu.setOnClickListener(this);
        gs25.setOnClickListener(this);
        mini.setOnClickListener(this);
        seven.setOnClickListener(this);
        with.setOnClickListener(this);

        setCustomActionbar();
        getAppKeyHash();
    }

    @Override
    public void onClick(View v){
        Intent ctgview_intent = new Intent( Activity_conView.this , Activity_ctgView.class);

        switch(v.getId()){
            case R.id.cu_image:
                ctgview_intent.putExtra("storeID", 1);
                startActivity(ctgview_intent);
                break;

            case R.id.withme_image:
                ctgview_intent.putExtra("storeID", 2);
                startActivity(ctgview_intent);
                break;

            case R.id.ge25_image:
                ctgview_intent.putExtra("storeID", 3);
                startActivity(ctgview_intent);
                break;

            case R.id.seven_image:
                ctgview_intent.putExtra("storeID", 4);
                Toast.makeText(getApplicationContext(), "구현중 . . .", Toast.LENGTH_LONG).show();
                break;

            case R.id.ministop_image:
                ctgview_intent.putExtra("storeID", 5);
                Toast.makeText(getApplicationContext(), "구현중 . . .", Toast.LENGTH_LONG).show();
                break;
        }
        //startActivity(ctgview_intent);
    }

    private void setCustomActionbar(){

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        //set custom view layout
        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_main, null);
        actionBar.setCustomView(mCustomView);

        // set no padding both side
        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        // set actionbar backgroung image
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.activity_main_background));

        //set actionbar layout layoutparams
//        ActionBar.LayoutParams params = new ActionBar.LayoutParams((ActionBar.LayoutParams.MATCH_PARENT));
//        actionBar.setCustomView(mCustomView, params);


        // setNavigation--------------------------------------------------

        // navigation 으로 동작할 화면
        drawerView = (View) findViewById(R.id.drawer);



        // Drawer layout
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Define_menu_click.set_menu_click(getApplicationContext(),dlDrawer,this);

        //actionbar에서 내비 제어할 버튼
        ImageButton btn_menu = (ImageButton) mCustomView.findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dlDrawer.openDrawer(drawerView);
            }
        });

    }


}
