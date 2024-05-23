package TelegramBots;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 *
 * @author D.Alekseenko
 */
public class Main
{

    public static void main(String[] args)
    {
        try
        {
            TelegramBotsApi reg = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot bots = new TelegramBot();
            bots.initBots();
            for (TelegramBot bot : bots.getBots())
            {
                reg.registerBot(bot);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
