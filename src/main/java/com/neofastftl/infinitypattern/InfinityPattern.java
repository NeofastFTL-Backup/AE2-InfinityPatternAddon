package com.neofastftl.infinitypattern;

import com.neofastftl.infinitypattern.guidebook.IFPATGuide;
import com.neofastftl.infinitypattern.registries.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(InfinityPattern.MODID)
public class InfinityPattern {
    public static final String MODID = "infinitypattern";

    public InfinityPattern() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(modEventBus);
        IFPATGuide.init();
    }
}
