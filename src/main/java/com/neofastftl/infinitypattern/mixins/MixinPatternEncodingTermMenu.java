package com.neofastftl.infinitypattern.mixins;

import appeng.core.definitions.AEItems;
import appeng.parts.encoding.PatternEncodingLogic;
import com.llamalad7.mixinextras.sugar.Local;

import com.neofastftl.infinitypattern.InfinityPattern;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

import appeng.api.crafting.PatternDetailsHelper;
import appeng.api.stacks.GenericStack;
import appeng.api.storage.ITerminalHost;
import appeng.menu.me.common.MEStorageMenu;
import appeng.menu.me.items.PatternEncodingTermMenu;
import appeng.menu.slot.RestrictedInputSlot;

@Mixin(PatternEncodingTermMenu.class)
public class MixinPatternEncodingTermMenu extends MEStorageMenu {

    @Final
    @Shadow(remap = false)
    private RestrictedInputSlot encodedPatternSlot;

    private void onEncodePattern(
            CallbackInfoReturnable<ItemStack> cir,
            @Local(ordinal = 0) GenericStack[] in,
            @Local(ordinal = 1) GenericStack[] out) {

            var encodeOutput = this.encodedPatternSlot.getItem();

            // first check the output slots, should either be null, or a pattern (encoded or otherwise)
            if (!encodeOutput.isEmpty()
                    && !PatternDetailsHelper.isEncodedPattern(encodeOutput)
                    && !InfinityPattern.ITEM_INFINITYPATTERN.is(encodeOutput.getItemHolder().getDelegate())) {
                clearPattern();
            }
    }
    private void clearPattern() {
        var encodedPattern = this.encodedPatternSlot.getItem();
        if (PatternDetailsHelper.isEncodedPattern(encodedPattern)) {
            this.encodedPatternSlot.set(
                    InfinityPattern.ITEM_INFINITYPATTERN.toStack(encodedPattern.getCount()));
        }
    }
    public MixinPatternEncodingTermMenu(MenuType<?> menuType, int id, Inventory ip, ITerminalHost host, PatternEncodingLogic encodingLogic) {
        super(menuType, id, ip, host);
    }
}
