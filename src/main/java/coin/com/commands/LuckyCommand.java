package coin.com.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;

public class LuckyCommand extends SlashCommand{
    @Override
    public String getDescription() {
        return "tell you whether you are lucky or not";
    }

    @Override
    public String getName() {
        return "lucky";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[0];
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        EmbedBuilder eb =new EmbedBuilder();
        int number = (int) (Math.random() * 3);
        switch (number){
            case 0:
                eb.setColor(Color.RED);
                eb.setTitle("Bad");
                break;
            case 1:
                eb.setColor(Color.YELLOW);
                eb.setTitle("Normal");
                break;
            case 2:
                eb.setColor(Color.GREEN);
                eb.setTitle("Good");
                break;
        }
        event.replyEmbeds(eb.build()).queue();
    }
}
