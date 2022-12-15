package com.coin.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class HiCommand extends SlashCommand {
    @Override
    public String getDescription() {
        return "say hi to whoever execute";
    }

    @Override
    public String getName() {
        return "hi";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[0];
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        event.reply("Hi").queue();
    }

}
