package kr.badream.convenience.Helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    public List<Helper_reviewData> reviewData;

    /*
    public static void setInfo(int _prodID, int _storeID, String _url, String _name, String _price, int _views,
                               int _isLiked, int _likes, int _reviews, List _reviewData){
        prodID = _prodID;
        storeID = _storeID;
        url = _url;
        name = _name;
        price = _price;
        views = _views;
        isLiked = _isLiked;
        likes = _likes;
        reviews = _reviews;
        reviewData = _reviewData;
    }
    */
}
