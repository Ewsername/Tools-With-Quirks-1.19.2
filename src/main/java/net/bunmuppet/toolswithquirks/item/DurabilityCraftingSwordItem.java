package net.bunmuppet.toolswithquirks.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class DurabilityCraftingSwordItem extends SwordItem {

    public DurabilityCraftingSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean hasRecipeRemainder() {
        return true; // Ensures the item isn't consumed in crafting
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        // Create a copy of the stack to modify
        ItemStack remainder = stack.copy();

        // Increment the item's damage to reduce durability
        remainder.setDamage(stack.getDamage() + 5);

        // If the item is about to break, return the broken state (ItemStack.EMPTY)
        if (remainder.getDamage() >= remainder.getMaxDamage()) {
            return ItemStack.EMPTY;
        }

        // Otherwise, return the updated item with reduced durability
        return remainder;
    }
}
