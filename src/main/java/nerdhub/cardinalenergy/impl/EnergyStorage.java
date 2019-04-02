package nerdhub.cardinalenergy.impl;

import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.api.IEnergyStorage;
import nerdhub.cardinalenergy.impl.example.BlockEntityEnergyImpl;
import net.minecraft.block.entity.BlockEntity;
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
    public int receiveEnergy(int amount) {
        int received = amount;

        if (received + energyStored > capacity) {
            received = amount - ((amount + energyStored) - capacity);
        }

        this.energyStored += received;
        return received;
    }

    @Override
    public int sendEnergy(World world, BlockPos pos, int amount) {
        if(amount <= energyStored) {
            if(world.getBlockEntity(pos) instanceof IEnergyHandler) {
                int amountReceived = getEnergyReceiver(world, pos).getEnergyStorage(null).receiveEnergy(amount);
                this.extractEnergy(amountReceived);
                return amountReceived;
            }
        }

        return 0;
    }

    @Override
    public int extractEnergy(int amount) {
        int extracted = amount;

        if(extracted > energyStored) {
            extracted = energyStored;
        }

        this.energyStored -= extracted;
        return extracted;
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
    public boolean canReceive(int amount) {
        return energyStored + amount <= capacity;
    }

    @Override
    public boolean canExtract(int amount) {
        return amount <= energyStored;
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

    public IEnergyHandler getEnergyReceiver(World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof IEnergyHandler && ((IEnergyHandler) blockEntity).canConnectEnergy(null) ? (IEnergyHandler) blockEntity : null;
    }
}