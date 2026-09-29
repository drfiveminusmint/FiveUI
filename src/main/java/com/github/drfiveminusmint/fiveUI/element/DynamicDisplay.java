/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.ItemUpdateEffect;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A DynamicDisplay is a display element (not clickable) that can be redrawn with an ItemUpdateEffect.
 */
public class DynamicDisplay implements UpdatableElement {
    ItemUpdateEffect updateEffect;

    DynamicDisplay(@NotNull ItemUpdateEffect effect) {
        updateEffect = effect;
    }

    @Override
    public @NotNull ItemStack getDisplayItem() {
        return updateEffect.onItemUpdate();
    }
}
