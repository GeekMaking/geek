import java.io.File;

/**
 * Created by ASUS on 2017/2/21.
 */
public class FileTest {
    public static void main(String[] args) {
        File f=new File("C:\\Users\\ASUS\\Desktop\\1.txt");
        System.out.println(f.delete());
    }
}
