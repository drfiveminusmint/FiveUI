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

public class Page implements Container {
    private Vector<UIElement> elements;
    private Inventory inventory;
    InventoryType inventoryType;
    CloseEffect closeEffect = null;

    public Page(Component title, InventoryType type) {
        // Create the inventory we'll be using to display this pane
        inventory = Bukkit.createInventory(null, type, title);
        inventoryType = type;
        elements = new Vector<>();
        elements.setSize(inventoryType.getDefaultSize());
        // Make this interface functional
        FiveUI.getInstance().getUIManager().registerInterface(this);
    }

    @Override
    @Nullable
    public UIElement getElement(int slot) {
        return elements.get(slot);
    }

    @Override
    @Nullable
    public UIElement setElement(int slot, UIElement element) {
        if (slot > inventory.getSize())
            throw new IndexOutOfBoundsException(String.format("Slot %d is invalid for inventories of type %s", slot, inventoryType.name()));
        if (element == null)
            inventory.setItem(slot, null);
        else
            inventory.setItem(slot, element.getDisplayItem());
        return elements.set(slot, element);
    }

    // Fills the entire pane with this element
    public void fillElement(UIElement element) {
        for (int i = 0; i < inventory.getSize(); i++)
            setElement(i, element);
    }

    // display this pane to a player
    @Override
    public boolean display(Player player) {
        updateContents();
        player.openInventory(inventory);
        return false;
    }

    // update all updateable elements within this pane
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
    public Inventory getInventory() {return inventory;}

    // set method to be run when the container is closed with the ESC key
    @Override
    public void setOnClose(CloseEffect newEffect) {
        closeEffect = newEffect;
    }

    // called when the container is closed
    @Override
    public void onClose(Player player) {
        if (closeEffect == null) return;
        closeEffect.effect(player, this);
    }
}
