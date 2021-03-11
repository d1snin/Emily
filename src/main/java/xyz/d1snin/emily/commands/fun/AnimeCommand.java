package xyz.d1snin.emily.commands.fun;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.d1snin.emily.Emily;
import sg4e.danbooru.Danbooru;
import sg4e.danbooru.DanbooruBuilder;
import sg4e.danbooru.Post;
import xyz.d1snin.emily.commands.Command;
import xyz.d1snin.emily.util.EmbedUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class AnimeCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if (e.getTextChannel().isNSFW()) {

            Danbooru danbooru = new DanbooruBuilder().build();
            List<Post> posts = danbooru.getPosts("", true);
            Random rand = new Random();
            Post randomElement = posts.get(rand.nextInt(posts.size()));
            String url = randomElement.getFileUrl();
            String tags = randomElement.getTagString().toString().replace("[", "").replace("]", "");
            EmbedUtils.sendEmbedWithImage(e, url, tags);
        } else {
            EmbedUtils.sendEmbed(e, "Please use this command in NSFW channel!");
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
    @Override
    public String getCategory() {
        return "Fun";
    }
}
