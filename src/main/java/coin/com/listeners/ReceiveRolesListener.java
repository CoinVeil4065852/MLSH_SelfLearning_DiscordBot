package coin.com.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.*;

public class ReceiveRolesListener extends ListenerAdapter {
    Long mid;

    @Override
    public void onReady(ReadyEvent event) {
        File config = new File("config.txt");
        if (config.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(config));
                mid = Long.parseLong(br.readLine());
                event.getJDA().getTextChannelById(1037914104289046620L).retrieveMessageById(mid).complete();
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }catch (ErrorResponseException e){

            }
        }


        Message m = event.getJDA().getTextChannelById(1037914104289046620L).sendMessage("請選取下方符號領取身份組").complete();
        m.addReaction(Emoji.fromUnicode("U+2757")).queue();
        m.addReaction(Emoji.fromUnicode("U+2753")).queue();
        m.addReaction(Emoji.fromUnicode("U+1F602")).queue();
        mid = m.getIdLong();


        try {
            config.createNewFile();
            FileWriter fw = new FileWriter(config);
            fw.write(String.valueOf(mid));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getUser().getIdLong() == event.getJDA().getSelfUser().getIdLong()) return;
        if (event.getMessageIdLong() != mid) return;
        if (event.getReaction().getEmoji().equals(Emoji.fromUnicode("U+2757")))
            event.getGuild().addRoleToMember(event.getUser(), event.getJDA().getRoleById(1037923229060317194L)).queue();
        if (event.getReaction().getEmoji().equals(Emoji.fromUnicode("U+2753")))
            event.getGuild().addRoleToMember(event.getUser(), event.getJDA().getRoleById(1037926318148419644L)).queue();
        if (event.getReaction().getEmoji().equals(Emoji.fromUnicode("U+1F602")))
            event.getGuild().addRoleToMember(event.getUser(), event.getJDA().getRoleById(1037926436746559548L)).queue();
    }
}
