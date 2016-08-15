/**
 * Created by GaoXiang on 2016/8/12.
 */
public class ErrorTest {

    public static void main(String[] args) throws InterruptedException {
        try {
            Integer value1 = format("name");
        } catch (Exception e) {

            boolean isClass = e instanceof NumberFormatException;
            boolean isClass2 = e instanceof ClassNotFoundException;
            System.out.println( isClass);
            System.out.println( isClass2);
            Thread.sleep(1000);
            e.printStackTrace();
        }
    }

    public  static Integer format(String val){
        Integer value = Integer.parseInt(val);
        return value;
    }
}
