package com.phln1108.f5horses.mixin;

import com.phln1108.f5horses.HorseMountCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class HorseMountMixin {
    @Inject(method = "startRiding(Lnet/minecraft/entity/Entity;)Z", at = @At("TAIL"), cancellable = true)
    private void onMount(Entity entity, CallbackInfoReturnable<ActionResult> info) {
        ActionResult result = HorseMountCallback.EVENT.invoker().interact((PlayerEntity) (Object) this);

        if (result == ActionResult.FAIL) {
            info.setReturnValue(result);
        }
    }
}
