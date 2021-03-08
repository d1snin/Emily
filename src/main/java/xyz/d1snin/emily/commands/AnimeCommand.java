package xyz.d1snin.emily.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;

import sg4e.danbooru.Danbooru;
import sg4e.danbooru.DanbooruBuilder;
import sg4e.danbooru.Post;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AnimeCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if (e.getTextChannel().isNSFW()) {
            String danbooruTags = getArgsAsString(args, false);
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription(getArgsAsString(args, true))
                    .setColor(Color.ORANGE)
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .setImage(generateImage(danbooruTags))
                    .build()).queue();
        }
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(Emily.BOT_PREFIX + "anime");
    }

    @Override
    public String getDescription() {
        return "Random anime picture from Danbooru (NSFW)";
    }
    private static String getArgsAsString(String[] args, boolean quotes) {
        StringBuilder res = new StringBuilder(" ");
        for (int i = 1; i < args.length; i++) {
            if (quotes) {
                res.append("`").append(args[i]).append("`").append(" ");
            } else {
                res.append(args[i]).append(" ");
            }
        }
        return res.toString();
    }
    private static String generateImage(String tags) {
        Danbooru danbooru = new DanbooruBuilder().build();
        List<Post> posts = danbooru.getPosts(tags, true);
        Random rand = new Random();
        Post randomElement = posts.get(rand.nextInt(posts.size()));
        return randomElement.getFileUrl();
    }
}
