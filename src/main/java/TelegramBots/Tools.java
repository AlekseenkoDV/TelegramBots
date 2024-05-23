package TelegramBots;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author D.Alekseenko
 */
public class Tools
{

    public static String getBotToken(String prop) throws Exception
    {
        String ret = "";
        Properties prot = new Properties();
        prot.load(Tools.class.getClassLoader().getResourceAsStream(prop + ".properties"));
        ret = prot.getProperty("bot_token");
        return ret;
//        return ResourceBundle.getBundle("bot_conf", new Locale("ru_RU")).getString("bot_token");
    }

    public static String getBotName(String prop) throws Exception
    {
        String ret = "";
        Properties prot = new Properties();
        prot.load(Tools.class.getClassLoader().getResourceAsStream(prop + ".properties"));
        ret = prot.getProperty("bot_name");
        return ret;
//        return ResourceBundle.getBundle("bot_conf", new Locale("ru_RU")).getString("bot_name");
    }
}
