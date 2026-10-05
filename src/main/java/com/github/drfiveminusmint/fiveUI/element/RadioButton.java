/*
 * This file is part of FiveUI.
 * FiveUI was created by DrFiveMinusMinus and is licensed under the Creative Commons 4.0 BY license.
 * This essentially means you're allowed to do anything you want with it as long as you credit me as the author.
 * See License.MD for full terms.
 *
 * Don't remove this notice from any copies of this file you receive.
 */
package com.github.drfiveminusmint.fiveUI.element;

import com.github.drfiveminusmint.fiveUI.effect.ClickEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

/**
 * A RadioButton is used to provide a list of mutually exclusive choices.
 * RadioButtons have two states: clicked and unclicked, and draw different ItemStacks in each case.
 * When one RadioButton in a group is clicked, any other button that is clicked becomes unclicked.
 * RadioButtons can be grouped using the RadioButton.link() method.
 */
public class RadioButton implements ClickableElement, UpdatableElement {

    private final ItemStack unsetItem, setItem;
    private ClickEffect onClick = null, onReset = null;
    private boolean isClicked;
    private HashSet<RadioButton> linked = new HashSet();
    public RadioButton(ItemStack unset, ItemStack set) {
        unsetItem = unset;
        setItem = set;
        linked.add(this);
    }
    @Override
    public @Nullable ItemStack getDisplayItem() { return isClicked ? setItem : unsetItem; }
    @Override
    public void onClick(@NotNull Player player, @NotNull ClickType type) {
        // set this button as the clicked option
        isClicked = true;
        for (RadioButton other : linked)
            if (other != this)
                other.reset(player, type);
        // trigger click effect
        if (onClick != null)
            onClick.effect(player, this, type);
    }

    /**
     * Set the effect to be run when this button is clicked.
     * @param effect the ClickEffect that will be run. Consider using a lambda here.
     */
    public void setOnClick (@Nullable ClickEffect effect) { onClick = effect; }

    /**
     * Called whenever another RadioButton linked to this one is clicked. This resets this button to its unclicked state.
     * Set a reset ClickEffect with setOnReset to trigger an effect when this happens.
     * @param player the player clicking on the other button.
     * @param type the type of click, as a ClickType enum.
     */
    public void reset(Player player, ClickType type) {
        if (onReset != null && isClicked)
            onReset.effect(player, this, type);
        isClicked = false;
    }

    /**
     * Set this button as the clicked button in the group.
     * This will <u>ignore</u> both onClick and onReset for all buttons if they have been set!
     */
    public void setClicked() {
        // set this button as the clicked option
        isClicked = true;
        for (RadioButton other : linked)
            if (other != this)
                other.isClicked = false;
    }

    /**
     * Set the effect to be run when this button is reset.
     * This happens when the button is in its "clicked" state and another linked button is prssed.
     * @param effect the ClickEffect that will be run. Consider using a lambda here.
     */
    public void setOnReset (@Nullable ClickEffect effect) { onReset = effect; }

    /**
     * Establishes a two-way link between two radio buttons and all buttons linked to both of them.
     * This effectively merges their two linked groups.
     * @param other the other RadioButton to link. Remember that this link is two-way; you only have to do it once.
     */
    public void link(@NotNull RadioButton other) {
        other.linked.addAll(this.linked);
        this.linked = other.linked;
    }

    public boolean getIsClicked() { return isClicked; }
}
