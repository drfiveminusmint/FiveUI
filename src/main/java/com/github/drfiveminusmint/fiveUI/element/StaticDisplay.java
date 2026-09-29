package com.github.drfiveminusmint.fiveUI.element;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class StaticDisplay implements UIElement {
    private final ItemStack displayItem;

    public StaticDisplay(@NotNull ItemStack displayItem) { this.displayItem = displayItem; }

    @Override
    @NotNull
    public ItemStack getDisplayItem() { return displayItem; }
}
