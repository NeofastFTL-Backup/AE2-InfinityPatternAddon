package com.neofastftl.infinitypattern.mixins;

import appeng.api.crafting.PatternDetailsHelper;
import appeng.api.features.GridLinkables;
import appeng.api.implementations.items.ISpatialStorageCell;
import appeng.api.implementations.items.IStorageComponent;
import appeng.api.inventories.InternalInventory;
import appeng.api.stacks.GenericStack;
import appeng.api.storage.ITerminalHost;
import appeng.api.storage.StorageCells;
import appeng.api.storage.cells.ICellWorkbenchItem;
import appeng.api.upgrades.Upgrades;
import appeng.blockentity.crafting.IMolecularAssemblerSupportedPattern;
import appeng.blockentity.misc.InscriberRecipes;
import appeng.blockentity.misc.VibrationChamberBlockEntity;
import appeng.blockentity.qnb.QuantumBridgeBlockEntity;
import appeng.core.definitions.AEItems;
import appeng.menu.me.common.MEStorageMenu;
import appeng.menu.me.items.PatternEncodingTermMenu;
import appeng.menu.slot.AppEngSlot;
import appeng.menu.slot.RestrictedInputSlot;
import appeng.parts.encoding.PatternEncodingLogic;
import appeng.util.Platform;
import com.llamalad7.mixinextras.sugar.Local;
import com.neofastftl.infinitypattern.InfinityPattern;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static appeng.api.ids.AEItemIds.BLANK_PATTERN;

@Mixin(RestrictedInputSlot.class)
public class MixinRestrictedInputSlot extends AppEngSlot {

    @Final
    @Shadow(remap = false)
    private RestrictedInputSlot encodedPatternSlot;

    private void mayPlace(
            CallbackInfoReturnable<ItemStack> cir,
            @Local(ordinal = 0) GenericStack[] in,
            @Local(ordinal = 1) GenericStack[] out) {
    }
}
