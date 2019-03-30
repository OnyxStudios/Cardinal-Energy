package nerdhub.cardinalenergy.api;

import net.minecraft.item.ItemStack;

/**
 * Implemented on an item energy handler object
 *
 * An example implementation can be found at {@link nerdhub.cardinalenergy.impl.ItemEnergyStorage}
 */
public interface IEnergyItemStorage {

    /**
     * Receive energy into an {@link ItemStack}
     * @param stack - The stack to receive into
     * @param amount - The amount to receive
     * @return - Returns the amount received
     */
    int receiveEnergy(ItemStack stack, int amount);

    /**
     * Extract energy from an {@link ItemStack}
     * @param stack - the stack to extract from
     * @param amount - The amount to extract
     * @return - Returns true if successful, false if failed
     */
    boolean extractEnergy(ItemStack stack, int amount);

    /**
     * Sets the amount of energy stored in an {@link ItemStack}
     * @param stack - The stack to modify
     * @param amount - The amount of energy to set
     */
    void setEnergyStored(ItemStack stack, int amount);

    /**
     * Set the capacity of energy that can be stored in an {@link ItemStack}
     * @param stack - The stack to modify
     * @param amount - The amount to set the capacity equal to
     */
    void setEnergyCapacity(ItemStack stack, int amount);

    /**
     * Get the amount of energy stored in an {@link ItemStack}
     * @param stack - The stack to get the energy from
     * @return - Returns the amount of energy stored
     */
    int getEnergyStored(ItemStack stack);

    /**
     * Get the max capacity of energy that can be stored in an {@link ItemStack}
     * @param stack - The stack to get the capacity from
     * @return - Returns the capacity
     */
    int getEnergyCapacity(ItemStack stack);
}
