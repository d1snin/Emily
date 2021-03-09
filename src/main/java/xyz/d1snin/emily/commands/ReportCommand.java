package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ReportCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        String reportMsg = e.getMessage().getContentRaw();
        TextChannel chan = e.getGuild().getTextChannelById("818905717561229332");
        chan.sendMessage(new EmbedBuilder()
                .setDescription("**New report.**\n**User:** " + e.getAuthor().getName() + "#" + e.getAuthor().getAsTag() + "\n**Report Message:** " + reportMsg)
                .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                .setColor(Color.ORANGE)
                .build()).queue();
        e.getTextChannel().sendMessage(new EmbedBuilder()
                .setDescription("Thanks for your report! We'll look at it shortly.")
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