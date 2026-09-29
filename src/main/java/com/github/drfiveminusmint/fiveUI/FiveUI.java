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
import com.github.drfiveminusmint.fiveUI.container.Page;
import com.github.drfiveminusmint.fiveUI.element.StaticButton;
import com.github.drfiveminusmint.fiveUI.element.StaticDisplay;
import com.github.drfiveminusmint.fiveUI.util.ItemStackBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

public final class FiveUI extends JavaPlugin {
    private static FiveUI instance;
    private Page InfoUI;
    private UIManager uiManager;

    @Override
    public void onEnable() {
        // Initialize
        instance = this;
        getCommand("fiveui").setExecutor(new FiveUICommand());
        uiManager = new UIManager();
        getServer().getPluginManager().registerEvents(uiManager, this);

        // create info / example UI
        InfoUI = new Page(Component.text("FiveUI"), InventoryType.CHEST);
        // fill the UI with yellow glass
        InfoUI.fillElement(new StaticDisplay(
                new ItemStackBuilder(Material.YELLOW_STAINED_GLASS_PANE, 1)
                .name(Component.text(""))
                .itemStack()));
        // Build info button
        StaticButton infoButton = new StaticButton(
                new ItemStackBuilder(Material.EMERALD,1)
                .name(Component.text(getPluginMeta().getName(), NamedTextColor.YELLOW))
                .addLore(Component.text("Click to see version info!", NamedTextColor.AQUA))
                .itemStack());
        // Set click effect
        infoButton.setOnClick((player, element, type) ->
                player.sendMessage(Component.text("FiveUI version ", NamedTextColor.AQUA)
                .append(Component.text(getPluginMeta().getVersion()))));
        // Place info button in the center
        InfoUI.setElement(13, infoButton);
    }

    @Override
    public void onDisable() {

    }

    /**
     * Returns the main info / example UI for FiveUI
     */
    public Container getInfoUI() {return InfoUI;}

    /**
     * Get the UI Manager, which manages open Containers.
     */
    public UIManager getUIManager() {return uiManager;}
    /**
     * Get the active instance of the FiveUI plugin.
     */
    public static FiveUI getInstance() {return instance;}
}
