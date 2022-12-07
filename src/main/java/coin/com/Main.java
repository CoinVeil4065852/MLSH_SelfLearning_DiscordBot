package coin.com;

import coin.com.listeners.CommandManager;
import coin.com.listeners.*;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {
    private static JDA jda;
    public static PPSListener ppsListener;
    public static Config config;

    public static void main(String[] args) throws IOException {
        Dotenv dotenv = Dotenv.load();
        config=new Config(new File("configs.json"));
        ppsListener = new PPSListener();
        jda = JDABuilder.create(dotenv.get("BOT_TOKEN")
                , GatewayIntent.GUILD_MESSAGES
                , GatewayIntent.GUILD_MEMBERS
                , GatewayIntent.MESSAGE_CONTENT
                , GatewayIntent.GUILD_VOICE_STATES
                , GatewayIntent.GUILD_MESSAGE_REACTIONS).build();
        jda.addEventListener(
                new CommandManager()
                , new CensorshipListener()
                , new LoggerListener()
                , new WelcomeMessageListener()
                , new ReceiveRolesListener()
                , new CalculateFunctionListener()
                , ppsListener);

    }
}