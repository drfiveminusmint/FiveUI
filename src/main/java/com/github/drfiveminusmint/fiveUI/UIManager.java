/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI;

import com.github.drfiveminusmint.fiveUI.container.Container;
import com.github.drfiveminusmint.fiveUI.container.PerPlayerContainer;
import com.github.drfiveminusmint.fiveUI.container.TextInput;
import com.github.drfiveminusmint.fiveUI.element.ClickableElement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.view.AnvilView;

import java.util.HashSet;


public class UIManager implements Listener {

    private final HashSet<Container> interfaces = new HashSet<>();
    // Handle clicks on elements within an interface
    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryClick(InventoryClickEvent event) {
        Container clicked = null;
        // detect if the click happened within a GUI
        for (Container container : interfaces)
            if (container.getInventory().equals(event.getClickedInventory())) {
                clicked = container;
                break;
            }
        if (clicked == null) return;
        // this should never happen, but better safe than sorry
        if (clicked instanceof PerPlayerContainer perPlayerContainer
                && perPlayerContainer.getPlayer() != event.getWhoClicked()) return;
        // Don't allow the player to take items out of the GUI
        event.setCancelled(true);
        // Handle anvil input
        if (clicked instanceof TextInput input)
            input.onEntry((Player) event.getWhoClicked(), ((AnvilView) event.getView()).getRenameText());
        // Handle clickable elements
        if (clicked.getElement(event.getSlot()) instanceof ClickableElement element)
            element.onClick((Player) event.getWhoClicked(), event.getClick());
        // Update elements
        clicked.updateContents();
    }
    // Handle an interface being closed
    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryClose(InventoryCloseEvent event){
        Container closed = null;
        for (Container container : interfaces)
            if (container.getInventory().equals(event.getInventory())) {
                closed = container;
                break;
            }
        if (closed != null) closed.onClose((Player) event.getPlayer(), event.getReason());
    }

    // Make sure items can't be withdrawn or added
    @EventHandler
    public void onInventoryModify(InventoryMoveItemEvent event) {
        Container modified = null;
        // detect if the click happened within a GUI
        for (Container container : interfaces)
            if (container.getInventory().equals(event.getSource()) || container.getInventory().equals(event.getDestination())) {
                modified = container;
                break;
            }
        if (modified == null) return;
        // Don't allow the player to take items out of the GUI
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event) {
        Container modified = null;
        // detect if the click happened within a GUI
        for (Container container : interfaces)
            if (container.getInventory().equals(event.getInventory())) {
                modified = container;
                break;
            }
        if (modified == null) return;
        // Don't allow the player to take items out of the GUI
        event.setCancelled(true);
    }

    // This method should be called by the constructor of all containers to register them
    public void registerInterface(Container container) {
        interfaces.add(container);
    }

    // When your plugin no longer needs an interface, unregister it
    public void unregisterInterface(Container container) {
        interfaces.remove(container);
    }
}
