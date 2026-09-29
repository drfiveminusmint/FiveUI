/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to make and decorate ItemStacks more easily for display purposes.
 * Create an ItemStackBuilder, run whatever methods are needed to modify the ItemStack, and then use itemStack() to build.
 */
public class ItemStackBuilder {
    ItemStack buildingItem;
    ItemMeta itemMeta;
    public ItemStackBuilder(@NotNull Material material, int count) {
        buildingItem = new ItemStack(material, count);
        itemMeta = buildingItem.getItemMeta();
    }

    /**
     * Finalize and build the ItemStack.
     * This does not destroy the ItemStackBuilder, and further modifications to the ItemStackBuilder do not affect built items.
     * @return a copy of the ItemStack that has been built.
     */
    @NotNull
    public ItemStack itemStack() {
        buildingItem.setItemMeta(itemMeta);
        return buildingItem.clone();
    }

    /**
     * Set the display name of the item.
     * @param title A Component to display when the ItemStack is moused over.
     * @return the ItemStackBuilder.
     */
    public ItemStackBuilder name(Component title) {
        itemMeta.displayName(title);
        return this;
    }

    /**
     * Set the lore or description of the item.
     * @param lore A Component to display below the ItemStack's name when it is moused over.
     * @return the ItemStackBuilder.
     */
    public ItemStackBuilder addLore(Component lore) {
        List<Component> currentLore = itemMeta.lore();
        if (currentLore == null)
            currentLore = new ArrayList<>();
        currentLore.add(lore);
        itemMeta.lore(currentLore);
        return this;
    }

    /**
     * Set the item's enchantments.
     * @param enchantment The enchantment type to add. This does not need to follow standard enchantment rules.
     * @param level The level of the enchantment to add. This can exceed the max level allowed for this enchantment,
     *              but levels above 10 will look strange.
     * @return the ItemStackBuilder.
     */
    public ItemStackBuilder enchant(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Overrides the item's enchantment glimmer.
     * @param glimmer Whether the item should glimmer as if it is enchanted.
     * @return the ItemStackBuilder.
     */
    public ItemStackBuilder setGlimmer(boolean glimmer) {
        itemMeta.setEnchantmentGlintOverride(glimmer);
        return this;
    }
}
