package dev.rndmorris.salisarcana.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import dev.rndmorris.salisarcana.SalisArcana;
import io.netty.buffer.ByteBuf;

public class MessagePortableHoleSync implements IMessage, IMessageHandler<MessagePortableHoleSync, IMessage> {

    public int x;
    public int y;
    public int z;

    public MessagePortableHoleSync() {}

    public MessagePortableHoleSync(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    @Override
    public IMessage onMessage(MessagePortableHoleSync message, MessageContext ctx) {
        SalisArcana.proxy.handlePortableHoleSync(message.x, message.y, message.z);
        return null;
    }
}
