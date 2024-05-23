package TelegramBots;

import TelegramBots.Bots.AdminBot.AdminBot;
import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author D.Alekseenko
 */
public class TelegramBot extends TelegramLongPollingBot
{

    protected List<TelegramBot> bots = new ArrayList<>();

    public void initBots()
    {
        bots.add(new AdminBot());
    }

    public List<TelegramBot> getBots()
    {
        return bots;
    }

    @Override
    public void onUpdatesReceived(List<Update> list)
    {

    }

    @Override
    public String getBotUsername()
    {
        return "";
    }

    @Override
    public String getBotToken()
    {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update)
    {
    }

    public void sendMessage()
    {

    }

}
