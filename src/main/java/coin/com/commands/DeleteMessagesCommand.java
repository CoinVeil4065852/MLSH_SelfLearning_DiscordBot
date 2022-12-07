package coin.com.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class DeleteMessagesCommand extends SlashCommand{

    @Override
    public String getDescription() {
        return "Delete all Messages in this channel";
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[0];
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        event.getChannel().purgeMessages(event.getChannel().getHistory().retrievePast(20).complete());
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("刪除成功").setDescription("已刪除訊息").setColor(Color.GREEN);
        event.replyEmbeds(eb.build()).queue();

    }
}
