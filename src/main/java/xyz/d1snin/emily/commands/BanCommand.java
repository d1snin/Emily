package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class BanCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if (args.length < 2) {
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription("Please use the following syntax: " + "`" + Emily.BOT_PREFIX + "ban <mentionTheUser> <Reason>`")
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Color.ORANGE)
                    .build()).queue();
            return;
        }
        if (!e.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription("You dont have permission to use this command.")
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Color.ORANGE)
                    .build()).queue();
        } else {
            String reason = "";
            for (int i = 2; i < args.length; i++) {
                reason += args[i];
            }
            List<Member> mentionedMembers = e.getMessage().getMentionedMembers();
            Member target = mentionedMembers.get(0);
            target.ban(7).reason(reason);
                e.getTextChannel().sendMessage(new EmbedBuilder()
                        .setDescription("User " + target.getAsMention() + " has been banned by " + e.getAuthor().getAsMention() + "\nReason: " + reason)
                        .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                        .setColor(Color.ORANGE)
                        .build()).queue();
        }
    }
    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(Emily.BOT_PREFIX + "ban");
    }

    @Override
    public String getDescription()
    {
        return "Ban a member";
    }
}