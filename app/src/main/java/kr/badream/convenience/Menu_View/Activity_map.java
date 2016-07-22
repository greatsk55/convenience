package kr.badream.convenience.Menu_View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import kr.badream.convenience.Adapter.Adapter_review_list_view;
import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.Helper.Helper_mapData;
import kr.badream.convenience.R;

public class Activity_map extends ActionBarActivity implements LocationListener {

    static final LatLng SEOUL = new LatLng( 37.56, 126.97);
    private GoogleMap map;

    private LocationManager locationManager;
    private String provider;

    public static List<Helper_mapData> map_data;

    View drawerView;
    DrawerLayout dlDrawer;

    @Override
    protected void onResume() {
        super.onResume();
        setCustomActionbar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        GooglePlayServicesUtil.isGooglePlayServicesAvailable(Activity_map.this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);



        if (provider == null) {  //위치정보 설정이 안되어 있으면 설정하는 엑티비티로 이동합니다
            new AlertDialog.Builder(Activity_map.this)
                    .setTitle("위치서비스 동의")
                    .setNeutralButton("이동", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
                        }
                    }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();
                }
            })
                    .show();
        } else {   //위치 정보 설정이 되어 있으면 현재위치를 받아옵니다
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(provider, 1, 1, Activity_map.this);
            setUpMapIfNeeded();
        }

//
        for( Helper_mapData data : map_data){
            LatLng position = new LatLng(Double.parseDouble(data.latitude), Double.parseDouble(data.longitude));

            switch( data.storeID ){
                case 1 :
                    map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.mini_cu)).position(position).title("cu " + data.storeName));
                    break;
                case 2 :
                    map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.mini_gs25)).position(position).title("gs25 " + data.storeName));
                    break;
            }

        }

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.6082699933, 126.999125684),7));
        map.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);
        //map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        setCustomActionbar();

    }

    private void setUpMapIfNeeded() {
        if (map == null) {
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap(); if (map != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        map.getMyLocation();

    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        Log.e("현재위치","gg = " + lat + " " + lng);
       // map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.6082699933, 126.999125684),15));


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void setCustomActionbar() {

        //0.1 0.06

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
        TextView act_title = (TextView) findViewById(R.id.actionbar_title);
        act_title.setText("우리 집 편의점");
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
