package TelegramBots;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;
import javax.ws.rs.ApplicationPath;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

        ClassPathXmlApplicationContext context = null;
        try
        {
            SimpleDateFormat formDate = new SimpleDateFormat("ddMMyyyy");
            String currentDate = formDate.format(new Date());
            String outFile = "appout_" + currentDate + ".log";
            String arrFile = "apperr_" + currentDate + ".log";
            PrintStream outStreaem = new PrintStream(new File(outFile));
            System.setOut(outStreaem);
            PrintStream errString = new PrintStream(new File(arrFile));
            System.setErr(errString);

            Tools.printOut("Старт программы");
            Tools.printOut("Подклюеник конфигурационного файла");
            context = new ClassPathXmlApplicationContext("TelegramBotsContext.xml");

            Tools.printOut("Подготовка ботов к запуску");
            TelegramBotsApi reg = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot bots = context.getBean("TelegramBot", TelegramBot.class);
            if (bots == null)
            {
                Tools.printOut("Объект не создан, стоп программы");
                System.exit(1);
            }

            bots.initBots();
            Tools.printOut("Количество запускаешь ботов: " + bots.getBots().size());

            for (TelegramBot bot : bots.getBots())
            {
                try
                {
                    Tools.printOut(bot.getBotUsername(), "Запускется бот");
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
        finally
        {
            if (context != null)
            {
                context.close();
            }
        }
    }
}
