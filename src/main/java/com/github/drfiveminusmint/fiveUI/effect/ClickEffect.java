package com.github.drfiveminusmint.fiveUI.effect;

import com.github.drfiveminusmint.fiveUI.element.ClickableElement;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

@FunctionalInterface
public interface ClickEffect {
    void effect(Player player, ClickableElement element, ClickType type);
}
