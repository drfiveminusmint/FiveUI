package com.github.drfiveminusmint.fiveUI.effect;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface EntryEffect {
    public void effect(Player player, Object entry);
}
