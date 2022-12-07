package coin.com.commands.receiveRoles;

import coin.com.Config;
import coin.com.Main;
import coin.com.commands.SlashCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.io.*;
import java.util.Map;

public class ReceiveRolesCommand extends SlashCommand {

    Config config;
    public ReceiveRolesCommand(){
        config= Main.config;
    }
    @Override
    public String getDescription() {
        return "send a message for collecting roles";
    }

    @Override
    public String getName() {
        return "role";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[0];
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
            Long guildId =event.getGuild().getIdLong();
            Map<String,Long> emojiRole= config.getEmojiRoleMap(guildId);

            Message m = event.getChannel().sendMessage("請選取下方符號領取身份組").complete();
            emojiRole.forEach((emoji,role)->m.addReaction(Emoji.fromUnicode(emoji)).queue());

            config.setRoleReceiveMessageId(event.getGuild().getIdLong(),m.getIdLong());
            event.reply("finished").queue(q->q.deleteOriginal().queue());



        }


}
