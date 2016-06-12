package kr.badream.convenience.Helper;


import java.util.ArrayList;

import kr.badream.convenience.R;

/**
 * Created by user on 16. 3. 2.
 */

//TODO 방정보를 저장할 자료형 클래스
public class Helper_itemData {

    public int prodID;
    public int storeID;
    public int mainCategory;
    public int subCategory;

    public String url;
    public String name;
    public int price;
    public int views;

    public String login;
    public String html_url;
    int contributions;

    @Override
    public String toString(){
        return login + " (" + contributions + ")";
    }


    public Helper_itemData(){
    }

    public Helper_itemData(int prodID, int storeID, int mainCategory, int subCategory, String url, String name, int price, int views){
        this.prodID = prodID;
        this.storeID = storeID;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.url = url;
        this.name = name;
        this.price = price;
        this.views = views;
    }
}
