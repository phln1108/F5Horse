package com.phln1108.f5horses;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.option.Perspective;

public class F5HorsesClient implements ClientModInitializer {
    boolean wasMountedLastTick;
    Perspective last_perspective;

    @Override
    public void onInitializeClient() {
        // this way is inefficient
//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            if (client.player == null  || !client.isWindowFocused()) return;
//
//            //any mount works
//            boolean isMounted = client.player.hasVehicle();
//
//            if (isMounted && !wasMountedLastTick) {
//                last_perspective = client.options.getPerspective();
//                client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
//            } else if (!isMounted && wasMountedLastTick) {
//                client.options.setPerspective(last_perspective);
//            }
//
//            wasMountedLastTick = isMounted;
//        });
    }
}