package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;
import xyz.d1snin.emily.util.EmbedUtils;
import java.util.Arrays;
import java.util.List;

public class KickCommand extends Command {
    private static String reason = "";

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if (!e.getMember().hasPermission(Permission.KICK_MEMBERS)) {
            EmbedUtils.sendEmbed(e, "You dont have permission to use this command.");
            return;
        }
        if (args.length < 2) {
            EmbedUtils.sendEmbed(e, "Please use the following syntax: " + "`" + Emily.BOT_PREFIX + "kick` `<mentionTheUser>` `<Reason>`");
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
            target.kick().queue();
            EmbedUtils.sendEmbed(e, "User " + target.getAsMention() + " has been kicked by " + e.getAuthor().getAsMention() + "\nReason: " + reason);
            sendPrivateMessage(privatemsg, e);

        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            EmbedUtils.sendEmbed(e, "There is no such user in this guild");
        }
        reason = "";
    }
    private void sendPrivateMessage(User user, MessageReceivedEvent e) {
        user.openPrivateChannel().queue((channel) ->
                EmbedUtils.sendPrivateEmbed(channel, "You have been kicked from the server " + e.getGuild().getName() + " by " + e.getAuthor().getAsMention() + "\nReason: " + reason));
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(Emily.BOT_PREFIX + "kick");
    }

    @Override
    public String getDescription() {
        return "Kick a member";
    }

    @Override
    public String getCategory() {
        return "Moderation";
    }

}