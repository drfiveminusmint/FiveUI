/*
* Base Interface for all UI elements
*/
package com.github.drfiveminusmint.fiveUI.element;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface UIElement {
    /**
     * This method specifies the ItemStack to draw in the Inventory associated with the Container this UIElement resides in.
     * Once added to a Container, items will not be redrawn unless they implement UpdateableElement.
     * @return the ItemStack to draw. If null this element will be invisible!
     */
    @Nullable ItemStack getDisplayItem();
}
