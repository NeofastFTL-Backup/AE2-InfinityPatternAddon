package com.neofastftl.infinitypattern.mixins;

import com.neofastftl.infinitypattern.registries.ModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import appeng.container.me.items.PatternTermContainer;
import appeng.container.slot.RestrictedInputSlot;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PatternTermContainer.class, remap = false)
public abstract class MixinPatternEncodingTermMenu {

    @Final
    @Shadow
    private RestrictedInputSlot encodedPatternSlot;

    @Shadow
    private RestrictedInputSlot blankPatternSlot;

    private boolean wasInfinitePattern = false;

    @Inject(method = "encode", at = @At("HEAD"))
    private void onEncodePatternHead(CallbackInfo ci) {
        ItemStack blank = this.blankPatternSlot.getItem();
        wasInfinitePattern = blank.getItem() == ModItems.ITEM_INFINITE_EMPTY_PATTERN.get();
    }

    @Inject(method = "isPattern", at = @At("HEAD"), cancellable = true)
    private void onIsPattern(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() == ModItems.ITEM_INFINITE_EMPTY_PATTERN.get()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "encode", at = @At("RETURN"))
    private void onEncodePatternReturn(CallbackInfo ci) {
        if (wasInfinitePattern) {
            ItemStack blank = this.blankPatternSlot.getItem();
            if (blank.isEmpty()) {
                this.blankPatternSlot.set(ModItems.ITEM_INFINITE_EMPTY_PATTERN.get().getDefaultInstance());
            } else if (blank.getItem() == ModItems.ITEM_INFINITE_EMPTY_PATTERN.get()) {
                blank.setCount(1);
            }
            wasInfinitePattern = false;
        }
    }

}
