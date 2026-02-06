package com.neofastftl.infinitypattern;

import com.neofastftl.infinitypattern.registries.ModItems;
import com.neofastftl.infinitypattern.registries.ModCreativeTabs;
import com.neofastftl.infinitypattern.guidebook.IFPATGuide;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(InfinityPattern.MODID)
public class InfinityPattern {
    public static final String MODID = "infinitypattern";

    public InfinityPattern(IEventBus modEventBus) {
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        IFPATGuide.init();
    }
}
