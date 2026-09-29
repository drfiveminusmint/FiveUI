package com.github.drfiveminusmint.fiveUI.effect;

import com.github.drfiveminusmint.fiveUI.container.Container;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface CloseEffect {
    public void effect(@NotNull Player player, @NotNull Container container);
}
