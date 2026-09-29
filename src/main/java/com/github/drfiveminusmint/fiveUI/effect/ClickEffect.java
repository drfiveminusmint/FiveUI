package com.github.drfiveminusmint.fiveUI.effect;

import com.github.drfiveminusmint.fiveUI.element.ClickableElement;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ClickEffect {
    void effect(@NotNull Player player, @NotNull ClickableElement element, @NotNull ClickType type);
}
