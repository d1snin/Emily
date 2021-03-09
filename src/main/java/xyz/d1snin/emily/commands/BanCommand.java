package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class BanCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        Member member = e.getMember();
        List<Member> mentionedMembers = e.getMessage().getMentionedMembers();
        List<String> arg = Arrays.asList(args.clone());
        Member target = mentionedMembers.get(0);
        if (!member.hasPermission(Permission.BAN_MEMBERS) && !member.canInteract(target)) {
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription("You dont have permission to run this command.")
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Color.ORANGE)
                    .build()).queue();
            return;
        }
        if (mentionedMembers.isEmpty() || arg.size() < 2) {
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription("Missing arguments.")
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .setColor(Color.ORANGE)
                    .build()).queue();
            return;
        }
        target.ban(0)
                .reason(null);
        e.getTextChannel().sendMessage(new EmbedBuilder()
                .setDescription("User " + target.getAsMention() + "was banned by " + e.getAuthor().getAsMention())
                .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                .setColor(Color.ORANGE)
                .build()).queue();
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