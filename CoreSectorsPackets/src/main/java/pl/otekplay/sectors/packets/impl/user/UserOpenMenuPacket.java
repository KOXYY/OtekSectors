package pl.otekplay.sectors.packets.impl.user;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.otekplay.sectors.packets.Packet;
import pl.otekplay.sectors.packets.PacketHandler;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserOpenMenuPacket extends Packet {
    private String uuidString;
    private int id;

    @Override
    public void write(ByteBuf bb) {
        writeString(bb, uuidString);
        bb.writeInt(id);
    }

    @Override
    public void read(ByteBuf bb) {
        uuidString = readString(bb);
        id = bb.readInt();
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle(this);
    }
}
