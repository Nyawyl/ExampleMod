package xyz.mangal.example.listener;

import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.event.*;
import xyz.mangal.example.config.Config;
import xyz.mangal.example.utils.MinecraftInstance;

public class Listener implements MinecraftInstance {
    @SubscribeEvent
    public void onRenderTick(RenderGameOverlayEvent.Post e) {
        if (mc.currentScreen != null) {
            return;
        }
        mc.fontRendererObj.drawStringWithShadow(EnumChatFormatting.LIGHT_PURPLE + Config.text, Config.posX, Config.posY, -1);
    }
}
