package coin.com.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WelcomeMessageListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        event.getJDA().getTextChannelById(1022700099987582976L).sendMessageEmbeds(new EmbedBuilder().setTitle("Welcome" + event.getMember().getUser().getName()
        ).build()).queue();
    }

}
