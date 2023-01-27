package io.github.itsflicker.itsfunmixin.mixin;

import com.mojang.authlib.GameProfile;
import io.github.itsflicker.itsfunmixin.fakes.ServerPlayerEntityInterface;
import io.github.itsflicker.itsfunmixin.helpers.EntityPlayerActionPack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayer_actionPackMixin implements ServerPlayerEntityInterface
{
    @Unique
    public EntityPlayerActionPack actionPack;
    @Override
    public EntityPlayerActionPack getActionPack()
    {
        return actionPack;
    }

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void onServerPlayerEntityContructor(MinecraftServer minecraftServer, ServerLevel serverLevel, GameProfile gameProfile, CallbackInfo ci)
    {
        this.actionPack = new EntityPlayerActionPack((ServerPlayer) (Object) this);
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void onTick(CallbackInfo ci)
    {
        actionPack.onUpdate();
    }
}
