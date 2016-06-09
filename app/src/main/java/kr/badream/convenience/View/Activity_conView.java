package kr.badream.convenience.View;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import kr.badream.convenience.R;

public class Activity_conView extends AppCompatActivity {

    ImageView cu ;
    ImageView gs25 ;
    ImageView mini_stop ;
    ImageView seven ;
    ImageView with_me ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cu = (ImageView) findViewById(R.id.cu_image);
        gs25 = (ImageView) findViewById(R.id.ge25_image);
        mini_stop = (ImageView) findViewById(R.id.ministop_image);
        seven = (ImageView) findViewById(R.id.seven_image);
        with_me = (ImageView) findViewById(R.id.withme_image);

        cu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ctgview_intent = new Intent( Activity_conView.this , Activity_ctgView.class);
                startActivity(ctgview_intent);
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
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.activity_main_background));

        //set actionbar layout layoutparams
        ActionBar.LayoutParams params = new ActionBar.LayoutParams((ActionBar.LayoutParams.MATCH_PARENT));
        actionBar.setCustomView(mCustomView, params);
    }


}
