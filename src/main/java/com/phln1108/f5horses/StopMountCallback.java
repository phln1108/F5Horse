package com.phln1108.f5horses;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface StopMountCallback {
    Event<StopMountCallback> EVENT = EventFactory.createArrayBacked(StopMountCallback.class,
            (listeners) -> (player) -> {
                for (StopMountCallback listener : listeners) {

                    ActionResult result = listener.interact(player);


                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(PlayerEntity player);
}
