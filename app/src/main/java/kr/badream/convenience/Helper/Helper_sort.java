package kr.badream.convenience.Helper;

import java.text.Collator;
import java.util.Comparator;

import kr.badream.convenience.Adapter.Item_list_view;

/**
 * Created by Administrator on 2016-06-14.
 */
public class Helper_sort {

    //Comparator 를 만든다.
    public final static Comparator<Helper_itemData> comparator_itemData_view_like = new Comparator<Helper_itemData>() {
        @Override
        public int compare(Helper_itemData item1, Helper_itemData item2) {
//            return Collator.getInstance().compare(item1.getItem_like_number(),item2.getItem_like_number());
            return ( item1.likes > item2.likes ? 1:-1);
        }

        private final Collator collator = Collator.getInstance();

    };

    public final static Comparator<Helper_itemData> comparator_itemData_view_review = new Comparator<Helper_itemData>() {
        @Override
        public int compare(Helper_itemData item1, Helper_itemData item2) {
//            return Collator.getInstance().compare(item1.getItem_like_number(),item2.getItem_like_number());
            return ( item1.reviews > item2.reviews ? 1:-1);
        }

        private final Collator collator = Collator.getInstance();

    };



}
