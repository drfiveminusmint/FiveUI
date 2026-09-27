package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.ClickEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class StaticButton implements ClickableElement {
    private final ItemStack displayItem;
    private ClickEffect clickEffect = new ClickEffect() {
        @Override
        public void effect(Player player, ClickableElement element, ClickType type) {
            return;
        }
    };

    public StaticButton(ItemStack displayItem) { this.displayItem = displayItem; }

    @Override
    public ItemStack getDisplayItem() { return displayItem; }
    @Override
    public void onClick(Player player, ClickType type) {
        clickEffect.effect(player, this, type);
    }

    // Set the method to be run when clicked
    public void setOnClick(ClickEffect effect) {
        clickEffect = effect;
    }
}
