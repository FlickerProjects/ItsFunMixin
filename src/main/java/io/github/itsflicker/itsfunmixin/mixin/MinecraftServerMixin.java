package io.github.itsflicker.itsfunmixin.mixin;

import io.github.itsflicker.itsfunmixin.Main;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Inject(method = "getServerModName", at = @At("RETURN"), cancellable = true, remap = false)
    public void getServerModName(CallbackInfoReturnable<String> cir) {
        if (Main.serverName != null) {
            cir.setReturnValue(Main.serverName);
        }
    }

}
