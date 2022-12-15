package com.coin.commands.censorship;

import com.coin.Config;
import com.coin.Main;
import com.coin.commands.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;

public class ToggleCensorshipCommand extends SlashCommand {
    Config config;

    public ToggleCensorshipCommand() {
        config = Main.config;
    }

    @Override
    public String getDescription() {
        return "toggle censorship feature";
    }

    @Override
    public String getName() {
        return "censorship";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[0];
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        long guildId = event.getGuild().getIdLong();
        config.setCensorship(guildId, !config.isCensorship(guildId));
        EmbedBuilder eb = new EmbedBuilder();
        if (config.isCensorship(guildId))
            eb.setTitle("設定成功").setDescription("已將 違禁字審查 開啟").setColor(Color.green);
        else eb.setTitle("設定成功").setDescription("已將 違禁字審查 關閉").setColor(Color.RED);
        event.replyEmbeds(eb.build()).queue();
    }
}
