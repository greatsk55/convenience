package kr.badream.convenience.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import kr.badream.convenience.Adapter.Adapter_mini_list_view;
import kr.badream.convenience.Adapter.Item_list_view;
import kr.badream.convenience.Adapter.item_mini_list_view;
import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.Helper.HorizontalListView;
import kr.badream.convenience.R;

public class Activity_register_review extends AppCompatActivity {

    private View drawerView;
    private DrawerLayout dlDrawer;
    private Button btn_register;


//    ListView listview;
    Adapter_mini_list_view adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_review);

        btn_register = (Button) findViewById(R.id.btn_register);

        adapter = new Adapter_mini_list_view();

        // 리스트뷰 참조 및 Adapter달기
        HorizontalListView listview = (HorizontalListView) findViewById(R.id.item_list);

//        listview = (ListView) findViewById(R.id.item_list);
        listview.setAdapter(adapter);
        adapter.addItem(0,"","아이템 추가");

//        adapter.addItem("","쿵쿵따");
//        adapter.addItem("","쿵쿵따");
//        adapter.addItem("","쿵쿵따");
//        adapter.addItem("","쿵쿵따");
//        adapter.addItem("","쿵쿵따");
//        adapter.addItem("","쿵쿵따");
//        adapter.addItem("","쿵쿵따");
//        adapter.addItem("","쿵쿵따");
//        adapter.addItem("","쿵쿵따");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                item_mini_list_view item = (item_mini_list_view) parent.getItemAtPosition(position);

                if(item.getItem_name().contains("아이템 추가"));{
                    LayoutInflater inflater = (LayoutInflater) Activity_register_review.this.getSystemService(Activity_register_review.this.LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.dialog_add_item, (ViewGroup) findViewById(R.id.popup));
                    AlertDialog.Builder aDialog = new AlertDialog.Builder(Activity_register_review.this);
                    aDialog.setTitle("상품"); //타이틀바 제목
                    aDialog.setView(layout); //dialog.xml 파일을 뷰로 셋팅
                    aDialog.setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //TODO 검색된 아이템을 adapter 에 추가해주면 된다.
                                    //adapter.additem();
                                    //adapter.notifyDataSetChanged();
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


            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO 아이템 등록
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
        act_title.setText("리뷰 등록하기");

        //set actionbar layout layoutparams
        ActionBar.LayoutParams params = new ActionBar.LayoutParams((ActionBar.LayoutParams.MATCH_PARENT));
        actionBar.setCustomView(mCustomView, params);

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
