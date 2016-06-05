package kr.badream.convenience.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.badream.convenience.R;

/**
 * Created by Administrator on 2016-06-04.
 */
public class Adapter_list_view  extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Item_list_view> listViewItemList = new ArrayList<Item_list_view>() ;

    // ListViewAdapter의 생성자
    public Adapter_list_view() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_item_list, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView item_image = (ImageView) convertView.findViewById(R.id.item_image) ;
        TextView item_name = (TextView) convertView.findViewById(R.id.item_name) ;
        TextView item_price = (TextView) convertView.findViewById(R.id.item_price) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Item_list_view item_list_view = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        item_image.setImageDrawable(item_list_view.getMain_image());
        item_name.setText(item_list_view.getItem_name());
        item_price.setText(item_list_view.getItem_price());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        Log.e("good","good" + position);
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Drawable img, String item_name, String item_price) {
        Item_list_view item = new Item_list_view();

        item.setMain_image(img);
        item.setItem_name(item_name);
        item.setItem_price(item_price);

        listViewItemList.add(item);
    }


}
