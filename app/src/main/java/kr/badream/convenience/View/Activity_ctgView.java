package kr.badream.convenience.View;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.R;

public class Activity_ctgView extends AppCompatActivity {

    ImageView ctg_1;
    ImageView ctg_2;
    ImageView ctg_3;
    ImageView ctg_4;
    ImageView ctg_5;
    ImageView ctg_6;

    View drawerView;
    DrawerLayout dlDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctgview);


        ctg_1 = (ImageView) findViewById(R.id.ctg_1);
        ctg_2 = (ImageView) findViewById(R.id.ctg_2);
        ctg_3 = (ImageView) findViewById(R.id.ctg_3);
        ctg_4 = (ImageView) findViewById(R.id.ctg_4);
        ctg_5 = (ImageView) findViewById(R.id.ctg_5);
        ctg_6 = (ImageView) findViewById(R.id.ctg_6);

        ctg_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent view_item_list = new Intent( getApplicationContext(), View_item_list.class);
                startActivity(view_item_list);
            }
        });

        setCustomActionbar();

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
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.activity_layout_background));
        TextView act_title = (TextView) findViewById(R.id.actionbar_title);
        act_title.setText("카테고리 선택");

        //set actionbar layout layoutparams
        ActionBar.LayoutParams params = new ActionBar.LayoutParams((ActionBar.LayoutParams.MATCH_PARENT));
        actionBar.setCustomView(mCustomView, params);

        // navigation 으로 동작할 화면
        drawerView = (View) findViewById(R.id.drawer);

        // Drawer layout
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        new Define_menu_click(getApplicationContext(),dlDrawer);

        //actionbar에서 내비 제어할 버튼
        ImageButton btn_menu = (ImageButton) mCustomView.findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dlDrawer.openDrawer(drawerView);
            }
        });
    }
}
