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
            try {
            String danbooruTags = getArgAsString(args, false);
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription(getArgAsString(args, true))
                    .setColor(Color.ORANGE)
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .setImage(generateImage(danbooruTags))
                    .build()).queue();
            } catch (IllegalArgumentException illegalArgumentException) {
                e.getTextChannel().sendMessage(new EmbedBuilder()
                        .setDescription("Could not find an image for this tag.")
                        .setColor(Color.ORANGE)
                        .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                        .build()).queue();
            }
        } else {
                e.getTextChannel().sendMessage(new EmbedBuilder()
                        .setDescription("Please use this command in NSFW channel!")
                        .setColor(Color.ORANGE)
                        .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                        .build()).queue();
        }
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(Emily.BOT_PREFIX + "anime");
    }

    @Override
    public String getDescription() {
        return "Random anime picture from Danbooru (NSFW).щщзYou can search by tag - `'anime <tag>`";
    }
    private static String getArgAsString(String[] args, boolean quotes) {
        if (quotes) {
            return "`" + args[0] + "`";
        }
        return args[0];
    }
    private static String generateImage(String tags) {
        Danbooru danbooru = new DanbooruBuilder().build();
        List<Post> posts = danbooru.getPosts(tags, true);
        Random rand = new Random();
        Post randomElement = posts.get(rand.nextInt(posts.size()));
        return randomElement.getFileUrl();
    }
}
