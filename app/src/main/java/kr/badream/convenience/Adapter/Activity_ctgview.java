package kr.badream.convenience.Adapter;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.badream.convenience.R;

public class Activity_ctgview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctgview);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
