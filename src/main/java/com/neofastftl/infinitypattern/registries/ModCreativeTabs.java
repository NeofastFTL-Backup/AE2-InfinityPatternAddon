package com.neofastftl.infinitypattern.registries;

import com.neofastftl.infinitypattern.InfinityPattern;
import com.neofastftl.infinitypattern.guidebook.IFPATGuide;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeModeTab INFINITY_PATTERN = new CreativeModeTab(InfinityPattern.MODID) {
        @Override
        public ItemStack makeIcon() {
            return ModItems.ITEM_INFINITE_EMPTY_PATTERN.get().getDefaultInstance();
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            super.fillItemList(items);
            var guideItem = IFPATGuide.createGuideItem();
            if (!guideItem.isEmpty()) {
                items.add(guideItem);
            }
        }
    };

    private ModCreativeTabs() {
    }
}
