package xyz.mangal.example.utils;

import net.minecraft.util.ChatComponentText;

public class Utils implements MinecraftInstance {
    public static void sendMessage(String msg) {
        if (nullCheck()) {
            String txt = replace("&7[&dEX&7]&r " + msg);
            mc.thePlayer.addChatMessage(new ChatComponentText(txt));
        }
    }
    public static boolean nullCheck() {
        return mc.thePlayer != null && mc.theWorld != null;
    }
    public static String replace(String text) {
        return text.replace("&", "\u00a7").replace("%and", "&");
    }
}
