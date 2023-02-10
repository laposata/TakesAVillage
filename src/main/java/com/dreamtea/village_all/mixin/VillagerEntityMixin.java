package com.dreamtea.village_all.mixin;

import com.dreamtea.village_all.villager.IGenerallyFriendly;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.village.VillagerGossips;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class )
public class VillagerEntityMixin {

  @Shadow @Final private VillagerGossips gossip;

  @Inject(method = "getReputation", at = @At("RETURN"), cancellable = true)
  public void friendlyToAll(PlayerEntity player, CallbackInfoReturnable<Integer> cir){
    int getOthers = ((IGenerallyFriendly)gossip).getBestReputationOfOthers(player.getUuid(), gossipType -> true);
    if(getOthers != 0){
      cir.setReturnValue(Math.max(cir.getReturnValue(), getOthers));
    }
  }
}
