package kr.badream.convenience.Helper;

/**
 * Created by user on 16. 6. 14.
 */
public class Helper_reviewData {
    public int isLiked;
    public int reviewID;
    public int prodID;
    public int userID;
    public String userName;
    public String content;
    public String price;
    public int likes;

    public Helper_reviewData(int isLiked, int reviewID, int prodID, int userID, String userName, String content, String price, int likes){
        this.isLiked = isLiked;
        this.reviewID = reviewID;
        this.prodID = prodID;
        this.userID = userID;
        this.userName = userName;
        this.content = content;
        this.price = price;
        this.likes = likes;
    }
}
