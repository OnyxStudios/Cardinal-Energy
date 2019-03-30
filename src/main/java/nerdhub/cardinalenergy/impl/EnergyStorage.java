package nerdhub.cardinalenergy.impl;

import nerdhub.cardinalenergy.api.IEnergyReceiver;
import nerdhub.cardinalenergy.api.IEnergyStorage;
import nerdhub.cardinalenergy.impl.example.BlockEntityEnergyImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * An implementation of {@link IEnergyStorage}
 *
 * An example implementation of this can be found at {@link BlockEntityEnergyImpl}
 */
public class EnergyStorage implements IEnergyStorage {

    private int capacity;
    private int energyStored;

    public EnergyStorage(int capacity) {
        this(capacity, 0);
    }

    public EnergyStorage(int capacity, int amount) {
        this.capacity = capacity;
        this.energyStored = amount;
    }

    @Override
    public int receiveEnergy(int recieve) {
        this.energyStored += recieve;

        if (energyStored > capacity) {
            int extra = energyStored - capacity;
            this.energyStored = capacity;
            return extra;
        }

        return recieve;
    }

    @Override
    public boolean sendEnergy(World world, BlockPos pos, int amount) {
        if(amount <= energyStored) {
            if(world.getBlockEntity(pos) instanceof IEnergyReceiver) {
                int amountReceived = getEnergyReceiver(world, pos).receiveEnergy(null, amount);
                this.extractEnergy(amountReceived);
                return true;
            }
        }

        return false;
    }

    public IEnergyReceiver getEnergyReceiver(World world, BlockPos pos) {
        return (IEnergyReceiver) world.getBlockEntity(pos);
    }

    @Override
    public void extractEnergy(int amount) {
        this.energyStored -= amount;

        if(amount > energyStored) {
            this.energyStored = 0;
        }
    }

    @Override
    public int getEnergyStored() {
        return energyStored;
    }

    @Override
    public int getEnergyCapacity() {
        return this.capacity;
    }

    @Override
    public void setEnergyCapacity(int maxCapacity) {
        this.capacity = maxCapacity;
    }

    @Override
    public void setEnergyStored(int energy) {
        this.energyStored = energy;
    }

    @Override
    public CompoundTag writeEnergyToTag(CompoundTag nbt) {
        nbt.putInt("capacity", capacity);
        nbt.putInt("energyStored", energyStored);
        return nbt;
    }

    @Override
    public EnergyStorage readEnergyFromTag(CompoundTag nbt) {
        capacity = nbt.getInt("capacity");
        energyStored = nbt.getInt("energyStored");
        return this;
    }
}