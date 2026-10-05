/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.ClickEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The simplest type of button. Has only one state and will not be redrawn when Container.updateContents() is called.
 */
public class StaticButton implements ClickableElement {
    private final ItemStack displayItem;
    private ClickEffect clickEffect = (player, element, type) -> {};

    public StaticButton(ItemStack displayItem) { this.displayItem = displayItem; }

    @Override
    public @Nullable ItemStack getDisplayItem() { return displayItem; }
    @Override
    public void onClick(@NotNull Player player, @NotNull ClickType type) {
        clickEffect.effect(player, this, type);
    }

    /**
     * Set the effect to be run when this button is clicked.
     * @param effect the ClickEffect that will be run. Consider using a lambda here.
     */
    public void setOnClick(ClickEffect effect) {
        clickEffect = effect;
    }
}
