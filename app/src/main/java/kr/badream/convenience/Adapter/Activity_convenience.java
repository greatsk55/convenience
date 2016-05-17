package kr.badream.convenience.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import kr.badream.convenience.R;

/**
 * Created by Administrator on 2016-05-17.
 */
public class Activity_convenience extends LinearLayout {

    public Activity_convenience(Context context) {
        super(context);
        init();
    }

    private void init(){

        final View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_main,null);
        view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

        this.addView(view);
    }

    /*
    public void logoutAlert() {
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(getContext());
        alert.setTitle("로그아웃");
        alert.setMessage("로그아웃 하겠습니까?");
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AsyncHttpClient client = Helper_server.getInstance();
                final PersistentCookieStore myCookieStore = new PersistentCookieStore(getContext()); //이부분 Context 확인해야함. Activity context로.
                Helper_server.logout(myCookieStore, getContext());
                client.setCookieStore(myCookieStore);

                Intent intent = new Intent(getContext(), Activity_login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                getContext().startActivity(intent);


            }
        });
        alert.show();

    }*/
}
