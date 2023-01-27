package io.github.itsflicker.itsfunmixin.mixin;

import io.github.itsflicker.itsfunmixin.fakes.ClientConnectionInterface;
import io.netty.channel.Channel;
import net.minecraft.network.Connection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Connection.class)
public abstract class ConnectionMixin implements ClientConnectionInterface
{
//    // Add to the packet counter whenever a packet is received.
//    @Inject(method = "channelRead0", at = @At("HEAD"))
//    private void packetInCount(ChannelHandlerContext channelHandlerContext_1, Packet<?> packet_1, CallbackInfo ci)
//    {
//        PacketCounter.totalIn++;
//    }
//
//    // Add to the packet counter whenever a packet is sent.
//    @Inject(method = "sendPacket", at = @At("HEAD"))
//    private void packetOutCount(Packet<?> packet, @Nullable PacketSendListener packetSendListener, CallbackInfo ci)
//    {
//        PacketCounter.totalOut++;
//    }

    @Override
    @Accessor //Compat with adventure-platform-fabric
    public abstract void setChannel(Channel channel);
}
