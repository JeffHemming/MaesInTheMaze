import java.io.IOException;

/**
 * Created by Dad on 3/9/2015.
 */
public class CreateNewLevel {
    public static Level l;
    public static Playboard pb;
    CreateNewLevel(int curr) throws IOException {
        l=new Level(Main.masterLevelList.get(curr));
        pb=new Playboard(l);
    }
}
