package xyz.d1snin.emily;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Emily {
    public static String prefix = "`";
    private static final String token = "NzcxMDAzNzc2NjE3Njc2ODMw.X5lzOA.85nCDXwvMlnLtB9OLb7nWPGlcNo";
    static JDA jda;

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setStatus(OnlineStatus.IDLE);
        builder.setActivity(Activity.watching("`help | uwu"));
        builder.addEventListeners(new MessageListener());
        builder.build().awaitReady();
    }
}
