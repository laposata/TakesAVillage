package com.dreamtea.village_all.villager;

import net.minecraft.village.VillageGossipType;

import java.util.UUID;
import java.util.function.Predicate;

public interface IGenerallyFriendly {
  public int getBestReputationOfOthers(UUID target, Predicate<VillageGossipType> gossipTypeFilter);
}
