package kr.badream.convenience.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import kr.badream.convenience.R;

/**
 * Created by Administrator on 2016-05-11.
 */
public class Activity_myself extends LinearLayout {

    Context mContext;

    ImageView myFace;
    Button logout_btn;
    Button chage_btn;
    Button edit_btn;

    EditText edit_phone;
    EditText edit_email;
    EditText edit_facebook;
    EditText edit_kakaotalk;

    TextView myname;

    TextView text_phone;
    TextView text_email;
    TextView text_facebook;
    TextView text_kakaotalk;


    int check_host_user = 0;

    public Activity_myself(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init(){

  //      final Activity_user_view aActivity = (Activity_user_view) Activity_user_view.AActivty;


        final View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_myself,null);
        view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));


        logout_btn = (Button) view.findViewById(R.id.logout);
        edit_btn = (Button) view.findViewById(R.id.editbutton);

        myname = (TextView) view.findViewById(R.id.myname);


        //Log.e("check :", "0=" + Helper_server.userData.getName());

//        if(Helper_server.userData.getId() != null )
//            myname.setText(Helper_server.userData.getId() + "(" + Helper_server.userData.getName() + ")" );
//        else
//            myname.setText("(" + Helper_server.userData.getName() + ")" );
//
//        text_phone.setText(Helper_server.userData.getPhoneNumber());
//
//        if( Helper_server.userData.getEmail() == null ) text_email.setText("없음");
//        else                                            text_email.setText(Helper_server.userData.getEmail());
//
//        if( Helper_server.userData.getFacebook() == null ) text_facebook.setText("없음");
//        else                                               text_facebook.setText(Helper_server.userData.getFacebook());
//
//        if( Helper_server.userData.getKakaotalk() == null ) text_kakaotalk.setText("없음");
//        else                                                text_kakaotalk.setText(Helper_server.userData.getKakaotalk());

        String str = "" + getContext().getClass();

        logout_btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.logout) {
                    // Set an EditText view to get user input
                    //로그아웃파트.
                    logoutAlert();
                    //여기에 로그아웃 됬다는 말과 함께 로그인 화면으로 이동시켜 주어야 함.
                }
            }
        });


//        edit_btn.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                if (v.getId() == R.id.editbutton) {
//                    Context mContext = getContext().getApplicationContext();
//                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
//
//                    //R.layout.dialog는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
//                    View layout = inflater.inflate(R.layout.dialog_edit,(ViewGroup) findViewById(R.id.popup));
//                    AlertDialog.Builder aDialog = new AlertDialog.Builder(getContext());
//
//                    aDialog.setTitle("내 정보 수정하기"); //타이틀바 제목
//                    aDialog.setView(layout); //dialog.xml 파일을 뷰로 셋팅
//
//
//                    edit_phone = (EditText) layout.findViewById(R.id.edit_phone);
//                    edit_email = (EditText) layout.findViewById(R.id.edit_email);
//                    edit_facebook = (EditText) layout.findViewById(R.id.edit_facebook);
//                    edit_kakaotalk = (EditText) layout.findViewById(R.id.edit_kakao);
//
//                    edit_phone.setHint(text_phone.getText());
//                    edit_email.setHint(text_email.getText());
//                    edit_facebook.setHint(text_facebook.getText());
//                    edit_kakaotalk.setHint(text_kakaotalk.getText());
//
//                    aDialog.setPositiveButton("확인",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    final RequestParams idParams = new RequestParams("id", Helper_server.userData.getUserID());
//                                    int userID = Helper_userData.getInstance().getUserID();
//                                    final String phone = edit_phone.getText().toString();
//                                    final String email = edit_email.getText().toString();
//                                    Log.d("aaaaaa",""+userID);
//                                    Log.d("aaaaaa",email);
//
//                                    //알림 뜨게 추가.
//                                    if(phone.equals(""))
//                                        return;
//                                    if(email.equals(""))
//                                        return;
//                                    idParams.put("userID", userID);
//                                    idParams.put("phone", phone);
//                                    idParams.put("email", email);
//                                    //TODO 카톡이랑 페이스북 아이디 넣기
//
//                                    Helper_server.post("editmyself.php", idParams, new AsyncHttpResponseHandler() {
//                                        @Override
//                                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                                            Log.i("abde", "why");
//                                            text_phone.setText(edit_phone.getText());
//                                            text_email.setText(edit_email.getText());
//                                            Helper_userData.getInstance().setPhoneNumber(phone);
//                                            Helper_userData.getInstance().setEmail(email);
//                                        }
//
//                                        @Override
//                                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                                            Log.i("abde", "fail");
//                                        }
//                                    });
//                                    // 'YES'
//                                    //Log.e("edit_phone.getText()",""+edit_phone.getText().toString());
//
//
//                                }
//                            }).setNegativeButton("취소",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // 'No'
//                                    return;
//                                }
//                            });
//                    AlertDialog ad = aDialog.create();
//                    //ad.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                    //ad.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                    ad.show();//보여줌!
//
//
//                }
//            }
//        });
        this.addView(view);
    }

    public Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        int size = (bitmap.getWidth()/2);
        canvas.drawCircle(size, size, size, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public void logoutAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("로그아웃");
        alert.setMessage("로그아웃 하겠습니까?");
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                AsyncHttpClient client = Helper_server.getInstance();
//                final PersistentCookieStore myCookieStore = new PersistentCookieStore(getContext()); //이부분 Context 확인해야함. Activity context로.
//                Helper_server.logout(myCookieStore, getContext());
//                client.setCookieStore(myCookieStore);
//
//                Intent intent = new Intent(getContext(), Activity_login.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                getContext().startActivity(intent);
            }
        });
        alert.show();

    }
    public void setCheck_host_user(int n){
        check_host_user = n;
    }
}