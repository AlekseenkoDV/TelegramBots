/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramBots.Bots.BotHolder;

import TelegramBots.Commands.Commands;
import TelegramBots.Commands.TelegramBotType;
import TelegramBots.TelegramBot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

/**
 *
 * @author fif90
 */
public class BotHolder extends TelegramBot
{

    public BotHolder()
    {
        try
        {
            List<BotCommand> bCommnads = new ArrayList<>();
            for (Commands command : Commands.getCommands(TelegramBotType.HOLDER))
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
            File fl = new File(msg.getChatId().toString() + ".txt");
            StringBuilder sb = new StringBuilder();
            boolean read = fl.exists();

            if (msg.getText().startsWith("/"))
            {
                Commands cmd = Commands.checkCommand(msg.getText());
                if (cmd == Commands.UNK)
                {
                    return;
                }
//                System.out.println(msg);
                switch (cmd)
                {
                    case START:
                        execute(new SendMessage(msg.getChatId().toString(), "Привет и добро пожаловать"));
                        return;
                    case HELP:
                        execute(new SendMessage(msg.getChatId().toString(), "Что я умею"));
                        return;
                    case HOLDLIST:
                        if (!read)
                        {
                            execute(new SendMessage(msg.getChatId().toString(), "Список пуст"));
                            return;
                        }
                        FileReader fr = new FileReader(fl);
                        BufferedReader reader = new BufferedReader(fr);
                        String line;
                        while ((line = reader.readLine()) != null)
                        {
                            StringBuilder tempSb = new StringBuilder(sb.toString());
                            tempSb.append(line);
                            if (tempSb.toString().length() >= 4000)
                            {
                                execute(new SendMessage(msg.getChatId().toString(), sb.toString()));
                                sb = new StringBuilder();
                            }
                            sb.append(line).append("\n");
                        }
                        execute(new SendMessage(msg.getChatId().toString(), sb.toString()));
                        return;
                }
            }

            if (!fl.exists())
            {
                fl.createNewFile();
            }

            if (read)
            {
                FileReader fr = new FileReader(fl);
                BufferedReader reader = new BufferedReader(fr);
                String line;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line).append("\n");
                }
            }

            sb.append(msg.getText());

            FileWriter wr = new FileWriter(fl);
            wr.write(sb.toString());
            wr.close();

            execute(new SendMessage(msg.getChatId().toString(), "Записана новая запись"));

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
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
