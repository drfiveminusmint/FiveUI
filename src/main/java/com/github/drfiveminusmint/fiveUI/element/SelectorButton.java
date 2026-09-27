package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.EntryEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SelectorButton implements ClickableElement, UpdatableElement {
    private int state;
    private final ItemStack[] states;
    private EntryEffect entryEffect;

    public SelectorButton(ItemStack[] displayStates) {
        states = displayStates;
    }

    @Override
    public void onClick(Player player, ClickType type) {
        // left click advances state, right click reverses it
        if(type.isRightClick())
            state = (state-1) % states.length;
        else
            state = (state+1) % states.length;
        // Trigger entry effect
        entryEffect.effect(player, state);
    }

    @Override
    public ItemStack getDisplayItem() { return states[state]; }

    public int getState() { return state; }

    public void setOnEntry(EntryEffect onEntry) { entryEffect = onEntry; }
}
