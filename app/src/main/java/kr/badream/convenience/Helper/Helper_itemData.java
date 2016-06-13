package kr.badream.convenience.Helper;


import java.io.Serializable;
import java.util.ArrayList;

import kr.badream.convenience.R;

/**
 * Created by user on 16. 3. 2.
 */

//TODO 방정보를 저장할 자료형 클래스
public class Helper_itemData implements Serializable {

    public int prodID;
    public int storeID;
    public int mainCategory;
    public int subCategory;

    public String url;
    public String name;
    public String price;
    public int views;


    public Helper_itemData(){
    }

    public Helper_itemData(int prodID, int storeID, int mainCategory, int subCategory, String url, String name, String price, int views){
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
