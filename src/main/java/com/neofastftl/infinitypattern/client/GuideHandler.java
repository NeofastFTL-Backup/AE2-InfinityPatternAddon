package com.neofastftl.infinitypattern.client;

import com.neofastftl.infinitypattern.InfinityPattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;

@EventBusSubscriber(modid = InfinityPattern.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class GuideHandler {

    private static final ResourceLocation GUIDE_PAGE = ResourceLocation.fromNamespaceAndPath(InfinityPattern.MODID, "ifpat_intro/ifpat-index.md");

    @SubscribeEvent
    public static void onKeyPressed(ScreenEvent.KeyPressed.Post event) {
        InfinityPattern.LOGGER.info("[DEBUG_LOG] Key pressed: {}, G is {}", event.getKeyCode(), GLFW.GLFW_KEY_G);
        if (event.getKeyCode() == GLFW.GLFW_KEY_G) {
            Screen screen = event.getScreen();
            InfinityPattern.LOGGER.info("[DEBUG_LOG] Screen: {}", screen.getClass().getName());
            if (screen instanceof AbstractContainerScreen<?> containerScreen) {
                Slot slot = containerScreen.getSlotUnderMouse();
                InfinityPattern.LOGGER.info("[DEBUG_LOG] Slot under mouse: {}", slot);
                if (slot != null && slot.hasItem()) {
                    ItemStack stack = slot.getItem();
                    InfinityPattern.LOGGER.info("[DEBUG_LOG] Item under mouse: {}", stack.getItem());
                    if (stack.is(InfinityPattern.ITEM_INFINITE_EMPTY_PATTERN.get())) {
                        InfinityPattern.LOGGER.info("[DEBUG_LOG] Matches Infinite Pattern! Opening guide...");
                        openGuide();
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    private static void openGuide() {
        try {
            InfinityPattern.LOGGER.info("[DEBUG_LOG] Attempting to open guide page: {}", GUIDE_PAGE);
            // In AE2 1.21.1, the guide is opened via appeng.client.guide.GuideHandler.openEntry
            Class<?> guideHandlerClass = Class.forName("appeng.client.guide.GuideHandler");
            var openEntryMethod = guideHandlerClass.getMethod("openEntry", ResourceLocation.class);
            openEntryMethod.invoke(null, GUIDE_PAGE);
            InfinityPattern.LOGGER.info("[DEBUG_LOG] Guide openEntry called successfully.");
        } catch (Exception e) {
            InfinityPattern.LOGGER.error("[DEBUG_LOG] Failed to open guide via GuideHandler: {}", e.getMessage());
            try {
                // Fallback for older AE2 versions or different class structure
                Class<?> guideScreenClass = Class.forName("appeng.client.guide.GuideScreen");
                var showMethod = guideScreenClass.getMethod("show", ResourceLocation.class);
                showMethod.invoke(null, GUIDE_PAGE);
                InfinityPattern.LOGGER.info("[DEBUG_LOG] Guide GuideScreen.show called successfully.");
            } catch (Exception e2) {
                InfinityPattern.LOGGER.error("[DEBUG_LOG] Failed to open guide via GuideScreen fallback: {}", e2.getMessage());
                Minecraft.getInstance().player.displayClientMessage(
                        net.minecraft.network.chat.Component.literal("Could not open AE2 guide: " + e.getMessage()),
                        false
                );
            }
        }
    }
}
