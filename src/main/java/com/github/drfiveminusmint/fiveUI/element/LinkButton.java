package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.container.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class LinkButton implements ClickableElement {

    private final ItemStack displayItem;
    private final Container link;
    public LinkButton(ItemStack displayItem, Container link) {
        this.displayItem = displayItem;
        this.link = link;
    }

    @Override
    public ItemStack getDisplayItem() { return displayItem; }

    // Open the linked Container for the player
    @Override
    public void onClick(Player player, ClickType type) {
        player.closeInventory();
        link.display(player);
    }
}
