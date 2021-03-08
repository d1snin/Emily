package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
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
    private static final String NO_DESCRIPTION = "No description has been provided for this command. Sorry!";

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
                String description = c.getDescription();
                description = (description == null || description.isEmpty()) ? NO_DESCRIPTION : description;

                s.append("| `").append(c.getAliases().get(0)).append("` - ");
                s.append(description).append("\n");
            }

            channel.sendMessage(new MessageBuilder()
                    .append(":star: **The following commands are supported by the bot** :star:\n")
                    .append(s.toString())
                    .build()).queue();
    }
}