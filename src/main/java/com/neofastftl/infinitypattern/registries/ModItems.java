package com.neofastftl.infinitypattern.registries;

import com.neofastftl.infinitypattern.InfinityPattern;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(InfinityPattern.MODID);

    public static final DeferredItem<Item> ITEM_INFINITE_EMPTY_PATTERN = ITEMS.register("infinite_empty_pattern",
            () -> new Item(new Item.Properties().stacksTo(1)));
}
