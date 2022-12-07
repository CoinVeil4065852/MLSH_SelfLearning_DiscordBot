package coin.com.commands;

import coin.com.Main;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class PSSCommand extends SlashCommand {
    @Override
    public String getDescription() {
        return "play paper scissors stone with bot";
    }

    @Override
    public String getName() {
        return "pss";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[0];
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        event.getChannel().sendMessage("chose").queue(m -> {
            m.addReaction((Emoji.fromUnicode("U+270A"))).queue();
            m.addReaction((Emoji.fromUnicode("U+270C"))).queue();
            m.addReaction((Emoji.fromUnicode("U+1F590"))).queue();
            Main.ppsListener.addToQueue(event.getUser().getIdLong(),m.getIdLong());
        });


    }
}
