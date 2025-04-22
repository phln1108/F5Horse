package com.phln1108.f5horses;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.Perspective;
import net.minecraft.entity.passive.HorseEntity;

public class F5HorsesClient implements ClientModInitializer {
    boolean wasMountedLastTick;

    @Override
    public void onInitializeClient() {
        // this way is inefficient
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null  || !client.isWindowFocused()) return;

            boolean isMounted = client.player.hasVehicle() &&
                    client.player.getVehicle() instanceof HorseEntity;


            if (isMounted && !wasMountedLastTick) {
                client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
            } else if (!isMounted && wasMountedLastTick) {
                client.options.setPerspective(Perspective.FIRST_PERSON);
            }

            wasMountedLastTick = isMounted;
        });
    }
}
