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

            Danbooru danbooru = new DanbooruBuilder().build();
            List<Post> posts = danbooru.getPosts("", true);
            Random rand = new Random();
            Post randomElement = posts.get(rand.nextInt(posts.size()));
            String url = randomElement.getFileUrl();
            String tags = randomElement.getTagString().toString().replace("[", "").replace("]", "");

            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setDescription(tags)
                    .setColor(Color.ORANGE)
                    .setFooter(Emily.BOT_NAME, e.getJDA().getSelfUser().getAvatarUrl())
                    .setImage(url)
                    .build()).queue();
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
        return "Random anime picture from Danbooru (NSFW)";
    }
}
