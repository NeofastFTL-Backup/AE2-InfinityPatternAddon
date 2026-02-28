package com.neofastftl.infinitypattern.registries;

import com.neofastftl.infinitypattern.InfinityPattern;
import com.neofastftl.infinitypattern.guidebook.IFPATGuide;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InfinityPattern.MODID);

    public static final RegistryObject<CreativeModeTab> INFINITY_PATTERN =
            CREATIVE_MODE_TABS.register("infinitypattern", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.infinitypattern"))
                    .icon(() -> ModItems.ITEM_INFINITE_EMPTY_PATTERN.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.ITEM_INFINITE_EMPTY_PATTERN.get());
                        var guideItem = IFPATGuide.createGuideItem();
                        if (!guideItem.isEmpty()) {
                            output.accept(guideItem);
                        }
                    }).build());
}
