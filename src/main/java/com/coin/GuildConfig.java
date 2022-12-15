package com.coin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GuildConfig {
    private Long roleReceiveMessageId;
    private Map<String, Long> emojiRoleMap;

    private Long welcomeChannel;
    private String welcomeMessage;

    private Set<String> censorshipWords;

    private boolean censorship;


    //    public GuildConfig(long roleReceiveMessageId, Map emojiRoleMap, String welcomeMessage, Set<String> censorshipWords) {
//        this.roleReceiveMessageId = roleReceiveMessageId;
//        this.emojiRoleMap = emojiRoleMap;
//        this.welcomeMessage = welcomeMessage;
//        this.censorshipWords = censorshipWords;
//    }
    public GuildConfig() {
        this.roleReceiveMessageId = null;
        this.emojiRoleMap = new HashMap<>();
        this.welcomeChannel = null;
        this.welcomeMessage = "Welcome %username !";
        this.censorship = false;
        this.censorshipWords = new HashSet<>();
    }

    public Long getRoleReceiveMessageId() {
        return roleReceiveMessageId;
    }

    public void setRoleReceiveMessageId(long roleReceiveMessageId) {
        this.roleReceiveMessageId = roleReceiveMessageId;
    }

    public Map<String, Long> getEmojiRoleMap() {
        return emojiRoleMap;
    }

    public void setEmojiRoleMap(Map emojiRoleMap) {
        this.emojiRoleMap = emojiRoleMap;
    }

    public Long getWelcomeChannel() {
        return welcomeChannel;
    }

    public void setWelcomeChannel(Long welcomeChannel) {
        this.welcomeChannel = welcomeChannel;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public boolean isCensorship() {
        return censorship;
    }

    public void setCensorship(boolean censorship) {
        this.censorship = censorship;
    }

    public Set<String> getCensorshipWords() {
        return censorshipWords;
    }

    public void setCensorshipWords(Set<String> censorshipWords) {
        this.censorshipWords = censorshipWords;
    }


}
