package TelegramBots.Bots.AdminBot;

import TelegramBots.Commands.Commands;
import TelegramBots.Commands.TelegramBotType;
import TelegramBots.TelegramBot;
import TelegramBots.Tools;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

/**
 *
 * @author D.Alekseenko
 */
public class AdminBot extends TelegramBot
{

    public AdminBot()
    {
        try
        {
            List<BotCommand> bCommnads = new ArrayList<>();
            for (Commands command : Commands.getCommands(TelegramBotType.ADMIN))
            {
                BotCommand bCommand = new BotCommand(command.command(), command.description());
                bCommnads.add(bCommand);
            }
            execute(new SetMyCommands(bCommnads, new BotCommandScopeDefault(), null));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        try
        {
            Message msg = update.getMessage();
            User user = msg.getFrom();
            Chat chat = msg.getChat();
            String text = msg.getText().toLowerCase();
            if (msg.hasDocument())
            {
                Document doc = msg.getDocument();
                System.out.println(doc.getFileName());
                text = msg.getCaption().toLowerCase();
            }

            System.out.println("Группа/ID: " + chat.getTitle() + "/" + chat.getId() + "\nПользователь: " + user.getUserName() + "\nСообщение: " + text);
            System.out.println("-------------");
//        boolean delete = false;
//        for (String string : text.split(" "))
//        {
//
//        }
//
//        if (delete)
//        {
//            
//        }
//
//        if (text.contains("сука") || text.contains("бля"))
//        {
//            try
//            {
//                SendMessage message = new SendMessage();
//                message.setChatId(chat.getId());
////                message.setReplyToMessageId(msg.getMessageId()); // Указываем идентификатор сообщения, на которое хотим ответить
//                message.setText("@" + user.getUserName() + " Сообщение будет удалено тк в нём содержется слово из черного списка слов, пошел нахер");
//                execute(message);
//
//                DeleteMessage delMsg = new DeleteMessage();
//                delMsg.setChatId(chat.getId());
//                delMsg.setMessageId(msg.getMessageId());
//                execute(delMsg);
//            }
//            catch (TelegramApiException ex)
//            {
//                Logger.getLogger(TelegramBot.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

            // Есди вызвали команду из группы, то удаляем её и ничего не делаем
            if (chat.getId() < 0 && text.startsWith("/"))
            {
                DeleteMessage delMsg = new DeleteMessage();
                delMsg.setChatId(chat.getId());
                delMsg.setMessageId(msg.getMessageId());
                execute(delMsg);
                return;
            }
            
            // Команда пришла из личного чата с пользователем
            if (chat.getId() > 0)
            {
                
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public String getBotToken()
    {
        try
        {
            return Tools.getBotToken(this.getClass().getSimpleName());
        }
        catch (Exception ex)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public String getBotUsername()
    {
        try
        {
            return Tools.getBotName(this.getClass().getSimpleName());
        }
        catch (Exception ex)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public void onUpdatesReceived(List<Update> list)
    {
        for (Update upd : list)
        {
            onUpdateReceived(upd); //To change body of generated methods, choose Tools | Templates.Update    
        }

    }

}
