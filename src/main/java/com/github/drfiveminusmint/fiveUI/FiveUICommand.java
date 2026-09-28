package com.github.drfiveminusmint.fiveUI;

import com.github.drfiveminusmint.fiveUI.container.TextInput;
import com.github.drfiveminusmint.fiveUI.effect.EntryEffect;
import com.github.drfiveminusmint.fiveUI.element.StaticDisplay;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FiveUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof  Player player)) {
            commandSender.sendMessage(Component.text("Only players can use this command!", NamedTextColor.RED));
            return true;
        }
        FiveUI.getInstance().getInfoUI().display(player);
        return true;
    }
}
