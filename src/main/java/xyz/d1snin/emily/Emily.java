package xyz.d1snin.emily;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import xyz.d1snin.emily.commands.fun.AnimeCommand;
import xyz.d1snin.emily.commands.info.HelpCommand;
import xyz.d1snin.emily.commands.info.PingCommand;
import xyz.d1snin.emily.commands.info.ReportCommand;
import xyz.d1snin.emily.commands.moderation.BanCommand;
import xyz.d1snin.emily.commands.moderation.KickCommand;
import xyz.d1snin.emily.util.Log;
import xyz.d1snin.emily.util.ReadJSON;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;
import java.time.LocalTime;

public class Emily
{
    private static JDA api;
    public static String BOT_PREFIX = "'";
    public static String BOT_NAME = "Emily | エミリー";
    public static String LAST_RESTART = getTime();
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
            jdaBuilder.setStatus(OnlineStatus.IDLE);
            jdaBuilder.addEventListeners(
                    help.registerCommand(new AnimeCommand()),
                    help.registerCommand(new PingCommand()),
                    help.registerCommand(new ReportCommand()),
                    help.registerCommand(new BanCommand()),
                    help.registerCommand(new KickCommand())
            );
            api = jdaBuilder.build();
            api.awaitReady();
            Log.Info("Bot has started up!    " + BOT_NAME);
            pingUpdate.start();
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
    static Thread pingUpdate = new Thread(() -> {
        while(true){
            try {
                api.getPresence().setActivity(Activity.watching("'help | " + "Ping: " + api.getGatewayPing() + " ms"));
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
            }
        }
    });
    private static String getTime() {
        LocalTime now = LocalTime.now();
        String time = now.toString();
        return time.substring(0, 8);
    }
}