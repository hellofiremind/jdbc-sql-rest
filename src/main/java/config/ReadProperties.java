package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jamescross91 on 05/11/2015.
 */

public class ReadProperties {
    private static Properties props = null;

    //Load static properties from the properties file
    private static void load() {
        try {
            InputStream inputStream = ReadProperties.class.getResourceAsStream("/jdbc.config");

            props = new Properties();
            props.load(inputStream);

            inputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Go get the property we want
    public static String getProperty(String property) {
        if(props == null)
            load();

        return(props.getProperty(property));
    }
}
