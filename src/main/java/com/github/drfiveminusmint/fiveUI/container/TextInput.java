package com.github.drfiveminusmint.fiveUI.container;

import com.github.drfiveminusmint.fiveUI.FiveUI;
import com.github.drfiveminusmint.fiveUI.UIManager;
import com.github.drfiveminusmint.fiveUI.effect.CloseEffect;
import com.github.drfiveminusmint.fiveUI.effect.EntryEffect;
import com.github.drfiveminusmint.fiveUI.element.UIElement;
import com.github.drfiveminusmint.fiveUI.element.UpdatableElement;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.view.AnvilView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;

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
    public UIElement setElement(int slot, UIElement element) {
        if (slot >= elements.size())
            throw new IndexOutOfBoundsException(String.format("Slot %d is invalid for TextInput GUIs"));
        inventoryView.setItem(slot, element.getDisplayItem());
        return elements.set(slot, element);
    }

    @Override
    public Inventory getInventory() {
        return inventoryView.getTopInventory();
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean display(Player player) {
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
    public void setOnClose(CloseEffect effect) {
        closeEffect = effect;
    }

    @Override
    public void onClose(Player player) {
        for (int i = 0; i < 3; i++)
            inventoryView.setItem(i, null);
        // prevent a resource leak here
        FiveUI.getInstance().getUIManager().unregisterInterface(this);
        closeEffect.effect(player, this);
    }

    // The method to run when the "enter" item is clicked
    public void onEntry(Player player, Object value) {
        entryEffect.effect(player, value);
    }

    public void setOnEntry(EntryEffect onEntry) {
        entryEffect = onEntry;
    }

    @NotNull
    @Override
    public Iterator<UIElement> iterator() {
        return elements.iterator();
    }
}
