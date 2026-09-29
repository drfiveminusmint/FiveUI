package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.ItemUpdateEffect;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

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
