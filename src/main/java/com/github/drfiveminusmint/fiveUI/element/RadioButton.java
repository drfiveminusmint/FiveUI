package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.ClickEffect;
import com.github.drfiveminusmint.fiveUI.effect.CloseEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class RadioButton implements ClickableElement, UpdatableElement {

    private final ItemStack unsetItem, setItem;
    private ClickEffect onClick = null, onReset = null;
    private boolean isClicked;
    private final ArrayList<RadioButton> linked = new ArrayList<>();
    public RadioButton(ItemStack unset, ItemStack set) {
        unsetItem = unset;
        setItem = set;
    }
    @Override
    public ItemStack getDisplayItem() { return isClicked ? setItem : unsetItem; }
    @Override
    public void onClick(Player player, ClickType type) {
        // set this button as the clicked option
        isClicked = true;
        for (RadioButton other : linked)
            other.reset(player, type);
        // trigger click effect
        if (onClick != null)
            onClick.effect(player, this, type);
    }

    public void setOnClick (@Nullable ClickEffect effect) { onClick = effect; }

    public void reset(Player player, ClickType type) {
        if (onReset != null && isClicked)
            onReset.effect(player, this, type);
        isClicked = false;
    }

    public void setOnReset (@Nullable ClickEffect effect) { onReset = effect; }

    // Establishes a two-way link between two radio buttons
    public void link(RadioButton other) {
        linked.add(other);
        other.linked.add(this);
    }
}
