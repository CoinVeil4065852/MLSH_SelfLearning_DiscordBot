package com.coin.listeners;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LoggerListener extends ListenerAdapter {
    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        event.getJDA().getTextChannelById(1032852796975878256L).sendMessage(event.getMember().getUser().getName() + "(" + event.getMember().getIdLong() + ") " + (event.getChannelJoined() != null ? ("joined " + event.getChannelJoined().getName() + "(" + event.getChannelJoined().getIdLong() + ")") : ("left " + event.getChannelLeft().getName() + "(" + event.getChannelLeft().getIdLong() + ")"))).queue();
    }
}
