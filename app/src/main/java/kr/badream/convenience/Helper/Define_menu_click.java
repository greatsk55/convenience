package kr.badream.convenience.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

    public static final int ALLCATEGORY=10;

    public static void set_menu_click(final Context context , DrawerLayout drw, final Activity activity) {

        Button btn_login;
        TextView menu_id;
        TextView menu_review;
        TextView menu_like;
        TextView menu_conv;
        TextView menu_rank;
        TextView menu_item;

        TextView menu_search;

        LinearLayout id_layout;

        btn_login = (Button) drw.findViewById(R.id.menu_btn_login);
        menu_id = (TextView) drw.findViewById(R.id.menu_id);
        menu_review = (TextView) drw.findViewById(R.id.menu_review);
        menu_like = (TextView) drw.findViewById(R.id.menu_like);
        menu_conv = (TextView) drw.findViewById(R.id.menu_conv);
        menu_rank = (TextView) drw.findViewById(R.id.menu_rank);
        menu_item = (TextView) drw.findViewById(R.id.menu_item);
        menu_search = (TextView) drw.findViewById(R.id.menu_search);
        id_layout = (LinearLayout) drw.findViewById(R.id.id_layout);

        if(LoginHelper.isLogin(context)){
            id_layout.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.GONE);
            menu_id.setText(LoginHelper.getUserName(context));
        }
        else{
            id_layout.setVisibility(View.GONE);
            btn_login.setVisibility(View.VISIBLE);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginHelper.openLoginActivity(activity);
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
                activity_myreview.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity_myreview.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(activity_myreview);
            }
        });
        menu_like.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity_mylike = new Intent( context , Activity_mylike.class);
                activity_mylike.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity_mylike.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(activity_mylike);
            }
        });
        menu_conv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Helper_server.loadMapListWithRetrofit( activity , ALLCATEGORY);
            }
        });
        menu_rank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity_Rank = new Intent( context , Activity_Rank.class);
                activity_Rank.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity_Rank.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(activity_Rank);
            }
        });
        menu_item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity_compare = new Intent( context , Activity_compare.class);
                activity_compare.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity_compare.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(activity_compare);
            }
        });
        menu_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Helper_server.loadAllItemListWithRetrofit( activity, LoginHelper.getUserID(context), ALLCATEGORY, 0);
            }
        });
    }


}
