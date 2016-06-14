package kr.badream.convenience.Helper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 16. 6. 14.
 */
public class Helper_itemInfo implements Serializable{
    public int prodID;
    public int storeID;

    public String url;
    public String name;
    public String price;
    public int views;

    public int isLiked;
    public int likes;
    public int reviews;
    public ArrayList<Helper_reviewData> reviewData;
}
