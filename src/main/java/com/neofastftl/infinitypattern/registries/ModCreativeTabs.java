package com.neofastftl.infinitypattern.registries;

import com.neofastftl.infinitypattern.InfinityPattern;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs {
    public static final ItemGroup INFINITY_PATTERN = new ItemGroup(InfinityPattern.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ITEM_INFINITE_EMPTY_PATTERN.get());
        }
    };

    private ModCreativeTabs() {
    }
}
