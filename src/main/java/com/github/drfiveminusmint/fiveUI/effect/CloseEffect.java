package com.github.drfiveminusmint.fiveUI.effect;

import com.github.drfiveminusmint.fiveUI.container.Container;
import org.bukkit.entity.Player;

@FunctionalInterface
public interface CloseEffect {
    public void effect(Player player, Container container);
}
