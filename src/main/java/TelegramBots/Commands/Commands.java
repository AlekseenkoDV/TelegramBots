/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramBots.Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author D.Alekseenko
 */
public enum Commands
{
    START(Arrays.asList(new TelegramBotType[]
    {
        TelegramBotType.ADMIN
    }), "/start", "Начать"),
    HELP(Arrays.asList(new TelegramBotType[]
    {
        TelegramBotType.ADMIN
    }), "/help", "Помощь"),
    BLACKLIST_WORD(Arrays.asList(new TelegramBotType[]
    {
        TelegramBotType.ADMIN
    }), "/blacklistword", "Добавить файл с черным списком слов (стоп слова)");

    private List<TelegramBotType> tBots;
    private String command;
    private String description;

    private Commands(List<TelegramBotType> tBots, String command, String description)
    {
        this.tBots = tBots;
        this.command = command;
        this.description = description;
    }

    public static List<Commands> getCommands(TelegramBotType botType)
    {

        List<Commands> ret = new ArrayList<>();
        for (Commands value : Commands.values())
        {
            boolean addCommand = botType == null;
            if (botType != null)
            {
                addCommand = value.tBots.contains(botType);
            }

            if (addCommand)
            {
                ret.add(value);
            }
        }
        return ret;
    }

    @Override
    public String toString()
    {
        return command + " " + description; //To change body of generated methods, choose Tools | Templates.
    }

    public String command()
    {
        return command;
    }

    public String description()
    {
        return description;
    }

}
