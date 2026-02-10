package com.crownSmp.mixin;

import com.crownSmp.CrownSMP;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(PlayerInventory.class)
public abstract class SoulBoundDeathMixin {

	// Storage to keep items safe while vanilla drops everything else
	private final Map<Integer, ItemStack> savedSoulBoundItems = new HashMap<>();

	@Inject(method = "dropAll", at = @At("HEAD"))
	private void saveSoulBoundBeforeDrop(CallbackInfo ci) {
		PlayerInventory inventory = (PlayerInventory) (Object) this;

		// Loop through the entire inventory (main, armor, and offhand)
		for (int i = 0; i < inventory.size(); i++) {
			ItemStack stack = inventory.getStack(i);
			if (!stack.isEmpty() && stack.contains(CrownSMP.SOUL_BOUND)) {
				// Store a copy and temporarily empty the slot so it isn't dropped
				savedSoulBoundItems.put(i, stack.copy());
				inventory.setStack(i, ItemStack.EMPTY);
			}
		}
	}

	@Inject(method = "dropAll", at = @At("TAIL"))
	private void restoreSoulBoundAfterDrop(CallbackInfo ci) {
		PlayerInventory inventory = (PlayerInventory) (Object) this;
		// Put the protected items back into the player's inventory
		savedSoulBoundItems.forEach(inventory::setStack);
		savedSoulBoundItems.clear();
	}
}