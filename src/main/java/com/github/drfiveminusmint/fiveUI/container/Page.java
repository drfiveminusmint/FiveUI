/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.container;

import com.github.drfiveminusmint.fiveUI.FiveUI;
import com.github.drfiveminusmint.fiveUI.effect.CloseEffect;
import com.github.drfiveminusmint.fiveUI.element.UIElement;
import com.github.drfiveminusmint.fiveUI.element.UpdatableElement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Vector;

/**
 * The most general-use Container, a Page consists of items in an Inventory
 * that does not have any specific functions associated with it. (ex. text entry, special buttons)
 */
public class Page implements Container {
    private Vector<UIElement> elements;
    private Inventory inventory;
    InventoryType inventoryType;
    CloseEffect closeEffect = null;

    /**
     * Create page with specified InventoryType.
     * This cannot be used to create pages of different sizes, use Page(title, size) instead.
     * @param title the title to display at the top of the window.
     * @param type the InventoryType to display this Page with. Some specialized functions of this
     *            inventory type will not be functional, see <a href="https://jd.papermc.io/paper/26.3/org/bukkit/Server.html#createInventory(org.bukkit.inventory.InventoryHolder,org.bukkit.event.inventory.InventoryType,net.kyori.adventure.text.Component)">Paper Docs</a>.
     */
    public Page(@NotNull Component title, @NotNull InventoryType type) {
        // Create the inventory we'll be using to display this pane
        inventory = Bukkit.createInventory(null, type, title);
        inventoryType = type;
        elements = new Vector<>();
        elements.setSize(inventoryType.getDefaultSize());
        // Make this interface functional
        FiveUI.getInstance().getUIManager().registerInterface(this);
    }

    /**
     * Create default size page
     * Same as Page(title, size = 27)
     * @param title the title to display at the top of the window.
     */
    public Page(@NotNull Component title) {
        this(title, InventoryType.CHEST);
    }

    /**
     * Create variable size page
     * Will always be InventoryType.Chest
     * @param title the title to display at the top of the window.
     * @param size the size of the page. This <u>must</u> be a multiple of 9.
     */
    public Page(@NotNull Component title, int size) {
        inventory = Bukkit.createInventory(null, size, title);
        inventoryType = InventoryType.CHEST;
        elements = new Vector<>();
        elements.setSize(size);
        FiveUI.getInstance().getUIManager().registerInterface(this);
    }

    @Override
    @Nullable
    public UIElement getElement(int slot) {
        return elements.get(slot);
    }

    @Override
    @Nullable
    public UIElement setElement(int slot, @Nullable UIElement element) {
        if (slot > inventory.getSize())
            throw new IndexOutOfBoundsException(String.format("Slot %d is invalid for inventories of type %s", slot, inventoryType.name()));
        if (element == null)
            inventory.setItem(slot, null);
        else
            inventory.setItem(slot, element.getDisplayItem());
        return elements.set(slot, element);
    }

    /**
     * Utility method to fill a page. Every available slot on this page will be filled with the specified element.
     * @param element the element to fill the page with.
     */
    public void fillElement(@Nullable UIElement element) {
        for (int i = 0; i < inventory.getSize(); i++)
            setElement(i, element);
    }
    
    @Override
    public boolean display(@NotNull Player player) {
        updateContents();
        player.openInventory(inventory);
        return false;
    }

    @Override
    public void updateContents() {
        for (int i = 0; i < inventory.getSize(); i++)
            if (getElement(i) instanceof UpdatableElement element)
                inventory.setItem(i, element.getDisplayItem());
    }

    @NotNull
    @Override
    public Iterator<UIElement> iterator() {
        return elements.iterator();
    }

    @Override
    public @NotNull Inventory getInventory() {return inventory;}

    @Override
    public void setOnClose(CloseEffect newEffect) {
        closeEffect = newEffect;
    }

    @Override
    public void onClose(@NotNull Player player) {
        if (closeEffect == null) return;
        closeEffect.effect(player, this);
    }
}
