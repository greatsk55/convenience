package kr.badream.convenience.Helper;

import java.io.Serializable;

/**
 * Created by user on 16. 6. 14.
 */
public class Helper_reviewData implements Serializable {
    public int reviewID;
    public int prodID;
    public int userID;
    public String userName;
    public String content;
    public String price;
    public int likes;

    public int isLiked;

    public Helper_reviewData(int reviewID, int prodID, int userID, String userName, String content, String price, int likes, int isLiked){
        this.reviewID = reviewID;
        this.prodID = prodID;
        this.userID = userID;
        this.userName = userName;
        this.content = content;
        this.price = price;
        this.likes = likes;
        this.isLiked = isLiked;
    }
}
