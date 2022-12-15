package com.coin.commands.censorship;

import com.coin.Main;
import com.coin.commands.SlashCommand;
import com.coin.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;

public class AddCensorshipWordCommand extends SlashCommand {
    Config config;

    public AddCensorshipWordCommand() {
        config = Main.config;
    }

    @Override
    public String getDescription() {
        return "add censorship word";
    }

    @Override
    public String getName() {
        return "censorshipadd";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]{new OptionData(OptionType.STRING, "word", "the word you want to add", true)};
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        long guildId = event.getGuild().getIdLong();
        config.addCensorshipWords(guildId, event.getOption("word").getAsString());
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("設定成功").setDescription("已將 \"" + event.getOption("word").getAsString() + "\" 新增為違禁字").setColor(Color.GREEN);
        event.replyEmbeds(eb.build()).queue();
    }
}
