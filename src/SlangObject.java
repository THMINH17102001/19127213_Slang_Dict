import java.util.List;
import java.util.TreeMap;

public class SlangObject {
    private TreeMap<String, List<String>> map = new TreeMap<>();

    private static SlangObject obj = new SlangObject();

    public static SlangObject getInstance() {
        if (obj == null) {
            synchronized (SlangObject.class) {
                if (obj == null) {
                    obj = new SlangObject();// instance will be created at request time
                }
            }
        }
        return obj;
    }

}
