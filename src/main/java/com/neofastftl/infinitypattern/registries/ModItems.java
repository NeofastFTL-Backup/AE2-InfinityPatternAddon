package com.neofastftl.infinitypattern.registries;

import com.neofastftl.infinitypattern.InfinityPattern;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = InfinityPattern.MODID)
public class ModItems {
    public static final Item ITEM_INFINITE_EMPTY_PATTERN = new Item()
            .setRegistryName(InfinityPattern.MODID, "infinite_empty_pattern")
            .setUnlocalizedName(InfinityPattern.MODID + ".infinite_empty_pattern")
            .setCreativeTab(ModCreativeTabs.INFINITY_PATTERN)
            .setMaxStackSize(1);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ITEM_INFINITE_EMPTY_PATTERN);
    }

    private ModItems() {
    }
}
