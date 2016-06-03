package kr.badream.convenience.View;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import kr.badream.convenience.R;

public class Activity_ctgview extends AppCompatActivity {

    ImageView ctg_1;
    ImageView ctg_2;
    ImageView ctg_3;
    ImageView ctg_4;
    ImageView ctg_5;
    ImageView ctg_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctgview);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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

    }
}
