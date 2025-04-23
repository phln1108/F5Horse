package com.phln1108.f5horses.mixin;

import com.phln1108.f5horses.HorseMountCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class HorseMountMixin {
//    @Inject(method = "startRiding(Lnet/minecraft/entity/Entity;)Z", at = @At("TAIL"), cancellable = true)
//    private void onMount(Entity entity, CallbackInfoReturnable<Boolean> info) {
//        ActionResult result = HorseMountCallback.EVENT.invoker().interact((PlayerEntity) (Object) this);
//
//        if (result == ActionResult.FAIL) {
//            info.setReturnValue(false);
//        }
//    }

    @Inject(method = "startRiding(Lnet/minecraft/entity/Entity;Z)Z", at = @At("TAIL"), cancellable = true)
    private void onMountForced(Entity entity, boolean force, CallbackInfoReturnable<Boolean> info) {
        if ((Object) this instanceof PlayerEntity player) {
//        if (!((Object) this instanceof PlayerEntity player) || !(entity instanceof HorseEntity)) return;
            // Call your event
            ActionResult result = HorseMountCallback.EVENT.invoker().interact(player);

            if (result == ActionResult.FAIL) {
                info.setReturnValue(false);
            }
        }
    }
}
