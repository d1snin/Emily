package xyz.d1snin.emily;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import xyz.d1snin.emily.commands.HelpCommand;
import xyz.d1snin.emily.commands.PingCommand;
import xyz.d1snin.emily.util.readFromJson;
import xyz.d1snin.emily.util.Time;

public class Emily {

    private static final String token = readFromJson.readJson("conf.json", "token");
    public static String prefix = readFromJson.readJson("conf.json", "prefix");
    private static JDA jda;

    private static final ListenerAdapter[] commands = {
            new PingCommand(),
            //new HelpCommand()
    };

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder builder = JDABuilder.createDefault(token);
        log("Logged.");
        builder.setStatus(OnlineStatus.IDLE);
        log("Status - IDLE.");
        builder.setActivity(Activity.watching("`help | uwu"));
        log("Activity - Watching `help | uwu");
        for (ListenerAdapter command : commands) {
            builder.addEventListeners(command);
        }
        log("All commands have been loaded.");
        builder.build().awaitReady();
        log("Bot has started up.");
    }

    public static void log(String text) {
        System.out.println("[" + Time.getTime() + "] " + text);
    }
}

