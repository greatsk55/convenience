package kr.badream.convenience.Helper;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.badream.convenience.Menu_View.Activity_Rank;
import kr.badream.convenience.Menu_View.Activity_Search;
import kr.badream.convenience.Menu_View.Activity_compare;
import kr.badream.convenience.Menu_View.Activity_map;
import kr.badream.convenience.Menu_View.Activity_mylike;
import kr.badream.convenience.Menu_View.Activity_myreview;
import kr.badream.convenience.R;

/**
 * Created by Administrator on 2016-06-11.
 */
public class Define_menu_click{

    DrawerLayout drw;

    Button btn_login;
    TextView menu_id;
    TextView menu_review;
    TextView menu_like;
    TextView menu_conv;
    TextView menu_rank;
    TextView menu_item;

    TextView menu_search;

    public Define_menu_click(final Context context , DrawerLayout drw) {

        btn_login = (Button) drw.findViewById(R.id.menu_btn_login);
        menu_id = (TextView) drw.findViewById(R.id.menu_id);
        menu_review = (TextView) drw.findViewById(R.id.menu_review);
        menu_like = (TextView) drw.findViewById(R.id.menu_like);
        menu_conv = (TextView) drw.findViewById(R.id.menu_conv);
        menu_rank = (TextView) drw.findViewById(R.id.menu_rank);
        menu_item = (TextView) drw.findViewById(R.id.menu_item);
        menu_search = (TextView) drw.findViewById(R.id.menu_search);


        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        menu_id.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        menu_review.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.e("ee","ee");
                Intent activity_myreview = new Intent( context , Activity_myreview.class);
                activity_myreview.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(activity_myreview);
            }
        });
        menu_like.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity_mylike = new Intent( context , Activity_mylike.class);
                activity_mylike.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(activity_mylike);
            }
        });
        menu_conv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity_map = new Intent( context , Activity_map.class);
                activity_map.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(activity_map);
            }
        });
        menu_rank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity_Rank = new Intent( context , Activity_Rank.class);
                activity_Rank.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(activity_Rank);
            }
        });
        menu_item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity_compare = new Intent( context , Activity_compare.class);
                activity_compare.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(activity_compare);
            }
        });
        menu_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Helper_search helper_search = new Helper_search();
                helper_search.loadAllItemListWithRetrofit(context, 10, 1);

            }
        });
    }


}
