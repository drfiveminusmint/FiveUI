package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.ClickEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.jetbrains.annotations.NotNull;

public interface ClickableElement extends UIElement {
    // Returns the method to be run when clicked
    void onClick(@NotNull Player player, @NotNull ClickType type);
}
