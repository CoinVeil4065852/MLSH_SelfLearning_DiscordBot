package coin.com.commands.welcome;

import coin.com.Config;
import coin.com.Main;
import coin.com.commands.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;

public class SetWelcomeChannelCommand extends SlashCommand {
    Config config;

    public SetWelcomeChannelCommand() {
        config = Main.config;
    }

    @Override
    public String getDescription() {
        return "set welcome channel";
    }

    @Override
    public String getName() {
        return "welcomechset";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]{new OptionData(OptionType.CHANNEL, "channel", "the channel you want to set")};
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        long guildId = event.getGuild().getIdLong();
        OptionMapping option = event.getOption("channel");
        if (option == null) {
            config.setWelcomeChannel(guildId,null);
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("設定成功").setDescription("已移除歡迎頻道").setColor(Color.GREEN);
            event.replyEmbeds(eb.build()).queue();
            return;
        }
        TextChannel channel = event.getOption("channel").getAsChannel().asTextChannel();
        config.setWelcomeChannel(guildId,channel.getIdLong());
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("設定成功").setDescription("已將 \"" + channel.getName() + "\" 設為歡迎頻道").setColor(Color.GREEN);
        event.replyEmbeds(eb.build()).queue();
    }
}
