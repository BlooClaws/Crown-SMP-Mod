package com.crownSmp.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class CrownMixin {
	// 'runServer' is the main loop entry in 1.21.11
	@Inject(at = @At("HEAD"), method = "runServer")
	private void init(CallbackInfo info) {
		System.out.println("Crown SMP Mod: Server loop started successfully!");
	}
}