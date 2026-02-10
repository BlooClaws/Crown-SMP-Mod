package com.crownSmp.mixin.client;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class CrownClientMixin {
	@Unique
    private boolean initialized = false;

	@Inject(at = @At("HEAD"), method = "tick")
	private void onTick(CallbackInfo info) {
		if (!initialized) {
			System.out.println("Crown SMP: Initialized after first tick!");
			initialized = true;
		}
	}
}