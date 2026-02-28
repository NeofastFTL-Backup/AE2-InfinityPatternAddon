package com.neofastftl.infinitypattern.registries;

import com.neofastftl.infinitypattern.InfinityPattern;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, InfinityPattern.MODID);

    public static final RegistryObject<Item> ITEM_INFINITE_EMPTY_PATTERN = ITEMS.register("infinite_empty_pattern",
            () -> new Item(new Item.Properties().stacksTo(1)));
}
