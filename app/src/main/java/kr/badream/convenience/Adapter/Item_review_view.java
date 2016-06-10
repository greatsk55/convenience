package kr.badream.convenience.Adapter;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016-06-10.
 */
public class Item_review_view {

    private Drawable main_image;
    private String user_name;
    private String item_price;
    private String like_number;
    private String review_main;

    public void setMain_image(Drawable main_image) {
        this.main_image = main_image;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public void setLike_number(String like_number) {
        this.like_number = like_number;
    }

    public void setReview_main(String review_main) {
        this.review_main = review_main;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public Drawable getMain_image() {
        return main_image;
    }

    public String getLike_number() {
        return like_number;
    }

    public String getReview_main() {
        return review_main;
    }

    public String getUser_name() {
        return user_name;
    }
}

