package xyz.d1snin.emily.commands.info;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;
import xyz.d1snin.emily.commands.Command;
import xyz.d1snin.emily.util.EmbedUtils;

import java.util.Arrays;
import java.util.List;

public class ReportCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        String reportMsg = e.getMessage().getContentRaw();
        TextChannel chan = e.getGuild().getTextChannelById("818905717561229332");
        if (args.length > 1) {
            EmbedUtils.sendEmbedToCurrentChannel(e, chan, "**New report.**\n**User:** " + e.getAuthor().getAsTag() + "\n**Report Message:** " + reportMsg.substring(8));
            EmbedUtils.sendEmbed(e, "Thanks for your report! We'll look at it shortly.");
        } else {
            EmbedUtils.sendEmbed(e, "Please describe the problem in more detail.");
        }
    }
    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(Emily.BOT_PREFIX + "report");
    }

    @Override
    public String getDescription()
    {
        return "Reporting a problem in a bot";
    }
    @Override
    public String getCategory() {
        return "Info";
    }
}