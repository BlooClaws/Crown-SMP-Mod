package com.crownSmp.mixin;

import com.crownSmp.CrownSMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class SoulBoundRespawnMixin {
    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void restoreSoulBound(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        if (!alive) { // This confirms the 'copy' is happening because of a death
            ServerPlayerEntity newPlayer = (ServerPlayerEntity) (Object) this;
            for (int i = 0; i < oldPlayer.getInventory().size(); i++) {
                ItemStack stack = oldPlayer.getInventory().getStack(i);
                if (stack.contains(CrownSMP.SOUL_BOUND)) {
                    newPlayer.getInventory().setStack(i, stack.copy());
                }
            }
        }
    }
}