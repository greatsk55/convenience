package kr.badream.convenience.Helper;


import java.util.ArrayList;

import kr.badream.convenience.R;

/**
 * Created by user on 16. 3. 2.
 */

//TODO 방정보를 저장할 자료형 클래스
public class Helper_itemData {

    private int id;
    private int conv_id;
    private String itemname;
    private int price;
    private int image;


    public ArrayList<Integer> list;

    public Helper_itemData(){
        //list = new ArrayList<Integer>();
       // list.add(0, R.drawable.facebook_gray);

        itemname = "삼각김밥";
        image = R.drawable.cu;
        price = 700;
    }

    public void setItem(int id, int conv_id, String itemname, int price, int image){
        this.id = id;
        this.conv_id = conv_id;
        this.itemname = itemname;
        this.price = price;
        this.image = image;
    }

    public int getPrice(){
        return price;
    }
    public String getName(){
        return itemname;
    }
    public int getImage(){
        return image;
    }
}
