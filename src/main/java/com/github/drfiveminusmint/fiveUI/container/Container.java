/*
 * Base Interface for all GUIs we can open
 */
package com.github.drfiveminusmint.fiveUI.container;

import com.github.drfiveminusmint.fiveUI.effect.CloseEffect;
import com.github.drfiveminusmint.fiveUI.element.UIElement;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Container extends Iterable<UIElement> {
    // add an element to the container
    // returns the element that was previously in the UIElement's slot, if any
    @Nullable UIElement setElement(int slot, @Nullable UIElement element);
    // gets the item currently in the specified slot;
    @Nullable UIElement getElement(int slot);
    // gets the inventory associated with this Container
    @NotNull Inventory getInventory();
    // display this container to a player
    boolean display(@NotNull Player player);
    // updates all updatable elements within this container
    void updateContents();
    // set method to be run when the container is closed with the ESC key
    void setOnClose(@Nullable CloseEffect effect);
    void onClose(@NotNull Player player);
}
