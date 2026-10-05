/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.effect;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This functional interface is used to provide effects that trigger when an element is redrawn in a GUI.
 * You can use this to update the visuals you present to the player in response to external state.
 * @see com.github.drfiveminusmint.fiveUI.element.UpdatableElement
 */
@FunctionalInterface
public interface ItemUpdateEffect {
    /**
     * This method is run when the item's appearance is updated.
     * @return the new ItemStack to display.
     */
    @Nullable
    ItemStack onItemUpdate();
}
