/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.element;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.jetbrains.annotations.NotNull;

public interface ClickableElement extends UIElement {
    /**
     * This method is run whenever this UIElement is clicked on within the GUI.
     * @param player the player clicking on the element.
     * @param type the type of click performed, specified as a ClickType enum.
     */
    void onClick(@NotNull Player player, @NotNull ClickType type);
}
