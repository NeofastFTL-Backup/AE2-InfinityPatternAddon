package com.neofastftl.infinitypattern.mixins;

import appeng.container.slot.RestrictedInputSlot;
import com.neofastftl.infinitypattern.registries.ModItems;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RestrictedInputSlot.class, remap = false)
public abstract class MixinRestrictedInputSlot {

    @Inject(method = "mayPlace", at = @At("HEAD"), cancellable = true)
    private void mayPlace(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() == ModItems.ITEM_INFINITE_EMPTY_PATTERN.get()) {
            cir.setReturnValue(true);
        }
    }
}
