package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.ItemUpdateEffect;
import org.bukkit.inventory.ItemStack;

public class DynamicDisplay implements UpdatableElement {
    ItemUpdateEffect updateEffect;

    DynamicDisplay(ItemUpdateEffect effect) {
        updateEffect = effect;
    }

    @Override
    public ItemStack getDisplayItem() {
        return updateEffect.onItemUpdate();
    }
}
