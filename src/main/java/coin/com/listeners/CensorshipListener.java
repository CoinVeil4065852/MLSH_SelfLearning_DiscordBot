package coin.com.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.UnsupportedEncodingException;

public class CensorshipListener extends ListenerAdapter {

    private final String[] SENS_WORDS = {"笨蛋", "test"};


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().getIdLong() == event.getJDA().getSelfUser().getIdLong()) return;
        for (String s : SENS_WORDS) {
            if (event.getMessage().getContentRaw().contains(s)) {
                String content = event.getMessage().getContentRaw();
                event.getChannel().deleteMessageById(event.getMessageId()).queue();
                EmbedBuilder eb = new EmbedBuilder();

                eb.setTitle("警告").setColor(Color.RED);

                eb.addField("使用者", event.getAuthor().getAsTag(), false);
                eb.addField("訊息", "您的發言\"" + content + "\"含有違禁字 已自動刪除", false);
                event.getChannel().sendMessageEmbeds(eb.build()).queue();
                return;
            }
        }
    }

}
