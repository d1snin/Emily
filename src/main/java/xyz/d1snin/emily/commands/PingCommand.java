package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PingCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        long time = System.currentTimeMillis();
        EmbedBuilder builderPong = new EmbedBuilder();
        builderPong.setColor(Color.MAGENTA);
        builderPong.setDescription("pong");
        e.getChannel().sendMessage(builderPong.build())
                .queue(response -> {
                    EmbedBuilder builderPing = new EmbedBuilder();
                    builderPing.setColor(Color.MAGENTA);
                    builderPing.setDescription("Pong: " + (System.currentTimeMillis() - time) + "ms");
                    response.editMessage(builderPing.build()).queue();
                });
    }
    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(Emily.BOT_PREFIX + "ping");
    }

    @Override
    public String getDescription()
    {
        return "Provide Emily's ping";
    }
}