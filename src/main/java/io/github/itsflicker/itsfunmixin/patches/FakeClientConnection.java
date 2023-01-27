package io.github.itsflicker.itsfunmixin.patches;

import io.github.itsflicker.itsfunmixin.fakes.ClientConnectionInterface;
import io.netty.channel.embedded.EmbeddedChannel;
import net.minecraft.network.Connection;
import net.minecraft.network.PacketDecoder;
import net.minecraft.network.PacketEncoder;
import net.minecraft.network.protocol.PacketFlow;

public class FakeClientConnection extends Connection
{
    public FakeClientConnection(PacketFlow p)
    {
        super(p);
        // compat with adventure-platform-fabric. This does NOT trigger other vanilla handlers for establishing a channel
        // also makes #isOpen return true, allowing enderpearls to teleport fake players
        ((ClientConnectionInterface)this).setChannel(new EmbeddedChannel());
        this.channel.pipeline().addLast("packet_handler", new FakeClientConnection());
        this.channel.pipeline().addLast("encoder", new PacketEncoder(PacketFlow.SERVERBOUND));
        this.channel.pipeline().addLast("decoder", new PacketDecoder(PacketFlow.CLIENTBOUND));
    }

    private FakeClientConnection()
    {
        super(PacketFlow.CLIENTBOUND);
        ((ClientConnectionInterface)this).setChannel(new EmbeddedChannel());
    }

    @Override
    public void setReadOnly()
    {
    }

    @Override
    public void handleDisconnection()
    {
    }
}