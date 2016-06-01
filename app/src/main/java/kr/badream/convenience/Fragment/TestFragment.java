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

import java.util.ArrayList;

import kr.badream.convenience.Adapter.Activity_convenience;
import kr.badream.convenience.Adapter.Activity_myself;
import kr.badream.convenience.Adapter.Item_mix_adapter;
import kr.badream.convenience.Helper.Helper_itemData;
import kr.badream.convenience.R;


public final class TestFragment extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";

    final int Convinience = 0;
    final int List = 1;
    final int MyInformation = 2;

    private View view;

    private RecyclerView recyclerView;

    private Activity_convenience Activity_convenience;
    private Item_mix_adapter Item_mix_adapter;

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

    private void setAdapterView(LayoutInflater inflater, ViewGroup container, int cases) {
        view = inflater.inflate(R.layout.activity_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);

        Helper_itemData first = new Helper_itemData();
        Helper_itemData second = new Helper_itemData();
        Helper_itemData third = new Helper_itemData();

        first.setItem(0,0,"구구콘",1500, R.drawable.cu);
        //second.roomname = "방 화면 예시 2";
        //second.roomimage = R.drawable.room2;
        //third.roomimage = R.drawable.room3;
        //third.roomname = "방 화면 예시 3";

        ArrayList<Helper_itemData> se = new ArrayList<Helper_itemData>();
        se.add(first);
        //se.add(second);
        //se.add(third);


        //Helper_userData user = new Helper_userData();

        switch (cases) {
            case List:
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                Item_mix_adapter = new Item_mix_adapter(getActivity(),
                        se,
                        (LinearLayoutManager) recyclerView.getLayoutManager());
                recyclerView.setAdapter(Item_mix_adapter);
                break;
        }
        return;
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

        if (mContent.equalsIgnoreCase("b")) { //Ignore Lower Upper case
            //setAdapterView(inflater, container, List);
            Activity_myself activity_myself = new Activity_myself(getContext());
            return activity_myself;
        } else if (mContent.equalsIgnoreCase("a")) {
            Activity_convenience = new Activity_convenience(getContext());
            return Activity_convenience;
        } else {

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

    }

    @Override
    public void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);

    }
}
