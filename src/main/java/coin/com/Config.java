package coin.com;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Config {
    private Map<Long, GuildConfig> guildsConfigMap;
    private File file;

    public Config(File file) throws IOException {
        this.file = file;
        if (!file.exists()) file.createNewFile();
        Gson gson = new Gson();
        guildsConfigMap = gson.fromJson(new FileReader(file), new TypeToken<Map<Long, GuildConfig>>() {});
        if (guildsConfigMap == null) guildsConfigMap = new HashMap<>();
    }

    public void save() {
        Gson gson = new Gson();

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(gson.toJson(guildsConfigMap));
            fw.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Long getRoleReceiveMessageId(long guildId) {
        if (!guildsConfigMap.containsKey(guildId)) return null;
        return guildsConfigMap.get(guildId).getRoleReceiveMessageId();
    }

    public void setRoleReceiveMessageId(long guildId, long roleReceiveMessageId) {
        if (!guildsConfigMap.containsKey(guildId)) guildsConfigMap.put(guildId, new GuildConfig());
        guildsConfigMap.get(guildId).setRoleReceiveMessageId(roleReceiveMessageId);
        save();
    }
//TODO

    public Map getEmojiRoleMap(long guildId) {
        if (!guildsConfigMap.containsKey(guildId)) return null;
        return guildsConfigMap.get(guildId).getEmojiRoleMap();
    }


    public void addEmojiRole(long guildId, String emoji,Long role) {
        if (!guildsConfigMap.containsKey(guildId)) guildsConfigMap.put(guildId, new GuildConfig());
        guildsConfigMap.get(guildId).getEmojiRoleMap().put(emoji,role);
        save();
    }

    public boolean removeEmojiRole(long guildId, String emoji) {
        if (!guildsConfigMap.containsKey(guildId)) guildsConfigMap.put(guildId, new GuildConfig());
        boolean removed =guildsConfigMap.get(guildId).getEmojiRoleMap().remove(emoji)!=null;
        save();
        return removed;
    }

    public Long getWelcomeChannel(long guildId) {
        if (!guildsConfigMap.containsKey(guildId)) return null;
        return guildsConfigMap.get(guildId).getWelcomeChannel();
    }

    public void setWelcomeChannel(long guildId, Long welcomeChannel) {
        if (!guildsConfigMap.containsKey(guildId)) guildsConfigMap.put(guildId, new GuildConfig());
        guildsConfigMap.get(guildId).setWelcomeChannel(welcomeChannel);
        save();
    }
    public String getWelcomeMessage(long guildId) {
        if (!guildsConfigMap.containsKey(guildId)) return null;
        return guildsConfigMap.get(guildId).getWelcomeMessage();
    }

    public void setWelcomeMessage(long guildId, String welcomeMessage) {
        if (!guildsConfigMap.containsKey(guildId)) guildsConfigMap.put(guildId, new GuildConfig());
        guildsConfigMap.get(guildId).setWelcomeMessage(welcomeMessage);
        save();
    }

    public boolean isCensorship(long guildId) {
        if (!guildsConfigMap.containsKey(guildId)) return false;
        return guildsConfigMap.get(guildId).isCensorship();
    }

    public void setCensorship(long guildId, boolean censorship) {
        if (!guildsConfigMap.containsKey(guildId)) guildsConfigMap.put(guildId, new GuildConfig());
        guildsConfigMap.get(guildId).setCensorship(censorship);
        save();
    }

    public Set<String> getCensorshipWords(long guildId) {
        if (!guildsConfigMap.containsKey(guildId)) return null;
        return guildsConfigMap.get(guildId).getCensorshipWords();
    }

    public void addCensorshipWords(long guildId, String word) {
        if (!guildsConfigMap.containsKey(guildId)) guildsConfigMap.put(guildId, new GuildConfig());
        guildsConfigMap.get(guildId).getCensorshipWords().add(word);
        save();
    }

    public boolean removeCensorshipWords(long guildId, String word) {
        if (!guildsConfigMap.containsKey(guildId)) guildsConfigMap.put(guildId, new GuildConfig());
        boolean removed =guildsConfigMap.get(guildId).getCensorshipWords().removeIf(w -> w.equals(word));
        save();
        return removed;
    }


}
