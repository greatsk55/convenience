package kr.badream.convenience.Adapter;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-06-14.
 */
public class item_mini_list_view implements Serializable {


    private int isItem;
    private String main_image;
    private String item_name;

    public item_mini_list_view(){

    }

    public item_mini_list_view(String img, String item_name) {

        this.setMain_image(img);
        this.setItem_name(item_name);
    }

    public void setIsItem(int isItem) {
        this.isItem = isItem;
    }
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public int getIsItem() {
        return isItem;
    }

    public String getMain_image() {
        return main_image;
    }

    public String getItem_name() {
        return item_name;
    }

}
