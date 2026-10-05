/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.element;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class StaticDisplay implements UIElement {
    private final ItemStack displayItem;

    public StaticDisplay(@Nullable ItemStack displayItem) { this.displayItem = displayItem; }

    @Override
    @Nullable
    public ItemStack getDisplayItem() { return displayItem; }
}
