package com.github.drfiveminusmint.fiveUI.effect;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface EntryEffect {
    public void effect(@NotNull Player player, Object entry);
}
