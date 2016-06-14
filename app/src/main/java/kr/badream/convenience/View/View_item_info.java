package kr.badream.convenience.View;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.badream.convenience.Adapter.Adapter_list_view;
import kr.badream.convenience.Adapter.Adapter_review_list_view;
import kr.badream.convenience.Adapter.Item_list_view;
import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.Helper.Helper_itemData;
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

    boolean isLogin;

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

        //로그인 체크
        if(LoginHelper.isLogin(getApplicationContext()))    isLogin = true;
        else isLogin = false;

        // Adapter 생성
        adapter = new Adapter_review_list_view();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.review_list);
        listview.setAdapter(adapter);

        Item_list_view item = (Item_list_view) getIntent().getSerializableExtra("list");
        Log.e("item0","item"+item.getItem_name());

        Glide.with(getApplicationContext()).load(item.getMain_image()).into(info_image);
        info_price.setText(item.getItem_price());
        info_btn_like.setText(""+item.getItem_like_number());
        info_review_number.setText(""+item.getItem_review_number());


        info_btn_like.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (info_btn_like.isSelected()){
                    info_btn_like.setSelected(false);
                }
                else{
                    info_btn_like.setSelected(true);
                }

            }
        });
        info_btn_review_write.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isLogin){
                    Intent activity_register_review = new Intent(getApplicationContext(), Activity_register_review.class);
                    startActivity(activity_register_review);
                }
            }
        });

        setCustomActionbar();
        TextView act_title = (TextView) findViewById(R.id.actionbar_title);
        act_title.setText(item.getItem_name());
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
        new Define_menu_click(getApplicationContext(),dlDrawer,this);

        //actionbar에서 내비 제어할 버튼
        ImageButton btn_menu = (ImageButton) mCustomView.findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dlDrawer.openDrawer(drawerView);
            }
        });

    }
}
