/**
 * Created by GaoXiang on 2016/7/18 0018.
 */
public class LongTest {
    public static void main(String[] args) {
        double price = 100001;
        double size = 3;
        double value = price/size;
        String sMoney = new java.text.DecimalFormat("#.00").format(value);
        System.out.println(value);
        System.out.println(sMoney);
    }
}
