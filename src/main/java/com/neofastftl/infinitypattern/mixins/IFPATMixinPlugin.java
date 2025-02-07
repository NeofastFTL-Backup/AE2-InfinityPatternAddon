package com.neofastftl.infinitypattern.mixins;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.fml.loading.moddiscovery.ModInfo;

public class IFPATMixinPlugin implements IMixinConfigPlugin {

    private static final Object2ObjectMap<String, String> MOD_MIXINS = new Object2ObjectOpenHashMap<>(
            new String[] {
                    "com.neofastftl.infinitypattern.mixins.MixinPatternEncodingTermMenu"
            },
            new String[] {"infinitypattern", "infinitypattern", "infinitypattern", "infinitypattern", "infinitypattern"},
            Object2ObjectOpenHashMap.DEFAULT_LOAD_FACTOR);

    @Override
    public void onLoad(String s) {
        String[] str;
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return !MOD_MIXINS.containsKey(mixinClassName) || isModLoaded(MOD_MIXINS.get(mixinClassName));
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {}

    private static boolean isModLoaded(String modId) {
        if (ModList.get() == null) {
            return LoadingModList.get().getMods().stream()
                    .map(ModInfo::getModId)
                    .anyMatch(modId::equals);
        } else {
            return ModList.get().isLoaded(modId);
        }
    }
}