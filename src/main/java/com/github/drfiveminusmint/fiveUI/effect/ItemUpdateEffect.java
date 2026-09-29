package com.github.drfiveminusmint.fiveUI.effect;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemUpdateEffect {
    @NotNull
    public ItemStack onItemUpdate();
}
