import java.util.HashMap;
import java.util.Map;

/**
 * Created by GaoXiang on 2016/8/15.
 */
public class MapTest {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("1","11");
        map.put("2","22");
        map.put("3","33");
        System.out.println(map.toString());
    }
}
