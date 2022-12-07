package coin.com.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager extends ListenerAdapter {
    private List<SlashCommand> commands;

    public CommandManager() {
        commands = new ArrayList<>();
        commands.add(new HiCommand());
        commands.add(new YoCommand());
        commands.add(new LuckyCommand());
        commands.add(new DeleteMessagesCommand());
        commands.add(new PSSCommand());

    }

    private void registerCommands(Guild guild) {
        List<CommandData> commandDatas = commands.stream().map(c->Commands.slash(c.getName(),c.getDescription()).addOptions(c.getOptions())).collect(Collectors.toList());
        guild.updateCommands().addCommands(commandDatas).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        commands.forEach(c -> {
            if (c.getName().equalsIgnoreCase(event.getName())) c.onExecute(event);
        });

    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        registerCommands(event.getGuild());
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        registerCommands(event.getGuild());
    }
}
