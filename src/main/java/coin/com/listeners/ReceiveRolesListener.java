package coin.com.listeners;

import coin.com.Config;
import coin.com.Main;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.*;
import java.util.Map;

public class ReceiveRolesListener extends ListenerAdapter {

    Config config;
    public ReceiveRolesListener(){
        config= Main.config;
    }
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getUser().getIdLong() == event.getJDA().getSelfUser().getIdLong()) return;
        Long guildId =event.getGuild().getIdLong();
        if (event.getMessageIdLong() != config.getRoleReceiveMessageId(guildId)) return;
        Map<String,Long> emojiRole= config.getEmojiRoleMap(guildId);
        emojiRole.forEach((emoji,role)->{
            if(event.getReaction().getEmoji().equals(Emoji.fromUnicode(emoji)))
                event.getGuild().addRoleToMember(event.getUser(),event.getGuild().getRoleById(role)).queue();
        });

    }
}
