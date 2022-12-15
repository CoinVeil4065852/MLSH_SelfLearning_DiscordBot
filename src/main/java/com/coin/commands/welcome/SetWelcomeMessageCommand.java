package com.coin.commands.welcome;

import com.coin.Config;
import com.coin.Main;
import com.coin.commands.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;

public class SetWelcomeMessageCommand extends SlashCommand {
    Config config;

    public SetWelcomeMessageCommand() {
        config = Main.config;
    }

    @Override
    public String getDescription() {
        return "set welcome message";
    }

    @Override
    public String getName() {
        return "welcomemsgset";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]{new OptionData(OptionType.STRING, "message", "the message you want to set")};
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        long guildId = event.getGuild().getIdLong();
        OptionMapping option = event.getOption("message");
        if (option == null) {
            config.setWelcomeMessage(guildId, null);
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("設定成功").setDescription("已移除歡迎訊息").setColor(Color.GREEN);
            event.replyEmbeds(eb.build()).queue();
            return;
        }
        String message = option.getAsString();
        config.setWelcomeMessage(guildId, message);
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("設定成功").setDescription("已將 \"" + message + "\" 設為歡迎訊息").setColor(Color.GREEN);
        event.replyEmbeds(eb.build()).queue();
    }
}
