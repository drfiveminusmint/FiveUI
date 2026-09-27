package com.github.drfiveminusmint.fiveUI.element;

import org.bukkit.inventory.ItemStack;

public class StaticDisplay implements UIElement {
    private final ItemStack displayItem;

    public StaticDisplay(ItemStack displayItem) { this.displayItem = displayItem; }

    @Override
    public ItemStack getDisplayItem() { return displayItem; }
}
