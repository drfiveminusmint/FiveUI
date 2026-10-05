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
import com.github.drfiveminusmint.fiveUI.effect.ItemUpdateEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A DynamicButton is a button that can have its display item updated by an ItemUpdateEffect.
 */
public class DynamicButton implements ClickableElement, UpdatableElement {
    private final ItemUpdateEffect updateEffect;
    private ClickEffect clickEffect = null;

    public DynamicButton(@NotNull ItemUpdateEffect effect) { updateEffect = effect; }

    @Override
    public @Nullable ItemStack getDisplayItem() {
        return updateEffect.onItemUpdate();
    }
    @Override
    public void onClick(@NotNull Player player, @NotNull ClickType type) {
        if (clickEffect != null)
            clickEffect.effect(player, this, type);
    }

    /**
     * Set the effect to be run when this button is clicked.
     * @param effect the ClickEffect that will be run. Consider using a lambda here.
     */
    public void setOnClick(@Nullable ClickEffect effect) {
        clickEffect = effect;
    }
}
