package kr.badream.convenience.View;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import kr.badream.convenience.Adapter.Adapter_review_list_view;
import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.Helper.Helper_itemInfo;
import kr.badream.convenience.Helper.Helper_reviewData;
import kr.badream.convenience.Helper.LoginHelper;
import kr.badream.convenience.R;

public class View_item_info extends AppCompatActivity {

    View drawerView;
    DrawerLayout dlDrawer;

    ListView listview;
    Adapter_review_list_view adapter;

    ImageView info_image;
    TextView info_price;
    Button info_btn_like;
    Button info_btn_review_write;
    TextView info_review_number;

    private Helper_itemInfo list;

    boolean isLogin;


    @Override
    protected void onResume() {
        super.onResume();
        setCustomActionbar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        // 레이아웃 설정
        info_image = (ImageView) findViewById(R.id.info_image) ;
        info_price = (TextView) findViewById(R.id.info_price) ;
        info_btn_like = (Button) findViewById(R.id.info_btn_like) ;
        info_btn_review_write = (Button) findViewById(R.id.info_btn_review_write) ;
        info_review_number = (TextView) findViewById(R.id.info_review_number) ;

//        list = (Helper_itemInfo) getIntent().getSerializableExtra("list");

        //로그인 체크
        if(LoginHelper.isLogin(getApplicationContext()))    isLogin = true;
        else isLogin = false;

        // Adapter 생성
        adapter = new Adapter_review_list_view();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.review_list);
        listview.setAdapter(adapter);

        final Helper_itemInfo item = (Helper_itemInfo) getIntent().getSerializableExtra("item_info");
        Log.e("item0","item"+item.name);

        Glide.with(getApplicationContext()).load(item.url).into(info_image);
        info_price.setText(item.price);
        info_btn_like.setText(""+item.likes);
        info_review_number.setText(""+item.reviews);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Helper_reviewData item = (Helper_reviewData) parent.getItemAtPosition(position);

            }
        });

        info_btn_like.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isLogin){
                    Helper_server.setLikedWithRetrofit(View_item_info.this, LoginHelper.getUserID(getApplicationContext()), item.prodID);
                }
                else{
                    LoginHelper.openLoginActivity(View_item_info.this);
                    Toast.makeText(getApplicationContext(),"로그인 하셔야 합니다.", Toast.LENGTH_LONG).show();
                }

            }
        });
        info_btn_review_write.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isLogin){
                    //모든카테고리 list 로 부르기
                    Helper_server.getAllItemList_to_register_review(View_item_info.this, LoginHelper.getUserID(getApplicationContext()), 10, 0);
                    Intent activity_register_review = new Intent(getApplicationContext(), Activity_register_review.class);
                    activity_register_review.putExtra("list", list);
                    startActivity(activity_register_review);
                }
                else{
                    LoginHelper.openLoginActivity(View_item_info.this);
                    Toast.makeText(getApplicationContext(),"로그인 하셔야 합니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

        setCustomActionbar();
        TextView act_title = (TextView) findViewById(R.id.actionbar_title);
        act_title.setText(item.name);
    }

    private void setCustomActionbar() {

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
