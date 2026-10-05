/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.container.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A LinkButton specifies a Container to be opened when it is clicked.
 * Notably, this should probably never be a PerPlayerContainer unless the element is placed in a PerPlayerContainer itself.
 */
public class LinkButton implements ClickableElement {

    private final ItemStack displayItem;
    private final Container link;
    public LinkButton(@Nullable ItemStack displayItem, @NotNull Container link) {
        this.displayItem = displayItem;
        this.link = link;
    }

    @Override
    public @Nullable ItemStack getDisplayItem() { return displayItem; }

    /**
     * On click, a LinkButton opens the specified linked Container.
     * @param player the player clicking on the element, to display the Container to.
     * @param type unused for LinkButtons.
     */
    @Override
    public void onClick(@NotNull Player player, @NotNull ClickType type) {
        player.closeInventory();
        link.display(player);
    }
}
