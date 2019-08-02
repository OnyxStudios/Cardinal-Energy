package nerdhub.cardinalenergy.impl;

import nerdhub.cardinal.components.api.component.BlockComponentProvider;
import nerdhub.cardinal.components.api.component.extension.CloneableComponent;
import nerdhub.cardinalenergy.DefaultTypes;
import nerdhub.cardinalenergy.api.IEnergyHandler;
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

    public EnergyStorage() {
        this(0, 0);
    }

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

            if(isEnergyReceiver(world, pos)) {
                int amountReceived = getEnergyReceiver(world, pos).receiveEnergy(amount);
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
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public void setCapacity(int maxCapacity) {
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
    public CompoundTag toTag(CompoundTag nbt) {
        nbt.putInt("capacity", capacity);
        nbt.putInt("energyStored", energyStored);
        return nbt;
    }

    @Override
    public void fromTag(CompoundTag nbt) {
        capacity = nbt.getInt("capacity");
        energyStored = nbt.getInt("energyStored");
    }

    @Override
    public CloneableComponent newInstance() {
        return new EnergyStorage(capacity, energyStored);
    }

    public IEnergyStorage getEnergyReceiver(World world, BlockPos pos) {
        BlockComponentProvider componentProvider = (BlockComponentProvider) world.getBlockState(pos).getBlock();

        if(world.getBlockEntity(pos) instanceof IEnergyHandler && componentProvider.hasComponent(world, pos, DefaultTypes.CARDINAL_ENERGY, null)) {
            IEnergyHandler energyHandler = (IEnergyHandler) world.getBlockEntity(pos);
            return energyHandler.canConnectEnergy(null, DefaultTypes.CARDINAL_ENERGY) ? componentProvider.getComponent(world, pos, DefaultTypes.CARDINAL_ENERGY, null) : null;
        }

        return null;
    }

    public boolean isEnergyReceiver(World world, BlockPos pos) {
        BlockComponentProvider componentProvider = (BlockComponentProvider) world.getBlockState(pos).getBlock();

        if(world.getBlockEntity(pos) instanceof IEnergyHandler && componentProvider.hasComponent(world, pos, DefaultTypes.CARDINAL_ENERGY, null)) {
            return ((IEnergyHandler) world.getBlockEntity(pos)).isEnergyReceiver(null, DefaultTypes.CARDINAL_ENERGY);
        }

        return false;
    }
}