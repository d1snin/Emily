package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class KickCommand extends Command {
    private static String reason = "";
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if (args.length < 2) {
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription("Please use the following syntax: " + "`" + Emily.BOT_PREFIX + "kick <mentionTheUser> <Reason>`")
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Color.ORANGE)
                    .build()).queue();
            return;
        }
        if (!e.getMember().hasPermission(Permission.KICK_MEMBERS)) {
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription("You dont have permission to use this command.")
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Color.ORANGE)
                    .build()).queue();
        } else {
            for (int i = 2; i < args.length; i++) {
                reason += args[i];
            }
            List<Member> mentionedMembers = e.getMessage().getMentionedMembers();
            Member target = mentionedMembers.get(0);
            target.kick(reason).queue();
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription("User " + target.getAsMention() + " has been kicked by " + e.getAuthor().getAsMention() + "\nReason: " + reason)
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Color.ORANGE)
                    .build()).queue();
            try {
                sendPrivate(e.getAuthor().openPrivateChannel().complete(), args, e);
            } catch (RuntimeException exception) {
                e.getTextChannel().sendMessage(new EmbedBuilder()
                        .setDescription("It is impossible to write a message to the user, perhaps the DM is closed")
                        .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                        .setColor(Color.ORANGE)
                        .build()).queue();
            }
        }
    }
    private void sendPrivate(PrivateChannel channel, String[] args, MessageReceivedEvent e)
    {
        channel.sendMessage(new EmbedBuilder()
                .setDescription("You have been kicked from the server " + e.getGuild().getName() + " by " + e.getAuthor().getAsMention() + "\nReason: " + reason)
                .setColor(Color.ORANGE)
                .setFooter(Emily.BOT_NAME, Emily.getAPI().getSelfUser().getAvatarUrl())
                .build()).queue();
    }
    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(Emily.BOT_PREFIX + "kick");
    }

    @Override
    public String getDescription()
    {
        return "Kick a member";
    }
}