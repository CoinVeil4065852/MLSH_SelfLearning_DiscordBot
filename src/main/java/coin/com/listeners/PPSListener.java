package coin.com.listeners;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;

public class PPSListener extends ListenerAdapter {
    private Map<Long,Long> userMessageMap;
    public PPSListener(){
        userMessageMap=new HashMap<>();
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if(!userMessageMap.containsKey(event.getUserIdLong()))return;
        long messageId = event.getMessageIdLong();
        if(userMessageMap.get(event.getUserIdLong())!=messageId)return;
        Emoji emojis[]={Emoji.fromUnicode("U+270A"),Emoji.fromUnicode("U+270C"),Emoji.fromUnicode("U+1F590")};
        int player = -1;
        for(int i=0;i<3;i++){
            if(event.getReaction().getEmoji().equals(emojis[i])){
                player=i;
                break;
            }
        }
        int npc = (int) (Math.random()*3);
        event.getChannel().sendMessage(emojis[player].getAsReactionCode()+emojis[npc].getAsReactionCode()).queue();
        userMessageMap.remove(event.getUserIdLong());
        
    }
    public void addToQueue(long user,long message){
        userMessageMap.put(user,message);
    }
}
