/*
* Base Interface for all UI elements
*/
package com.github.drfiveminusmint.fiveUI.element;

import org.bukkit.inventory.ItemStack;

public interface UIElement {
    // Itemstack to display in the pane
    ItemStack getDisplayItem();
}
