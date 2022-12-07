package coin.com.commands.receiveRoles;

import coin.com.Config;
import coin.com.Main;
import coin.com.commands.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;

public class RemoveReceiveRolesCommand extends SlashCommand {
    Config config;

    public RemoveReceiveRolesCommand() {
        config = Main.config;
    }

    @Override
    public String getDescription() {
        return "remove emoji role";
    }

    @Override
    public String getName() {
        return "rolerm";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]{new OptionData(OptionType.STRING, "emoji", "only emoji is allowed", true),};
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) {
        long guildId = event.getGuild().getIdLong();
        String emoji = event.getOption("emoji").getAsString();
        boolean removed= config.removeEmojiRole(guildId,Emoji.fromUnicode(emoji).getAsCodepoints());
        EmbedBuilder eb = new EmbedBuilder();
        if (!removed) {
            eb.setColor(Color.RED).setTitle("移除失敗").setDescription("未找到此Emoji");
            event.replyEmbeds(eb.build()).queue();
            return;
        }


        eb.setTitle("設定成功").setDescription("已將 " + emoji  + " 移除" ).setColor(Color.GREEN);
        event.replyEmbeds(eb.build()).queue();
    }
}
