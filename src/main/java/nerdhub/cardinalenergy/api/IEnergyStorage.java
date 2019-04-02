package nerdhub.cardinalenergy.api;

import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * An interface used internally in an {@link EnergyStorage}
 */
public interface IEnergyStorage {

    /**
     * Receive energy internally
     *
     * @param amount - The amount of energy to fill
     * @return - Returns the amount of energy received
     */
    int receiveEnergy(int amount);

    /**
     * Send energy to a {@link IEnergyHandler} at a given BlockPos
     *
     * @param world  - The world used to get the BlockEntity
     * @param pos    - The position of the IEnergyReceiver
     * @param amount - The amount of energy to send
     * @return - Returns the amount of energy sent
     */
    int sendEnergy(World world, BlockPos pos, int amount);

    /**
     * Extract energy internally
     *
     * @param amount - The amount of energy to extract
     * @return - The amount of energy extracted
     */
    int extractEnergy(int amount);

    /**
     * Get the total amount of energy stored
     *
     * @return - The amount of energy stored
     */
    int getEnergyStored();

    /**
     * Get the max capacity of energy that can be stored
     *
     * @return - The energy capacity
     */
    int getEnergyCapacity();

    /**
     * Set the capacity of energy that can be stored
     *
     * @param maxCapacity - The new capacity to replace with
     */
    void setEnergyCapacity(int maxCapacity);

    /**
     * Set the amount of energy stored
     *
     * @param energy - The amount of energy to set
     */
    void setEnergyStored(int energy);

    /**
     * Check if the EnergyStorage can receive a set amount of energy
     * @param amount - The amount to check for
     * @return - True if it can receive, false if not
     */
    boolean canReceive(int amount);

    /**
     * Check if the EnergyStorage can extract a set amount of energy
     * @param amount - The amount to check for
     * @return - True if it can extract, false if not
     */
    boolean canExtract(int amount);

    /**
     * Write the energy data to a CompoundTag
     *
     * @param tag - The tag to write the energy data to
     * @return - The CompoundTag with the energy data added
     */
    CompoundTag writeEnergyToTag(CompoundTag tag);

    /**
     * Read energy data from a CompoundTag
     *
     * @param tag - The tag to read from
     * @return - Returns an EnergyStorage with the read data
     */
    EnergyStorage readEnergyFromTag(CompoundTag tag);
}