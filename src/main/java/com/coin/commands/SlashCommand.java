package com.coin.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public abstract class SlashCommand {
    public abstract String getDescription();

    public abstract String getName();

    public abstract OptionData[] getOptions();

    public abstract void onExecute(SlashCommandInteractionEvent event);
}
