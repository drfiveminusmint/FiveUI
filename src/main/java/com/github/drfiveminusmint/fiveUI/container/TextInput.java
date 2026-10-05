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
import com.github.drfiveminusmint.fiveUI.effect.EntryEffect;
import com.github.drfiveminusmint.fiveUI.element.UIElement;
import com.github.drfiveminusmint.fiveUI.element.UpdatableElement;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.view.AnvilView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;

/**
 * This Container allows you to get text input from a player, using the Anvil UI.
 * You <b>must</b> put a UIElement (even if it's only a StaticDisplay) in slot 0 to enable the anvil's text input.
 */
public class TextInput implements PerPlayerContainer {

    private Vector<UIElement> elements;
    private AnvilView inventoryView;
    private Player player;

    private CloseEffect closeEffect;
    private EntryEffect entryEffect;

    public TextInput(Component title, Player player) {
        elements = new Vector<>();
        elements.setSize(3);
        // Horrible hack
        this.player = player;
        inventoryView = MenuType.ANVIL.builder().checkReachable(false).title(title).build(player);
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
        if (slot >= elements.size())
            throw new IndexOutOfBoundsException(String.format("Slot %d is invalid for TextInput GUIs"));
        inventoryView.setItem(slot, element.getDisplayItem());
        return elements.set(slot, element);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventoryView.getTopInventory();
    }

    @Override
    public @NotNull Player getPlayer() {
        return player;
    }

    @Override
    public boolean display(@NotNull Player player) {
        if (player != this.player) {
            FiveUI.getInstance().getLogger().log(Level.SEVERE, "Attempted to open a TextInput for a player it was not associated with! All TextInputs must be opened by the player declared at construction.");
            return false;
        }
        player.openInventory(inventoryView);
        return true;
    }

    @Override
    public void updateContents() {
        for (int i = 0; i < 3; i++)
            if (getElement(i) instanceof UpdatableElement element)
                inventoryView.setItem(i, element.getDisplayItem());
    }

    // The method to run when the container is closed
    @Override
    public void setOnClose(@Nullable CloseEffect effect) {
        closeEffect = effect;
    }

    @Override
    public void onClose(@NotNull Player player, InventoryCloseEvent.@NotNull Reason reason) {
        for (int i = 0; i < 3; i++)
            inventoryView.setItem(i, null);
        // prevent a resource leak here
        FiveUI.getInstance().getUIManager().unregisterInterface(this);
        closeEffect.effect(player, this, reason);
    }

    /**
     * This method is run when any item in this Container is clicked.
     * It passes information from the UIManager to your defined EntryEffect
     * @param player the player to pass to EntryEffect.
     * @param value the text in the anvil name window, which will be passed as an argument to EntryEffect.
     */
    public void onEntry(@NotNull Player player, Object value) {
        if (entryEffect != null)
            entryEffect.effect(player, value);
    }

    /**
     * Set the effect to trigger when text is entered into this GUI and a button is clicked.
     * This is used to process the data received from the Anvil UI.
     * @param onEntry the EntryEffect to trigger. If this is set to null, then no additional code will run when text is submitted.
     */
    public void setOnEntry(@Nullable EntryEffect onEntry) {
        entryEffect = onEntry;
    }

    @NotNull
    @Override
    public Iterator<UIElement> iterator() {
        return elements.iterator();
    }
}
