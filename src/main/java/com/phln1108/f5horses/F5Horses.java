package com.phln1108.f5horses;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class F5Horses implements ModInitializer {
    public static final String MOD_ID = "f5horses";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    static Perspective lastPerspective = Perspective.FIRST_PERSON;
    static boolean mounted = false;
    Entity lastMount;


    @Override
    public void onInitialize() {
        HorseMountCallback.EVENT.register((player) -> {
            LOGGER.info("montou");
            if (!mounted) {
                lastPerspective = MinecraftClient.getInstance().options.getPerspective();
            }
            lastMount = player.getVehicle();

            mounted = true;
            MinecraftClient.getInstance().options.setPerspective(Perspective.THIRD_PERSON_BACK);

            return ActionResult.PASS;
        });

        StopMountCallback.EVENT.register((player) -> {
            LOGGER.info("desmontou");
            if (player.hasVehicle() && lastMount != null && lastMount.equals(player.getVehicle())) {
                MinecraftClient.getInstance().options.setPerspective(lastPerspective);
                mounted = false;
            }

            return ActionResult.PASS;
        });

    }
}