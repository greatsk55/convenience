package kr.badream.convenience.Helper;

import java.text.Collator;
import java.util.Comparator;

import kr.badream.convenience.Adapter.Item_list_view;

/**
 * Created by Administrator on 2016-06-14.
 */
public class Helper_sort {

    //Comparator 를 만든다.
    public final static Comparator<Item_list_view> comparator_Item_list_view_like = new Comparator<Item_list_view>() {
        @Override
        public int compare(Item_list_view item1, Item_list_view item2) {
//            return Collator.getInstance().compare(item1.getItem_like_number(),item2.getItem_like_number());
            return ( item1.getItem_like_number() > item2.getItem_like_number() ? 1:-1);
        }

        private final Collator collator = Collator.getInstance();

    };

    public final static Comparator<Item_list_view> comparator_Item_list_view_review = new Comparator<Item_list_view>() {
        @Override
        public int compare(Item_list_view item1, Item_list_view item2) {
//            return Collator.getInstance().compare(item1.getItem_like_number(),item2.getItem_like_number());
            return ( item1.getItem_review_number() > item2.getItem_review_number() ? 1:-1);
        }

        private final Collator collator = Collator.getInstance();

    };
}
