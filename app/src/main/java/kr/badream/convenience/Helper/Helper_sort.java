package kr.badream.convenience.Helper;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by Administrator on 2016-06-14.
 */
public class Helper_sort {

    //Comparator 를 만든다.
    private final static Comparator myComparator= new Comparator() {
        private final Collator   collator = Collator.getInstance();
        @Override
        public int compare(Object lhs, Object rhs) {
            //숫자는 문자열로 변환시 길이가 가변이라서 값에따른 sort가 비정상(1200보다 130이 높게 나옴)
            //문자열의 길이를 맞추어 compare 를 만들면 정상적인 sort가 가능
            String price1 = getData1();
            String price2 = getData2();
            String tmp1 = "";
            String tmp2 = "";

            if(getData1() < 5){
                tmp1 = tmp1 + "0";
            }
            if(getData2() < 5){
                tmp2 = tmp2 + "0";
            }
            return collator.compare(tmp1+price1, tmp2+price2);
        }

}
