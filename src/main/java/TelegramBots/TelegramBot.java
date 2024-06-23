package TelegramBots;

import TelegramBots.Bots.AdminBot.AdminBot;
import TelegramBots.Bots.BotHolder.BotHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author D.Alekseenko
 */
public class TelegramBot extends TelegramLongPollingBot {

    protected List<TelegramBot> bots = new ArrayList<>();

    public TelegramBot() {
    }

    public void initBots() {
        bots.add(new AdminBot());
        bots.add(new BotHolder());
    }

    public List<TelegramBot> getBots() {
        return bots;
    }

    @Override
    public void onUpdatesReceived(List<Update> list) {

    }

    @Override
    public String getBotUsername() {
        try {
            return Tools.getBotName(this.getClass().getSimpleName());
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public String getBotToken() {
        try {
            return Tools.getBotToken(this.getClass().getSimpleName());
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    public void sendMessage() {

    }

}
