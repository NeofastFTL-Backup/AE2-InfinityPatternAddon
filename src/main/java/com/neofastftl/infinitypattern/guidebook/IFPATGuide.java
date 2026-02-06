package com.neofastftl.infinitypattern.guidebook;

import com.neofastftl.infinitypattern.InfinityPattern;
import guideme.Guide;
import net.minecraft.resources.ResourceLocation;

public class IFPATGuide {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(InfinityPattern.MODID, "book");
    private static Guide guide;

    public static void init() {
        guide = Guide.builder(ID)
                .folder("ifpat_guidebook")
                .build();
    }

    private IFPATGuide() {}
}
