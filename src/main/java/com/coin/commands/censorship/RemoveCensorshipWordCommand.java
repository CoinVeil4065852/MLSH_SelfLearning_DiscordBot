package com.coin.commands.censorship;

import com.coin.Config;
import com.coin.Main;
import com.coin.commands.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;

public class RemoveCensorshipWordCommand extends SlashCommand {
    Config config;

    public RemoveCensorshipWordCommand() {
        config = Main.config;
    }

    @Override
    public String getDescription() {
        return "remove censorship word";
    }

    @Override
    public String getName() {
        return "censorshiprm";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]{new OptionData(OptionType.STRING, "word", "the word you want to remove", true)};
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        long guildId = event.getGuild().getIdLong();
        boolean removed = config.removeCensorshipWords(guildId, event.getOption("word").getAsString());
        EmbedBuilder eb = new EmbedBuilder();
        if (removed)
            eb.setTitle("設定成功").setDescription("已將 \"" + event.getOption("word").getAsString() + "\" 移除").setColor(Color.GREEN);
        else
            eb.setTitle("設定失敗").setDescription("未找到 \"" + event.getOption("word").getAsString() + "\" ").setColor(Color.RED);
        event.replyEmbeds(eb.build()).queue();
    }
}
