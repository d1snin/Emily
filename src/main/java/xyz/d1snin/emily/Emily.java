package xyz.d1snin.emily;

import net.dv8tion.jda.api.entities.Activity;
import xyz.d1snin.emily.commands.*;
import xyz.d1snin.emily.util.Log;
import xyz.d1snin.emily.util.ReadJSON;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

public class Emily
{
    private static JDA api;
    public static String BOT_PREFIX = "'";
    public static String BOT_NAME = "Emily";
    public static void main(String[] args)
    {
            setupBot();
    }
    public static JDA getAPI()
    {
        return api;
    }
    private static void setupBot()
    {
        try
        {
            JDABuilder jdaBuilder = JDABuilder.createDefault((ReadJSON.readJson("conf.json", "token")));
            HelpCommand help = new HelpCommand();
            jdaBuilder.addEventListeners(help.registerCommand(help));
            jdaBuilder.setEnableShutdownHook(true);
            jdaBuilder.setActivity(Activity.watching("'help | " + api.getGuildCache().size()));
            jdaBuilder.addEventListeners(
                    help.registerCommand(new AnimeCommand()),
                    help.registerCommand(new PingCommand())
            );
            api = jdaBuilder.build();
            api.awaitReady();
            Log.Info("Bot has started up!");

        }
        catch (IllegalArgumentException e)
        {
            Log.Error("No login details provided! Please provide a botToken in the config.");
            System.exit(0);
        }
        catch (LoginException e)
        {
            Log.Error("The botToken provided in the conf.json was incorrect.\nDid you modify the conf.json after it was created?");
            System.exit(0);
        }
        catch (InterruptedException e)
        {
            Log.Error("Our login thread was interrupted!");
            System.exit(0);
        }
    }
}