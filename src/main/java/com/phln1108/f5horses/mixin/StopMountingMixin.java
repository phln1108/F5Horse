package com.phln1108.f5horses.mixin;


import com.phln1108.f5horses.StopMountCallback;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class StopMountingMixin {

    @Inject(method = "dismountVehicle", at = @At("HEAD"))
    private void onStopRiding(CallbackInfo info) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        StopMountCallback.EVENT.invoker().interact(player);
    }
}
