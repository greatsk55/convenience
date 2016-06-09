package kr.badream.convenience.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import kr.badream.convenience.Adapter.Adapter_list_view;
import kr.badream.convenience.Adapter.Item_list_view;
import kr.badream.convenience.R;

/**
 * Created by Administrator on 2016-06-04.
 */
public class View_item_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ListView listview;
        Adapter_list_view adapter;

        // Adapter 생성
        adapter = new Adapter_list_view();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);



        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.cu),
                "Good", "Account Box Black 36dp");
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.seven),
                "Circle", "Account Circle Black 36dp");
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.with_me),
                "XXX", "Assignment Ind Black 36dp");


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Item_list_view item = (Item_list_view) parent.getItemAtPosition(position) ;

                String titleStr = item.getItem_name() ;
                String descStr = item.getItem_price() ;

                Log.e("abc","abc" + titleStr + descStr);


                // TODO : use item data.

                Intent ctgview_intent = new Intent( getApplicationContext(), Activity_ctgView.class);
                startActivity(ctgview_intent);
            }
        }) ;
    }



}