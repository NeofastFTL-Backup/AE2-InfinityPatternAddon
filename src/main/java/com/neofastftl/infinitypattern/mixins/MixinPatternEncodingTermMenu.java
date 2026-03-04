package com.neofastftl.infinitypattern.mixins;

import appeng.container.implementations.ContainerPatternTerm;
import appeng.container.slot.SlotRestrictedInput;
import com.neofastftl.infinitypattern.registries.ModItems;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ContainerPatternTerm.class, remap = false)
public abstract class MixinPatternEncodingTermMenu {

    @Shadow
    private SlotRestrictedInput patternSlotIN;

    private boolean wasInfinitePattern = false;

    @Inject(method = "encode", at = @At("HEAD"))
    private void onEncodePatternHead(CallbackInfo ci) {
        ItemStack blank = this.patternSlotIN.getStack();
        wasInfinitePattern = blank.getItem() == ModItems.ITEM_INFINITE_EMPTY_PATTERN;
    }

    @Inject(method = "isPattern", at = @At("HEAD"), cancellable = true)
    private void onIsPattern(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() == ModItems.ITEM_INFINITE_EMPTY_PATTERN) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "encode", at = @At("RETURN"))
    private void onEncodePatternReturn(CallbackInfo ci) {
        if (wasInfinitePattern) {
            ItemStack blank = this.patternSlotIN.getStack();
            if (blank.isEmpty()) {
                this.patternSlotIN.putStack(new ItemStack(ModItems.ITEM_INFINITE_EMPTY_PATTERN));
            } else if (blank.getItem() == ModItems.ITEM_INFINITE_EMPTY_PATTERN) {
                blank.setCount(1);
            }
            wasInfinitePattern = false;
        }
    }
}
