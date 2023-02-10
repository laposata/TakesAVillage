package com.dreamtea.village_all.mixin;

import com.dreamtea.village_all.villager.IGenerallyFriendly;
import net.minecraft.village.VillageGossipType;
import net.minecraft.village.VillagerGossips;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

@Mixin(VillagerGossips.class)
public abstract class VillagerGossipsMixin implements IGenerallyFriendly {

  @Shadow @Final private Map<UUID, VillagerGossips.Reputation> entityReputation;

  @Shadow public abstract int getReputationFor(UUID target, Predicate<VillageGossipType> gossipTypeFilter);

  public int getBestReputationOfOthers(UUID target, Predicate<VillageGossipType> gossipTypeFilter) {
    Set<UUID> players = entityReputation.keySet();
    return players.stream().filter(uuid -> !uuid.equals(target)).map(uuid -> getReputationFor(uuid, gossipTypeFilter)).max(Integer::compareTo).orElse(0);
  }
}
