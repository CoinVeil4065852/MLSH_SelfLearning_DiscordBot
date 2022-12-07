package coin.com.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class YoCommand extends SlashCommand{
    @Override
    public String getDescription() {
        return "say yo to whoever execute";
    }

    @Override
    public String getName() {
        return "yo";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[0];
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        event.reply("Yo").queue();
    }

}
