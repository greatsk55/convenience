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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.badream.convenience.Adapter.Adapter_mini_list_view;
import kr.badream.convenience.Adapter.item_mini_list_view;
import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.Helper.Helper_itemData;
import kr.badream.convenience.Helper.Helper_server;
import kr.badream.convenience.Helper.HorizontalListView;
import kr.badream.convenience.Helper.LoginHelper;
import kr.badream.convenience.R;

public class Activity_register_review extends AppCompatActivity {

    private View drawerView;
    private DrawerLayout dlDrawer;
    private Button btn_register;

    TextView item_price;
    EditText edit_contents;

    //TODO 서버로 보낼 데이터
    String prodID;
    int userID;
    String userName;
    String contents;
    String total_price;

    private ArrayList<Helper_itemData> list;

    //    ListView listview;
    Adapter_mini_list_view adapter;

    @Override
    protected void onResume() {
        super.onResume();
        setCustomActionbar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_review);

        btn_register = (Button) findViewById(R.id.btn_register);
        item_price = (TextView) findViewById(R.id.item_price);
        edit_contents = (EditText) findViewById(R.id.edit_contents);

        adapter = new Adapter_mini_list_view();

        // 리스트뷰 참조 및 Adapter달기
        HorizontalListView listview = (HorizontalListView) findViewById(R.id.item_list);

//        listview = (ListView) findViewById(R.id.item_list);
        listview.setAdapter(adapter);
        adapter.addItem(0,"","아이템 추가");


        //serach
        list = (ArrayList<Helper_itemData>) getIntent().getSerializableExtra("list");

        // 서버로 보낼 데이터 초기화
        prodID = "";
        userID = 0;
        userName = "";
        contents = "";
        total_price = "";


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
                    aDialog.setNegativeButton("닫기",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 'No'
                                    return;
                                }
                            });
                    AlertDialog ad = aDialog.create();

                    //search
                    setSearch(layout,ad);
                    ad.show();
                }


            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO 리뷰 등록

                userID = LoginHelper.getUserID(getApplicationContext());
                userName = LoginHelper.getUserName(getApplicationContext());
                contents = "" + edit_contents.getText();
                Log.e("print register", " = " + userID + " " + userName + " " + prodID + " "+ total_price + " " + contents );

                Helper_server.postReviewWithRetrofit(Activity_register_review.this, userID, userName, prodID, total_price, contents);
            }
        });

        setCustomActionbar();

    }


    private  void setSearch(View layout, final AlertDialog ad){

        final ArrayAdapter<Helper_itemData> add_adapter = new ArrayAdapter<Helper_itemData>
                (this, android.R.layout.simple_dropdown_item_1line, list);

        final AutoCompleteTextView text = (AutoCompleteTextView) layout.findViewById(R.id.edit_search);

        text.setAdapter(add_adapter);
        text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("aaa", "선택된 아이템:?!?!?"+ parent.getItemAtPosition(position));
                Helper_itemData data = add_adapter.getItem(position);

                //TODO 검색된 아이템을 adapter 에 추가해주면 된다.
                adapter.addItem(1,data.url,data.name);

                // -원 이랑 , 때어내기
                String p1 = (""+item_price.getText());

                if(p1.contains("원"))
                    p1 = p1.substring(0, p1.indexOf("원"));


                int price = Integer.parseInt(""+p1);

                // -원 이랑 , 때어내기
                String p2 = data.price.substring(0,data.price.indexOf("원"));
                p2 = p2.replace(",","");

                price += Integer.parseInt(p2);
                item_price.setText(price+"원");

                //TODO 물품 아이디 추가
                prodID += data.prodID+",";
                total_price = price+"원";

                ad.dismiss();
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("aasa","::"+text.length() + add_adapter.getCount());
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
