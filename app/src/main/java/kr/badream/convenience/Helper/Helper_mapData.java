package kr.badream.convenience.Helper;

import java.io.Serializable;

/**
 * Created by user on 16. 6. 14.
 */
public class Helper_mapData implements Serializable {
    public int mapID;
    public int storeID;

    public String storeName;
    public String tel;
    public String address;
    public String latitude;
    public String longitude;

    public Helper_mapData(int mapID, int storeID, String storeName, String tel, String address, String lat, String lng){
        this.mapID = mapID;
        this.storeID = storeID;
        this.storeName = storeName;
        this.tel = tel;
        this.address = address;
        this.latitude = lat;
        this.longitude = lng;
    }
}
