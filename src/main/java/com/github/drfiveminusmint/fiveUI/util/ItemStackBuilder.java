package com.github.drfiveminusmint.fiveUI.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemStackBuilder {
    ItemStack buildingItem;
    ItemMeta itemMeta;
    public ItemStackBuilder(Material material, int count) {
        buildingItem = new ItemStack(material, count);
        itemMeta = buildingItem.getItemMeta();
    }
    public ItemStack itemStack() {
        buildingItem.setItemMeta(itemMeta);
        return buildingItem;
    }

    public ItemStackBuilder name(Component title) {
        itemMeta.displayName(title);
        return this;
    }

    public ItemStackBuilder addLore(Component lore) {
        List<Component> currentLore = itemMeta.lore();
        if (currentLore == null)
            currentLore = new ArrayList<>();
        currentLore.add(lore);
        itemMeta.lore(currentLore);
        return this;
    }

    public ItemStackBuilder enchant(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }
}
