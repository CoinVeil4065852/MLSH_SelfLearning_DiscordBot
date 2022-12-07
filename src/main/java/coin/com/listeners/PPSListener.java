package coin.com.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;

public class PPSListener extends ListenerAdapter {
    private Map<Long, Long> userMessageMap;

    public PPSListener() {
        userMessageMap = new HashMap<>();
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (!userMessageMap.containsKey(event.getUserIdLong())) return;
        long messageId = event.getMessageIdLong();
        if (userMessageMap.get(event.getUserIdLong()) != messageId) return;
        Emoji emojis[] = {Emoji.fromUnicode("U+1F590"), Emoji.fromUnicode("U+270C"), Emoji.fromUnicode("U+270A")};
        int player = -1;
        for (int i = 0; i < 3; i++) {
            if (event.getReaction().getEmoji().equals(emojis[i])) {
                player = i;
                break;
            }
        }
        int npc = (int) (Math.random() * 3);
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("猜拳結果:")
                .addField(event.getUser().getName()+":", emojis[player].getAsReactionCode(), true)
                .addField("電腦:", emojis[npc].getAsReactionCode(), true).setColor(0xffea00);
        if (player == npc) eb.setDescription("平手!");
        else if ((player == 0 && npc == 2) || (player == 1 && npc == 0) || (player == 2 && npc == 1))
            eb.setDescription(event.getUser().getAsMention() + "贏了!");
        else eb.setDescription("電腦贏了!");

        event.getChannel().sendMessageEmbeds(eb.build()).queue();
        userMessageMap.remove(event.getUserIdLong());

    }

    public void addToQueue(long user, long message) {
        userMessageMap.put(user, message);
    }
}
