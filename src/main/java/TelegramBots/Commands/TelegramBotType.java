/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramBots.Commands;

/**
 *
 * @author D.Alekseenko
 */
public enum TelegramBotType
{
    ADMIN("Администратор", 1),
    HOLDER("Держатель", 2);
    
    private String name;
    private Integer code;

    private TelegramBotType(String name, Integer code)
    {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString()
    {
        return name;
    }
    
    
    
    
}
