package kr.badream.convenience.Adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.badream.convenience.R;

/**
 * Created by Administrator on 2016-06-14.
 */
public class Adapter_mini_list_view  extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList

    private ArrayList<item_mini_list_view> listViewItemList = new ArrayList<item_mini_list_view>() ;

    // ListViewAdapter의 생성자
    public Adapter_mini_list_view() {

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
            convertView = inflater.inflate(R.layout.adapter_mini_list, parent, false);
        }

        ImageView item_image = ViewHolderHelper.get(convertView, R.id.item_image);
        TextView item_name = ViewHolderHelper.get(convertView, R.id.item_name);

//        convertView.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent ctgview_intent = new Intent( context , View_item_info.class);
//                context.startActivity(ctgview_intent);
//                Log.e("position","position: " + pos);
//            }
//        });

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        item_mini_list_view item_list_view = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        //item_image.setImageDrawable(item_list_view.getMain_image());
        if(item_list_view.getIsItem() == 0)
            item_image.setImageResource(R.drawable.ic_action_plus);
        else
            Glide.with(context).load(item_list_view.getMain_image()).error(null).into(item_image);
        item_name.setText(item_list_view.getItem_name());


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
        return listViewItemList.get(position) ;
    }

    public void clear(){
        listViewItemList.clear();
    }

    // 1.이미지, 2.물품이름, 3.가격, 4.좋아요수, 5.리뷰수
    public void addItem(int isItem, String img, String item_name) {
        item_mini_list_view item = new item_mini_list_view();

        item.setIsItem(isItem);
        item.setMain_image(img);
        item.setItem_name(item_name);

        listViewItemList.add(item);
    }

    public static class ViewHolderHelper{
        public static <T extends View> T get(View convertView, int id) {

            SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();

            if (viewHolder == null) {

                viewHolder = new SparseArray<View>();
                convertView.setTag(viewHolder);
            }

            View childView = viewHolder.get(id);

            if (childView == null) {

                childView = convertView.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }
    }


}
