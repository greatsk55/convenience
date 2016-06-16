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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import kr.badream.convenience.Adapter.Adapter_list_view;
import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.Helper.Helper_itemData;
import kr.badream.convenience.Helper.Helper_itemInfo;
import kr.badream.convenience.Helper.Helper_server;
import kr.badream.convenience.Helper.Helper_sort;
import kr.badream.convenience.Helper.LoginHelper;
import kr.badream.convenience.R;

/**
 * Created by Administrator on 2016-06-04.
 */

//TODO 소스 정리 해야할듯
public class View_item_list extends AppCompatActivity {

    public static final int REQUEST_LIST = 100;

    public final int ALL_SUBCATEGORY=0;
    public final int JUMUKBAP=1;
    public final int DOSIRAK=2;
    public final int HAMBURGER=3;
    public final int GIMBAP=4;
    public final int SANDWICH=5;
    public final int ETC=6;

    private View drawerView;
    private DrawerLayout dlDrawer;

    private ArrayList<Helper_itemData> list;

    ListView listview;
    Adapter_list_view adapter;

    TextView btn_ctg_item;
    TextView btn_ctg_array;

    RadioGroup rd;
    RadioButton r0;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;
    RadioButton r5;
    RadioButton r6;

    int state_ctg_item;
    int state_array_item;

    int mainCtg;
    int storeID;
    int listIndex;

    @Override
    protected void onResume() {
        super.onResume();
        setCustomActionbar();
        listview.setAdapter(adapter);

    }

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
        mainCtg = getIntent().getIntExtra("ctg", -1);
        storeID = getIntent().getIntExtra("storeID", -1);
        listIndex = getIntent().getIntExtra("listIndex", 0);

        // 1.이미지, 2.물품이름, 3.가격, 4.좋아요수, 5.리뷰수 6.편의점 이미지
        for( Helper_itemData data : list){
            adapter.addItem(data.prodID,data.url, data.name, data.price, data.likes, data.reviews, data.storeID);
        }

        state_ctg_item = 0;
        state_array_item = 0;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Helper_itemInfo item = (Helper_itemInfo) parent.getItemAtPosition(position);

                String titleStr = item.name;
                String descStr = item.price;

                Helper_server.loadItemInfoListWithRetrofit(View_item_list.this, LoginHelper.getUserID(getApplicationContext()), item.prodID);
            }
        });



        btn_ctg_item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.e("mainCtg", " = " + mainCtg);
                //cu 즉석 식품
                if(mainCtg == 3 && storeID == 1) {

                    LayoutInflater inflater = (LayoutInflater) View_item_list.this.getSystemService(View_item_list.this.LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.dialog_radio, (ViewGroup) findViewById(R.id.popup));
                    AlertDialog.Builder aDialog = new AlertDialog.Builder(View_item_list.this);

                    r0 = (RadioButton) layout.findViewById(R.id.radio0);
                    r1 = (RadioButton) layout.findViewById(R.id.radio1);
                    r2 = (RadioButton) layout.findViewById(R.id.radio2);
                    r3 = (RadioButton) layout.findViewById(R.id.radio3);
                    r4 = (RadioButton) layout.findViewById(R.id.radio4);
                    r5 = (RadioButton) layout.findViewById(R.id.radio5);
                    r6 = (RadioButton) layout.findViewById(R.id.radio6);

                    aDialog.setTitle("상품"); //타이틀바 제목
                    aDialog.setView(layout); //dialog.xml 파일을 뷰로 셋팅
                    aDialog.setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (r0.isChecked()) {
                                        setAdapter(ALL_SUBCATEGORY, state_array_item);
                                        state_ctg_item = 0;
                                    } else if (r1.isChecked()) {
                                        setAdapter(JUMUKBAP, state_array_item);
                                        state_ctg_item = 1;
                                    } else if (r2.isChecked()) {
                                        setAdapter(DOSIRAK, state_array_item);
                                        state_ctg_item = 2;
                                    } else if (r3.isChecked()) {
                                        setAdapter(HAMBURGER, state_array_item);
                                        state_ctg_item = 3;
                                    } else if (r4.isChecked()) {
                                        setAdapter(GIMBAP, state_array_item);
                                        state_ctg_item = 4;
                                    } else if (r5.isChecked()) {
                                        setAdapter(SANDWICH, state_array_item);
                                        state_ctg_item = 5;
                                    } else if (r6.isChecked()) {
                                        setAdapter(ETC, state_array_item);
                                        state_ctg_item = 6;
                                    }
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
        btn_ctg_array.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) View_item_list.this.getSystemService(View_item_list.this.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.dialog_array,(ViewGroup) findViewById(R.id.popup));
                AlertDialog.Builder aDialog = new AlertDialog.Builder(View_item_list.this);

                r0 = (RadioButton) layout.findViewById(R.id.radio0);
                r1 = (RadioButton) layout.findViewById(R.id.radio1);
                r2 = (RadioButton) layout.findViewById(R.id.radio2);

                aDialog.setTitle("상품"); //타이틀바 제목
                aDialog.setView(layout); //dialog.xml 파일을 뷰로 셋팅
                aDialog.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (r0.isChecked()) {
                                    setAdapter(state_ctg_item, 0);
                                    state_array_item = 0;
                                } else if (r1.isChecked()) {
                                    setAdapter(state_ctg_item, 1);
                                    state_array_item = 1;
                                } else if (r2.isChecked()) {
                                    setAdapter(state_ctg_item, 2);
                                    state_array_item = 2;
                                }
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

        listview.requestFocusFromTouch();
        adapter.notifyDataSetChanged();
        listview.setSelection(listIndex);

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
        //TODO Deprecated 수정하기
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

    private void setAdapter(int item, int array){
        adapter.clear();
        switch (item){
            //전체 보기
            case ALL_SUBCATEGORY:
                for( Helper_itemData data : list){
                    adapter.addItem(data.prodID,data.url, data.name, data.price, 0, 0, data.storeID);
                }
                if(array == 1){
                    Collections.sort(list, Helper_sort.comparator_Item_list_view_review);
                }
                break;
            //주먹밥
            case JUMUKBAP:
                for( Helper_itemData data : list){
                    if(data.name.charAt(0)=='주'){
                        adapter.addItem(data.prodID,data.url, data.name, data.price, 0, 0, data.storeID);
                    }
                }
                if(array == 1){
                    Collections.sort(list, Helper_sort.comparator_Item_list_view_review);
                }

                break;
            //도시락
            case DOSIRAK:
                for( Helper_itemData data : list){
                    if(data.name.charAt(0)=='도' && !data.name.contains("도너츠")){
                        adapter.addItem(data.prodID,data.url, data.name, data.price, 0, 0, data.storeID);
                    }
                }
                if(array == 1){
                    Collections.sort(list, Helper_sort.comparator_Item_list_view_review);
                }

                break;
            //햄버거
            case HAMBURGER:
                if(array == 0)
                    for( Helper_itemData data : list){
                        if(data.name.charAt(0)=='햄'){
                            adapter.addItem(data.prodID,data.url, data.name, data.price, 0, 0, data.storeID);
                        }
                    }
                break;
            //김밥
            case GIMBAP:
                if(array == 0)
                    for( Helper_itemData data : list){
                        if(data.name.charAt(0)=='김'){
                            adapter.addItem(data.prodID,data.url, data.name, data.price, 0, 0, data.storeID);
                        }
                    }
                break;
            //샌드위치
            case SANDWICH:
                if(array == 0)
                    for( Helper_itemData data : list){
                        if(data.name.charAt(0)=='샌'){
                            adapter.addItem(data.prodID,data.url, data.name, data.price, 0, 0, data.storeID);
                        }
                    }
                break;
            //나머지
            case ETC:
                if(array == 0)
                    for( Helper_itemData data : list){
                        if(data.name.charAt(0)!='주' && data.name.charAt(0) !='샌' && data.name.charAt(0)!='김'&& (data.name.charAt(0)!='도'|| data.name.contains("도너츠")) && data.name.charAt(0)!='햄'){
                            adapter.addItem(data.prodID,data.url, data.name, data.price, 0, 0, data.storeID);
                        }
                    }
                break;
        }


        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);


        listIndex = listview.getFirstVisiblePosition();
        Helper_server.loadStoreCategoryListWithRetrofit(this, LoginHelper.getUserID(getApplicationContext()), storeID, mainCtg, listIndex);
        finish();

    }
}