package com.github.drfiveminusmint.fiveUI.container;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface PerPlayerContainer extends Container {
    @NotNull
    public Player getPlayer();
}
