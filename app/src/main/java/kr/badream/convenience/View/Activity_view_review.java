package kr.badream.convenience.View;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import kr.badream.convenience.Adapter.Adapter_mini_list_view;
import kr.badream.convenience.Adapter.item_mini_list_view;
import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.Helper.HorizontalListView;
import kr.badream.convenience.R;

public class Activity_view_review extends AppCompatActivity {

    private View drawerView;
    private DrawerLayout dlDrawer;

    Adapter_mini_list_view adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review);

        setCustomActionbar();

        adapter = new Adapter_mini_list_view();

        // 리스트뷰 참조 및 Adapter달기
        HorizontalListView listview = (HorizontalListView) findViewById(R.id.item_list);

//        listview = (ListView) findViewById(R.id.item_list);
        listview.setAdapter(adapter);

        adapter.addItem(0,"","아이템 추가");



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
