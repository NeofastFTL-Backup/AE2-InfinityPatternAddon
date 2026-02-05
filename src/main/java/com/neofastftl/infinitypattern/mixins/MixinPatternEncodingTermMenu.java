package com.neofastftl.infinitypattern.mixins;

import appeng.parts.encoding.PatternEncodingLogic;
import com.neofastftl.infinitypattern.InfinityPattern;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

import appeng.api.storage.ITerminalHost;
import appeng.menu.me.common.MEStorageMenu;
import appeng.menu.me.items.PatternEncodingTermMenu;
import appeng.menu.slot.RestrictedInputSlot;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PatternEncodingTermMenu.class, remap = false)
public abstract class MixinPatternEncodingTermMenu extends MEStorageMenu {

    @Final
    @Shadow
    private RestrictedInputSlot encodedPatternSlot;

    @Shadow
    private RestrictedInputSlot blankPatternSlot;

    private boolean wasInfinitePattern = false;

    @Inject(method = "encode", at = @At("HEAD"))
    private void onEncodePatternHead(CallbackInfo ci) {
        var blank = this.blankPatternSlot.getItem();
        if (blank.is(InfinityPattern.ITEM_INFINITE_EMPTY_PATTERN.get())) {
            wasInfinitePattern = true;
        } else {
            wasInfinitePattern = false;
        }
    }

    @Inject(method = "isPattern", at = @At("HEAD"), cancellable = true)
    private void onIsPattern(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(InfinityPattern.ITEM_INFINITE_EMPTY_PATTERN.get())) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "encode", at = @At("RETURN"))
    private void onEncodePatternReturn(CallbackInfo ci) {
        if (wasInfinitePattern) {
            var blank = this.blankPatternSlot.getItem();
            if (blank.isEmpty()) {
                this.blankPatternSlot.set(InfinityPattern.ITEM_INFINITE_EMPTY_PATTERN.get().getDefaultInstance());
            } else if (blank.is(InfinityPattern.ITEM_INFINITE_EMPTY_PATTERN.get())) {
                blank.setCount(1);
            }
            wasInfinitePattern = false;
        }
    }

    public MixinPatternEncodingTermMenu(MenuType<?> menuType, int id, Inventory ip, ITerminalHost host, PatternEncodingLogic encodingLogic) {
        super(menuType, id, ip, host);
    }
}
