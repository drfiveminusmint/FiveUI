package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.container.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LinkButton implements ClickableElement {

    private final ItemStack displayItem;
    private final Container link;
    public LinkButton(@NotNull ItemStack displayItem, @NotNull Container link) {
        this.displayItem = displayItem;
        this.link = link;
    }

    @Override
    public @NotNull ItemStack getDisplayItem() { return displayItem; }

    // Open the linked Container for the player
    @Override
    public void onClick(@NotNull Player player, @NotNull ClickType type) {
        player.closeInventory();
        link.display(player);
    }
}
