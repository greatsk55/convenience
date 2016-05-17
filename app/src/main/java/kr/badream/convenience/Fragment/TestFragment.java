package kr.badream.convenience.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import kr.badream.convenience.R;


public final class TestFragment extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";

    final int Convinience = 0;
    final int List = 1;
    final int MyInformation = 2;

    private View view;
    private View view_register;
    private View info;

    public RecyclerView recyclerView;
    public RecyclerView recyclerView_register;

    public static TestFragment newInstance(String content) {
        TestFragment fragment = new TestFragment();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = content;

        return fragment;
    }

    private String mContent = "???";

    private void setAdapterView(LayoutInflater inflater, ViewGroup container, int cases){
        view = inflater.inflate(R.layout.activity_list, container, false);
        //info = inflater.inflate(R.layout.activity_myself,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);

        //Helper_roomData first = new Helper_roomData();
        //Helper_roomData second = new Helper_roomData();
       // Helper_roomData third = new Helper_roomData();
       // first.roomname ="방 화면 예시 1";
     //   second.roomname = "방 화면 예시 2";
   //     second.roomimage = R.drawable.room2;
 //       third.roomimage = R.drawable.room3;
//        third.roomname = "방 화면 예시 3";

//        ArrayList<Helper_roomData> se = new ArrayList<Helper_roomData>();
//        se.add(first);
//        se.add(second);
//        se.add(third);


        //Helper_userData user = new Helper_userData();

        switch (cases){
            case List:
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                //Adapter = new Adapter(getActivity(),
                //        se,
                //        (LinearLayoutManager) recyclerView.getLayoutManager());
                //recyclerView.setAdapter(reservationAdapter);
                break;
        }
        return ;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(mContent.equalsIgnoreCase("a")){ //Ignore Lower Upper case
            //setAdapterView(inflater, container, ROOMLIST);
          //  LinearLayout layout = new LinearLayout(getActivity());
          //  layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
         //   layout.setGravity(Gravity.CENTER);

            //Activity l =  (Activity) new Activity_roomList();
            //LinearLayout layout = (LinearLayout) l.findViewById(R.id.roomlist);

            //return inflater.inflate(R.layout.activity_list, container, false);
            setAdapterView(inflater, container, Convinience);
            return view;
        }
        else if(mContent.equalsIgnoreCase("b")){
            setAdapterView(inflater, container, List);
            return view;
        }
        else if(mContent.equalsIgnoreCase("c")){
           // TabView_myself tabView_myself = new TabView_myself(getContext());
           // return tabView_myself;

        }else {

            TextView text = new TextView(getActivity());
            text.setGravity(Gravity.CENTER);
            text.setText(mContent);
            text.setTextSize(20 * getResources().getDisplayMetrics().density);
            text.setPadding(20, 20, 20, 20);

            LinearLayout layout = new LinearLayout(getActivity());
            layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            layout.setGravity(Gravity.CENTER);
            layout.addView(text);

            return layout;
        }

        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);

    }
}
