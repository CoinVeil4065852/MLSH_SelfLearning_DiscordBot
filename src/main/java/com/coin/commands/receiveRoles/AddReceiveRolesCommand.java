package com.coin.commands.receiveRoles;

import com.coin.Config;
import com.coin.Main;
import com.coin.commands.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;

public class AddReceiveRolesCommand extends SlashCommand {
    Config config;

    public AddReceiveRolesCommand() {
        config = Main.config;
    }

    @Override
    public String getDescription() {
        return "add emoji role";
    }

    @Override
    public String getName() {
        return "roleadd";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]{new OptionData(OptionType.STRING, "emoji", "only emoji is allowed", true),
                new OptionData(OptionType.ROLE, "role", "the role you want to use", true)};
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        long guildId = event.getGuild().getIdLong();
        String emoji = event.getOption("emoji").getAsString();
        boolean isEmoji = false;
        Message m = event.getChannel().sendMessage("測試Emoji").complete();
        try {
            m.addReaction(Emoji.fromUnicode(emoji)).complete();
            isEmoji = true;
        } catch (Exception e) {
        }
        m.delete().queue();

        EmbedBuilder eb = new EmbedBuilder();

        if (!isEmoji) {
            eb.setColor(Color.RED).setTitle("設定失敗").setDescription("請使用Emoji");
            event.replyEmbeds(eb.build()).queue();
            return;
        }
        Role role = event.getOption("role").getAsRole();
        config.addEmojiRole(guildId, Emoji.fromUnicode(emoji).getAsCodepoints(), role.getIdLong());

        eb.setTitle("設定成功").setDescription("已將 " + emoji + " == " + role.getName() + " 新增").setColor(Color.GREEN);
        event.replyEmbeds(eb.build()).queue();
    }
}
