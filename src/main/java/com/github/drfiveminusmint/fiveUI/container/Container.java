/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.container;

import com.github.drfiveminusmint.fiveUI.effect.CloseEffect;
import com.github.drfiveminusmint.fiveUI.element.UIElement;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base interface for all GUIs we can open.
 */
public interface Container extends Iterable<UIElement> {
    /**
     * Add an element to the container.
     * Implementations are not required to check whether the slot in question is out of bounds.
     * @param slot the inventory to place the element in.
     * @param element the UIElement to add to the GUI.
     * @return the UIElement that was replaced, or null if none.
     */
    @Nullable UIElement setElement(int slot, @Nullable UIElement element);

    /**
     * Gets the item currently in the specified slot in this container's inventory.
     * Implementations are not required to check whether the slot in question is out of bounds.
     * @param slot the slot to look at within the Container's inventory.
     * @return the UIElement at the specified slot, or null if none.
     */
    @Nullable UIElement getElement(int slot);

    /**
     * Gets the inventory associated with this Container.
     * @return the container's Inventory.
     */
    @NotNull Inventory getInventory();

    /**
     * Displays this container to the player.
     * Implementations should display the inventory associated with the container, along with all elements.
     * @param player the player to display this Container to.
     * @return true if the Container was successfully displayed, false otherwise.
     */
    boolean display(@NotNull Player player);

    /**
     * Redraws all GUI elements within this Container that can be updated, using UIElement.getDisplayItem().
     * To declare itself updatable, an element should implement the UpdatableElement marker interface
     * @see com.github.drfiveminusmint.fiveUI.element.UpdatableElement
     */
    void updateContents();

    /**
     * Sets the method to be run when this interface is closed.
     * @param effect the new CloseEffect to run. If this is set to null, no additional code will be run when the interface closes.
     */
    void setOnClose(@Nullable CloseEffect effect);
    /**
     * Sets the method to be run when this interface is closed.
     * Implementations should invoke CloseEffect.effect() at some point in this method.
     * @param player the player closing this interface. Implementations should pass this to the CloseEffect in some way.
     * @param reason the reason this interface was closed. Implementations should pass this to CloseEffect in some way.
     */
    void onClose(@NotNull Player player, @NotNull InventoryCloseEvent.Reason reason);
}
