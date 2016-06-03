package kr.badream.convenience.Adapter;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016-06-04.
 */
public class Item_list_view {

    private Drawable main_image;
    private String item_name;
    private String item_price;

    private int item_review_number;
    private int item_evaluation_number;

    public void setItem_evaluation_number(int item_evaluation_number) {
        this.item_evaluation_number = item_evaluation_number;
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

    public void setMain_image(Drawable main_image) {
        this.main_image = main_image;
    }

    public Drawable getMain_image() {
        return main_image;
    }

    public int getItem_evaluation_number() {
        return item_evaluation_number;
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
