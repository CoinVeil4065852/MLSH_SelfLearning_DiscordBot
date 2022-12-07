package coin.com;

import coin.com.commands.CommandManager;
import coin.com.listeners.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.UnsupportedEncodingException;

public class Main {
    private static JDA jda;
    public static PPSListener ppsListener;

    public static void main(String[] args) throws UnsupportedEncodingException {
        Dotenv dotenv = Dotenv.load();
        ppsListener = new PPSListener();
        jda = JDABuilder.create(dotenv.get("BOT_TOKEN")
                , GatewayIntent.GUILD_MESSAGES
                , GatewayIntent.GUILD_MEMBERS
                , GatewayIntent.MESSAGE_CONTENT
                , GatewayIntent.GUILD_VOICE_STATES
                , GatewayIntent.GUILD_MESSAGE_REACTIONS).build();
        jda.addEventListener(new Listener()
                , new CommandManager()
                , new CensorshipListener()
                , new LoggerListener()
                , new WelcomeMessageListener()
                , new ReceiveRolesListener()
                , new CalculateFunctionListener()
                , ppsListener);

    }
}