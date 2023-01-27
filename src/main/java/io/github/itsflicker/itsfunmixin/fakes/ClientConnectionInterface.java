package io.github.itsflicker.itsfunmixin.fakes;

import io.netty.channel.Channel;

public interface ClientConnectionInterface {
    void setChannel(Channel channel);
}
