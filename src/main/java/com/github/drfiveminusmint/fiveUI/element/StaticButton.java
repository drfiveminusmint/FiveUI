package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.ClickEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class StaticButton implements ClickableElement {
    private final ItemStack displayItem;
    private ClickEffect clickEffect = new ClickEffect() {
        @Override
        public void effect(@NotNull Player player, @NotNull ClickableElement element, @NotNull ClickType type) {
            return;
        }
    };

    public StaticButton(ItemStack displayItem) { this.displayItem = displayItem; }

    @Override
    public @NotNull ItemStack getDisplayItem() { return displayItem; }
    @Override
    public void onClick(@NotNull Player player, @NotNull ClickType type) {
        clickEffect.effect(player, this, type);
    }

    // Set the method to be run when clicked
    public void setOnClick(ClickEffect effect) {
        clickEffect = effect;
    }
}
