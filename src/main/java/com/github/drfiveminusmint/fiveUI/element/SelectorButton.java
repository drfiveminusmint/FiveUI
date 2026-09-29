/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.EntryEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Selector Buttons have multiple states they can cycle through with left and right click, each with an associated ItemStack to draw.
 * Set an EntryEffect to process their transition to a new state; the integer value of the new state is passed to the effect() method as the entry parameter.
 * The most common use case is a toggle button with two states, but buttons with more states are possible.
 */
public class SelectorButton implements ClickableElement, UpdatableElement {
    private int state;
    private final ItemStack[] states;
    private EntryEffect entryEffect;

    public SelectorButton(@NotNull ItemStack[] displayStates) {
        states = displayStates;
    }

    @Override
    public void onClick(@NotNull Player player, @NotNull ClickType type) {
        // left click advances state, right click reverses it
        if(type.isRightClick())
            state = (state-1) % states.length;
        else
            state = (state+1) % states.length;
        // Trigger entry effect
        entryEffect.effect(player, state);
    }

    @Override
    @NotNull
    public ItemStack getDisplayItem() { return states[state]; }

    public int getState() { return state; }

    /**
     * Set the effect to run when the button's state changes.
     * The new state is passed as an integer to the EntryEffect.effect() method.
     * @param onEntry the EntryEffect to run on a state change, with the clicking player and button's new state as arguments.
     */
    public void setOnEntry(EntryEffect onEntry) { entryEffect = onEntry; }
}
