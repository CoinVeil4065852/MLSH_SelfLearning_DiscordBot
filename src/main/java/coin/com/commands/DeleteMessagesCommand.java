package coin.com.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

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
        event.getChannel().purgeMessages(event.getChannel().getHistoryFromBeginning(100).complete().getRetrievedHistory());
        event.reply("Delete Completed").queue();;

    }
}
