package com.coin.listeners;

import com.coin.Config;
import com.coin.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class CensorshipListener extends ListenerAdapter {

    Config config;

    public CensorshipListener() {
        config = Main.config;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().getIdLong() == event.getJDA().getSelfUser().getIdLong()) return;
        if (!config.isCensorship(event.getGuild().getIdLong())) return;
        for (String s : config.getCensorshipWords(event.getGuild().getIdLong())) {
            if (event.getMessage().getContentRaw().toLowerCase().contains(s)) {
                String content = event.getMessage().getContentRaw();
                event.getChannel().deleteMessageById(event.getMessageId()).queue();
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("警告").setColor(Color.RED);
                eb.addField("使用者", event.getAuthor().getAsMention(), false);
                eb.addField("訊息", "您的發言\"" + content + "\"含有違禁字 已自動刪除", false);
                event.getChannel().sendMessageEmbeds(eb.build()).queue();
                return;
            }
        }
    }

}
