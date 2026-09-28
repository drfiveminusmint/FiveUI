package com.github.drfiveminusmint.fiveUI;

import com.github.drfiveminusmint.fiveUI.container.Container;
import com.github.drfiveminusmint.fiveUI.container.Page;
import com.github.drfiveminusmint.fiveUI.effect.ClickEffect;
import com.github.drfiveminusmint.fiveUI.element.ClickableElement;
import com.github.drfiveminusmint.fiveUI.element.StaticButton;
import com.github.drfiveminusmint.fiveUI.element.StaticDisplay;
import com.github.drfiveminusmint.fiveUI.util.ItemStackBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

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

        // create info ui
        InfoUI = new Page(Component.text("FiveUI"), InventoryType.CHEST);
        // fill the UI with yellow glass
        ItemStack fillItem = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta fillMeta = fillItem.getItemMeta();
        fillMeta.itemName(Component.text(""));
        fillItem.setItemMeta(fillMeta);
        InfoUI.fillElement(new StaticDisplay(fillItem));
        // place the info item at the center
        ItemStack infoItem = new ItemStack(Material.EMERALD);
        //ItemMeta infoMeta = infoItem.getItemMeta();
        //infoMeta.itemName(Component.text(getPluginMeta().getName()));
        //infoMeta.lore(List.of(Component.text("Click to see version info!")));
        //infoItem.setItemMeta(infoMeta);
        StaticButton infoButton = new StaticButton(new ItemStackBuilder(Material.EMERALD,1)
                .name(Component.text(getPluginMeta().getName(), NamedTextColor.YELLOW))
                .addLore(Component.text("Click to see version info!", NamedTextColor.AQUA))
                .itemStack());
        infoButton.setOnClick(new ClickEffect() {
            @Override
            public void effect(Player player, ClickableElement element, ClickType type) {
                player.sendMessage(Component.text("FiveUI version ", NamedTextColor.AQUA)
                        .append(Component.text(getPluginMeta().getVersion())));
            }
        });
        InfoUI.setElement(13, infoButton);
    }

    @Override
    public void onDisable() {

    }

    public Container getInfoUI() {return InfoUI;}
    public UIManager getUIManager() {return uiManager;}
    public static FiveUI getInstance() {return instance;}
}
