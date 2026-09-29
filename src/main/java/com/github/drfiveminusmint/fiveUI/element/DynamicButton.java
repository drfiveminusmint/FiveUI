package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.ClickEffect;
import com.github.drfiveminusmint.fiveUI.effect.ItemUpdateEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DynamicButton implements ClickableElement, UpdatableElement {
    private ItemUpdateEffect updateEffect;
    private ClickEffect clickEffect = null;

    public DynamicButton(@NotNull ItemUpdateEffect effect) { updateEffect = effect; }

    @Override
    public ItemStack getDisplayItem() {
        return updateEffect.onItemUpdate();
    }
    @Override
    public void onClick(Player player, ClickType type) {
        if (clickEffect != null)
            clickEffect.effect(player, this, type);
    }

    // Set the method to be run when clicked
    public void setOnClick(@Nullable ClickEffect effect) {
        clickEffect = effect;
    }
}
