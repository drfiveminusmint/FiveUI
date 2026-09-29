/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.container;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Containers associated permanently with a specific player.
 * Use this if you need to display player-specific data in a GUI.
 */
public interface PerPlayerContainer extends Container {
    /**
     * Gets the player associated with this GUI.
     * This should never change. If a new player must be associated with this GUI, make a new copy for them.
     * @return the player associated with this container.
     */
    @NotNull Player getPlayer();
}
