/*
* Base Interface for all UI elements
*/
package com.github.drfiveminusmint.fiveUI.element;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface UIElement {
    // Itemstack to display in the pane
    @NotNull ItemStack getDisplayItem();
}
