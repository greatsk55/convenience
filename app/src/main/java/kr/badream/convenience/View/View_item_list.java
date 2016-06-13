package kr.badream.convenience.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import kr.badream.convenience.Adapter.Adapter_list_view;
import kr.badream.convenience.Adapter.Item_list_view;
import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.Helper.Helper_itemData;
import kr.badream.convenience.R;

/**
 * Created by Administrator on 2016-06-04.
 */
public class View_item_list extends AppCompatActivity {


    private View drawerView;
    private DrawerLayout dlDrawer;

    private ArrayList<Helper_itemData> list;

    ListView listview;
    Adapter_list_view adapter;

    TextView btn_ctg_item;
    TextView btn_ctg_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        // Adapter 생성
        adapter = new Adapter_list_view();
        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.item_list);
        listview.setAdapter(adapter);

        // 상위 정렬 버튼
        btn_ctg_item = (TextView) findViewById(R.id.btn_ctg_item);
        btn_ctg_array = (TextView) findViewById(R.id.btn_ctg_array);

        btn_ctg_item.setClickable(true);

        list = (ArrayList<Helper_itemData>) getIntent().getSerializableExtra("list");

        // 1.이미지, 2.물품이름, 3.가격, 4.좋아요수, 5.리뷰수
        for( Helper_itemData data : list){
            adapter.addItem(data.url, data.name, data.price, 0, 0);
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Item_list_view item = (Item_list_view) parent.getItemAtPosition(position);

                String titleStr = item.getItem_name();
                String descStr = item.getItem_price();

                Log.e("abc", "abc" + titleStr + descStr);


                // TODO : use item data.

                Intent view_item_info = new Intent(getApplicationContext(), View_item_info.class);
                view_item_info.putExtra("list", item);

                startActivity(view_item_info);
            }
        });

        btn_ctg_item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) View_item_list.this.getSystemService(View_item_list.this.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.dialog_radio,(ViewGroup) findViewById(R.id.popup));
                AlertDialog.Builder aDialog = new AlertDialog.Builder(View_item_list.this);

                Log.e("gg","ggok");
                aDialog.setTitle("상품"); //타이틀바 제목
                aDialog.setView(layout); //dialog.xml 파일을 뷰로 셋팅
                aDialog.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'No'
                                return;
                            }
                        });
                AlertDialog ad = aDialog.create();

                ad.show();
            }
        });
        btn_ctg_array.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        setCustomActionbar();
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
        new Define_menu_click(getApplicationContext(),dlDrawer);
        TextView act_title = (TextView) findViewById(R.id.actionbar_title);
        act_title.setText("상품 목록");

        //actionbar에서 내비 제어할 버튼
        ImageButton btn_menu = (ImageButton) mCustomView.findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dlDrawer.openDrawer(drawerView);
            }
        });


    }
}