package xyz.d1snin.emily.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;

import java.awt.*;

public class EmbedUtils {
    public static void sendEmbed(MessageReceivedEvent e, String content) {
        e.getTextChannel().sendMessage(new EmbedBuilder()
                .setDescription(content)
                .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                .setColor(Color.ORANGE)
                .build()).queue();
    }
    public static void sendEmbedToCurrentChannel(MessageReceivedEvent e, TextChannel ch, String content) {
        ch.sendMessage(new EmbedBuilder()
                .setDescription(content)
                .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                .setColor(Color.ORANGE)
                .build()).queue();
    }
    public static void sendEmbedWithImage(MessageReceivedEvent e, String url, String content) {
        e.getTextChannel().sendMessage(new EmbedBuilder()
                .setDescription(content)
                .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                .setColor(Color.ORANGE)
                .setImage(url)
                .build()).queue();
    }
    public static void sendEmbedWithThumbnailPrivate(PrivateChannel ch, String url, String content) {
        ch.sendMessage(new EmbedBuilder()
                .setDescription(content)
                .setThumbnail(url)
                .setFooter(Emily.BOT_NAME, Emily.getAPI().getSelfUser().getAvatarUrl())
                .setColor(Color.ORANGE)
                .build()).queue();
    }

    public static void sendPrivateEmbed(PrivateChannel channel, String content) {
        channel.sendMessage(new EmbedBuilder()
                .setDescription(content)
                .setFooter(Emily.BOT_NAME, Emily.getAPI().getSelfUser().getAvatarUrl())
                .setColor(Color.ORANGE)
                .build()).queue();
    }
}