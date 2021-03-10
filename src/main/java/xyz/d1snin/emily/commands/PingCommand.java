package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;
import xyz.d1snin.emily.util.EmbedUtils;

import java.util.Arrays;
import java.util.List;

public class PingCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        EmbedUtils.sendEmbed(e, "Ping: " + e.getJDA().getGatewayPing() + "ms");
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
    @Override
    public String getCategory() {
        return "Info";
    }
}