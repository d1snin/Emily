package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;
import xyz.d1snin.emily.util.EmbedUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class KickCommand extends Command {
    private static String reason = "";

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if (args.length < 2) {
            EmbedUtils.sendEmbed(e, "Please use the following syntax: " + "`" + Emily.BOT_PREFIX + "kick` `<mentionTheUser>` `<Reason>`");
            return;
        }
        if (!e.getMember().hasPermission(Permission.KICK_MEMBERS)) {
            EmbedUtils.sendEmbed(e, "You dont have permission to use this command.");
            return;
        }
        try {
            List<Member> mentionedMembers = e.getMessage().getMentionedMembers();
            Member target = mentionedMembers.get(0);
            List<User> privateMessage = e.getMessage().getMentionedUsers();
            User privatemsg = privateMessage.get(0);
            for (int i = 2; i < args.length; i++) {
                reason += args[i] + " ";
            }
            sendPrivateMessageAndKick(privatemsg, e, target);
            EmbedUtils.sendEmbed(e, "User " + target.getAsMention() + " has been kicked by " + e.getAuthor().getAsMention() + "\nReason: " + reason);

        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            EmbedUtils.sendEmbed(e, "There is no such user in this guild");
        }
        reason = "";
    }
    private void sendPrivateMessageAndKick(User user, MessageReceivedEvent e, Member target) {
        user.openPrivateChannel().complete().sendMessage(new EmbedBuilder()
                .setDescription("You have been banned from the server " + e.getGuild().getName() + " by " + e.getAuthor().getAsMention() + "\nReason: " + reason)
                .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                .setColor(Color.ORANGE)
                .build()).complete();
        target.kick().queue();
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(Emily.BOT_PREFIX + "kick");
    }

    @Override
    public String getDescription() {
        return "kick a member";
    }

    @Override
    public String getCategory() {
        return "Moderation";
    }

}