package com.neofastftl.infinitypattern.registries;

import com.neofastftl.infinitypattern.InfinityPattern;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InfinityPattern.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> INFINITY_PATTERN =
            CREATIVE_MODE_TABS.register("infinitypattern", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.infinitypattern"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.ITEM_INFINITE_EMPTY_PATTERN.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.ITEM_INFINITE_EMPTY_PATTERN.get());
                    }).build());
}
