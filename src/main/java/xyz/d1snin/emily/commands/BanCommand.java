package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;
import xyz.d1snin.emily.util.EmbedUtils;
import java.util.Arrays;
import java.util.List;

public class BanCommand extends Command {
    private static String reason = "";
    private static int delDays;

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        List<Member> mentionedMembers = e.getMessage().getMentionedMembers();
        Member target = mentionedMembers.get(0);
        List<User> privateMessage = e.getMessage().getMentionedUsers();
        User privatemsg = privateMessage.get(0);
        try {
            if (args.length < 3) {
                EmbedUtils.sendEmbed(e, "Please use the following syntax: " + "`" + Emily.BOT_PREFIX + "ban` `<mentionTheUser>` `<delete user`s messages (yes or no)>` `<Reason>`");
                return;
            }
            if (!e.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                EmbedUtils.sendEmbed(e, "You dont have permission to use this command.");
                delDays = args[2].equalsIgnoreCase("yes") ? 7 : 0;
            } else {
                for (int i = 4; i < args.length; i++) {
                    reason += args[i] + " ";
                }
                    target.ban(delDays).queue();
                    EmbedUtils.sendEmbed(e, "User " + target.getAsMention() + " has been banned by " + e.getAuthor().getAsMention() + "\nReason: " + reason);
            }

        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            EmbedUtils.sendEmbed(e, "There is no such user in this guild");
        }
            sendPrivateMessage(privatemsg, e);
        reason = "";
    }
    private void sendPrivateMessage(User user, MessageReceivedEvent e) {
        user.openPrivateChannel().queue((channel) ->
                EmbedUtils.sendPrivateEmbed(channel, "You have been banned from the server " + e.getGuild().getName() + " by " + e.getAuthor().getAsMention() + "\nReason: " + reason));
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(Emily.BOT_PREFIX + "ban");
    }

    @Override
    public String getDescription() {
        return "Ban a member";
    }

    @Override
    public String getCategory() {
        return "Moderation";
    }

}