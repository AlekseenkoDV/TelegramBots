package TelegramBots;

import java.io.File;
import java.io.PrintStream;
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

            Tools.printOut("Старт программы");
            
            String outFile = "_out_.log";
            String arrFile = "_err_.log";
            PrintStream outStreaem = new PrintStream(new File(outFile));
            System.setOut(outStreaem);
            PrintStream errString = new PrintStream(new File(arrFile));
            System.setErr(errString);

            Tools.printOut("Подготовка ботов к запуску");
            TelegramBotsApi reg = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot bots = new TelegramBot();         
            bots.initBots();
            Tools.printOut("Количество запускаешь ботов: " + bots.getBots().size());
            
            for (TelegramBot bot : bots.getBots())
            {
                try
                {
                    Tools.printOut(bot.getBotUsername(), "Запускется бот" );
                    reg.registerBot(bot);
                }
                catch (Exception ex)
                {
                    Tools.printErr(bot.getBotUsername(), "Ошибка запуска бота");
                    Tools.printErr(bot.getBotUsername(), ex.toString());
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception ex)
        {
            Tools.printErr("Ошибка запуска программы");
            ex.printStackTrace();
        }
    }
}
