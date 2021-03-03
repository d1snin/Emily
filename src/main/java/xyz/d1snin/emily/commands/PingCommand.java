package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.d1snin.emily.Emily;

import java.awt.*;

public class PingCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
            String[] args = event.getMessage().getContentRaw().split(" ");
            String commandName = "ping";
            if (args[0].equalsIgnoreCase(Emily.prefix + commandName) && !(event.getAuthor().isBot())) {
                long time = System.currentTimeMillis();
                EmbedBuilder builderPong = new EmbedBuilder();
                builderPong.setColor(Color.MAGENTA);
                builderPong.setDescription("pong");
                event.getChannel().sendMessage(builderPong.build())
                        .queue(response -> {
                            EmbedBuilder builderPing = new EmbedBuilder();
                            builderPing.setColor(Color.MAGENTA);
                            builderPing.setDescription("Pong: " + (System.currentTimeMillis() - time) + "ms");
                            response.editMessage(builderPing.build()).queue();
                            Emily.log("Command " + "<" + commandName + ">" + " executed.");
                        });
            }
    }
}
