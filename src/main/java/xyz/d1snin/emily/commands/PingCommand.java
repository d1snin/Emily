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
        e.getTextChannel().sendMessage(new EmbedBuilder()
                .setDescription(e.getJDA().getGatewayPing() + "ms")
                .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                .setColor(Color.ORANGE)
                .build()).queue();
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