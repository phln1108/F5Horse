package com.phln1108.f5horses.mixin;

import com.phln1108.f5horses.HorseMountCallback;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HorseEntity.class)
public class HorseMountMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/HorseEntity;hasPassengers()Z"), method = "interactMob", cancellable = true)
    private void onMount(final PlayerEntity player, final Hand hand, final CallbackInfoReturnable<ActionResult> info) {
        ActionResult result = HorseMountCallback.EVENT.invoker().interact(player, (HorseEntity) (Object) this);

        if (result == ActionResult.FAIL) {
            info.setReturnValue(result);
        }
    }
}
