package kr.badream.convenience.Adapter;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016-06-04.
 */
public class Item_list_view {

    private String main_image;
    private String item_name;
    private String item_price;

    private int item_review_number;
    private int item_like_number;

    public void setitem_like_number(int item_evaluation_number) {
        this.item_like_number = item_evaluation_number;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public void setItem_review_number(int item_review_number) {
        this.item_review_number = item_review_number;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getMain_image() {
        return main_image;
    }

    public int getItem_like_number() {
        return item_like_number;
    }

    public int getItem_review_number() {
        return item_review_number;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_price() {
        return item_price;
    }
}
