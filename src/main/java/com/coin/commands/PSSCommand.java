package com.coin.commands;

import com.coin.Main;
import net.dv8tion.jda.api.EmbedBuilder;
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
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("選擇出拳").setColor(0xffea00);
        event.replyEmbeds(eb.build()).queue(re -> {
            re.retrieveOriginal().queue(m -> {
                        m.addReaction((Emoji.fromUnicode("U+1F590"))).queue();
                        m.addReaction((Emoji.fromUnicode("U+270C"))).queue();
                        m.addReaction((Emoji.fromUnicode("U+270A"))).queue();
                        Main.pssListener.addToQueue(event.getUser().getIdLong(), m.getIdLong());
                    }
            );
        });
    }
}
