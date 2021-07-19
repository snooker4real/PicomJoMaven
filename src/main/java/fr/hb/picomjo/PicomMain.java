package fr.hb.picomjo;
import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PicomMain {

    public static void main(String[] args) throws IOException {
        //System.out.println("Jonathan");
        InputStream aIn = PicomMain.class.getResourceAsStream("/env/app.env");
        Properties env = new Properties();

        env.load(aIn);

        String aBasePath = env.getProperty("basepath");
        System.out.println("basepath=" + aBasePath);
    }
}
