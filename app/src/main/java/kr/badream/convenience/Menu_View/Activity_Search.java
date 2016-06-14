package kr.badream.convenience.Menu_View;

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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import kr.badream.convenience.Adapter.Item_list_view;
import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.Helper.Helper_itemData;
import kr.badream.convenience.R;
import kr.badream.convenience.View.View_item_info;

public class Activity_Search extends AppCompatActivity {

    View drawerView;
    DrawerLayout dlDrawer;

    private ArrayList<Helper_itemData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setCustomActionbar();

        list = (ArrayList<Helper_itemData>) getIntent().getSerializableExtra("list");

        final ArrayAdapter<Helper_itemData> adapter = new ArrayAdapter<Helper_itemData>
                (this, android.R.layout.simple_dropdown_item_1line, list);

        final AutoCompleteTextView text = (AutoCompleteTextView)
                findViewById(R.id.edit_search);

        text.setAdapter(adapter);
        text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("aaa", "선택된 아이템:"+ parent.getItemAtPosition(position));
                Helper_itemData data = adapter.getItem(position);

                Intent view_item_info = new Intent(getApplicationContext(), View_item_info.class);
                view_item_info.putExtra("list", new Item_list_view(data.url, data.name, data.price, 0, 0, data.storeID));
                startActivity(view_item_info);
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.showDropDown();
            }
        });
        text.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("aaa", "셀렉된 아이템:");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("aaa","아무것도 안 셀렉됨:");
            }
        });
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
        TextView act_title = (TextView) findViewById(R.id.actionbar_title);
        act_title.setText("내가 쓴 리뷰");
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
