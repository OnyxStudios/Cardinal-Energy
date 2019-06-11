package nerdhub.cardinalenergy.impl;

import nerdhub.cardinal.components.api.component.Component;
import nerdhub.cardinalenergy.api.IEnergyItemStorage;
import nerdhub.cardinalenergy.api.IEnergyStorage;
import nerdhub.cardinalenergy.impl.example.ItemEnergyImpl;
import net.minecraft.nbt.CompoundTag;

/**
 * An implementation of {@link IEnergyItemStorage}
 *
 * An example implementation of this can be found at {@link ItemEnergyImpl}
 */
public class ItemEnergyStorage implements IEnergyItemStorage {

    public static final String ENERGY_TAG = "cardinal";

    private int capacity;
    private int energyStored;

    public ItemEnergyStorage(int capacity) {
        this(capacity, 0);
    }

    public ItemEnergyStorage(int capacity, int energyStored) {
        this.capacity = capacity;
        this.energyStored = 0;
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
    public int extractEnergy(int amount) {
        int extracted = amount;

        if (extracted > energyStored) {
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
    public void deserialize(CompoundTag tag) {
        if(tag.containsKey(ENERGY_TAG)) {
            CompoundTag energyData = tag.getCompound(ENERGY_TAG);
            this.capacity = energyData.getInt("capacity");
            this.energyStored = energyData.getInt("energyStored");
        }
    }

    @Override
    public CompoundTag serialize(CompoundTag tag) {
        CompoundTag energyData = new CompoundTag();
        energyData.putInt("capacity", capacity);
        energyData.putInt("energyStored", energyStored);

        tag.put(ENERGY_TAG, energyData);
        return tag;
    }

    @Override
    public Component newInstance() {
        return new ItemEnergyStorage(capacity, energyStored);
    }

    @Override
    public boolean isComponentEqual(Component other) {
        return other instanceof IEnergyStorage && ((IEnergyStorage) other).getEnergyStored() == energyStored && ((IEnergyStorage) other).getCapacity() == capacity;
    }
}
