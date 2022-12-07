package coin.com.listeners;

import coin.com.Config;
import coin.com.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WelcomeMessageListener extends ListenerAdapter {
    Config config;
    public WelcomeMessageListener(){
        config= Main.config;
    }
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Long guildId = event.getGuild().getIdLong();
        Long channelId =config.getWelcomeChannel(guildId);
        if(channelId==null)return;
        TextChannel welcomeChannel = event.getJDA().getTextChannelById(channelId);
        if(welcomeChannel==null)return;
        String welcomeMessage = config.getWelcomeMessage(guildId);
        if(welcomeMessage==null||welcomeMessage.isEmpty())return;
        welcomeChannel.sendMessageEmbeds(new EmbedBuilder().setTitle(welcomeMessage.replaceAll("%username",event.getMember().getUser().getName())
        ).build()).queue();
    }

}
