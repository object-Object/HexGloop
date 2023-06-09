package com.samsthenerd.hexgloop.misc;

import com.samsthenerd.hexgloop.HexGloop;
import com.samsthenerd.hexgloop.network.HexGloopNetwork;

import at.petrak.hexcasting.common.lib.HexSounds;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.PacketByteBuf;

public class CastingRingHelperClient {
    public static void handleCastingRingKeypress(KeyBinding keyBinding, MinecraftClient client){
        if(!keyBinding.isPressed()) return;
        if(client.player == null) return;
        if(!HexGloop.TRINKETY_INSTANCE.isCastingRingEquipped(client.player)) return;
        if (client.player.isSneaking()) {
            client.player.playSound(HexSounds.FAIL_PATTERN, 1f, 1f);
            // need to add a packet to the server to clear it
            NetworkManager.sendToServer(HexGloopNetwork.CLEAR_GRID_PACKET_ID, new PacketByteBuf(Unpooled.buffer()));
            return;
        }
        // need to add a packet to the server to open the casting screen
        NetworkManager.sendToServer(HexGloopNetwork.OPEN_CASTING_GRID_PACKET_ID, new PacketByteBuf(Unpooled.buffer()));
    }
}
