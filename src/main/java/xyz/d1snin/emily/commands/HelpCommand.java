package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class HelpCommand extends Command
{
    private TreeMap<String, Command> commands;

    public HelpCommand()
    {
        commands = new TreeMap<>();
    }

    public Command registerCommand(Command command)
    {
        commands.put(command.getAliases().get(0), command);
        return command;
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(!e.isFromType(ChannelType.PRIVATE))
        {
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription(e.getAuthor().getAsMention() + ", Help information was sent as a private message.")
                    .setColor(Color.ORANGE)
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .build()).queue();
        }
        sendPrivate(e.getAuthor().openPrivateChannel().complete(), args);
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(Emily.BOT_PREFIX + "help", Emily.BOT_PREFIX + "commands");
    }

    @Override
    public String getDescription()
    {
        return "Command that helps use all other commands!";
    }

    private void sendPrivate(PrivateChannel channel, String[] args)
    {
            StringBuilder s = new StringBuilder();
            for (Command c : commands.values())
            {
                if (c.getCategory().equals("Fun")) {
                    s.append(c.getCategory());
                    s.append("\n`").append(c.getAliases().get(0)).append("` - ").append(c.getDescription());
                }
            }
            for (Command c : commands.values()) {
                if (c.getCategory().equals("Moderation")) {
                    s.append(c.getCategory());
                    s.append("\n`").append(c.getAliases().get(0)).append("` - ").append(c.getDescription());
                }
            }
            for (Command c : commands.values()) {
                if (c.getCategory().equals("Info")) {
                    s.append(c.getCategory());
                    s.append("\n`").append(c.getAliases().get(0)).append("` - ").append(c.getDescription());
                }
            }



            channel.sendMessage(new EmbedBuilder()
                    .setDescription(":star: **The following commands are supported by the bot** :star:\n\n" + s.toString())
                    .setColor(Color.ORANGE)
                    .setFooter(Emily.BOT_NAME, Emily.getAPI().getSelfUser().getAvatarUrl())
                    .build()).queue();
    }
    @Override
    public String getCategory() {
        return "Info";
    }
}