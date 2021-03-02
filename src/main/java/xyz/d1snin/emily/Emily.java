package xyz.d1snin.emily;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import xyz.d1snin.emily.util.readTokenFromJson;

public class Emily {

    private static final String token = readTokenFromJson.readJson();
    public static String prefix = "`";

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setStatus(OnlineStatus.IDLE);
        builder.setActivity(Activity.watching("`help | uwu"));
        builder.addEventListeners(new MessageListener());
        builder.build().awaitReady();
    }
}
