package com.phln1108.f5horses;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface HorseMountCallback {
    Event<HorseMountCallback> EVENT = EventFactory.createArrayBacked(HorseMountCallback.class,
            (listeners) -> (player) -> {
                for (HorseMountCallback listener : listeners) {
                    ActionResult result = ActionResult.PASS;



                    result = listener.interact(player);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(PlayerEntity player);
}