package com.neofastftl.infinitypattern.registries;

import com.neofastftl.infinitypattern.InfinityPattern;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeTabs INFINITY_PATTERN = new CreativeTabs(InfinityPattern.MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.ITEM_INFINITE_EMPTY_PATTERN);
        }
    };

    private ModCreativeTabs() {
    }
}
