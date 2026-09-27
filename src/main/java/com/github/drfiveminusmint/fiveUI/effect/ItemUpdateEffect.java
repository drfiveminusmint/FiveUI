package com.github.drfiveminusmint.fiveUI.effect;

import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface ItemUpdateEffect {
    public ItemStack onItemUpdate();
}
