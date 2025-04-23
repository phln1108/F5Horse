package com.phln1108.f5horses;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;

public class F5HorsesClient implements ClientModInitializer {
    static Perspective lastPerspective = Perspective.FIRST_PERSON;
    static boolean mounted = false;
    Entity lastMount;

    @Override
    public void onInitializeClient() {
        // this way is inefficient
//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            if (client.player == null  || !client.isWindowFocused()) return;
//
//            //any mount works
//            boolean isMounted = client.player.hasVehicle();
//
//            if (isMounted && !mounted) {
//                lastPerspective = client.options.getPerspective();
//                client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
//            } else if (!isMounted && mounted) {
//                client.options.setPerspective(lastPerspective);
//            }
//
//            mounted = isMounted;
//        });

        HorseMountCallback.EVENT.register((player) -> {
            if (!mounted) {
                lastPerspective = MinecraftClient.getInstance().options.getPerspective();
            }
            lastMount = player.getVehicle();

            mounted = true;
            MinecraftClient.getInstance().options.setPerspective(Perspective.THIRD_PERSON_BACK);

            return ActionResult.PASS;
        });

        StopMountCallback.EVENT.register((player) -> {
            if (player.hasVehicle() && lastMount != null && lastMount.equals(player.getVehicle())) {
                MinecraftClient.getInstance().options.setPerspective(lastPerspective);
                mounted = false;
            }

            return ActionResult.PASS;
        });
    }
}