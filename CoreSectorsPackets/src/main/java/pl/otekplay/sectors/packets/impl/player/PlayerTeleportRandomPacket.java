package pl.otekplay.sectors.packets.impl.player;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.otekplay.sectors.packets.Packet;
import pl.otekplay.sectors.packets.PacketHandler;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerTeleportRandomPacket extends Packet {
    private String uuidPlayer;

    @Override
    public void write(ByteBuf bb) {
        writeString(bb, uuidPlayer);
    }

    @Override
    public void read(ByteBuf bb) {
        uuidPlayer = readString(bb);
    }

    @Override
    public void handle(PacketHandler handler) {
        handler.handle(this);
    }
}
