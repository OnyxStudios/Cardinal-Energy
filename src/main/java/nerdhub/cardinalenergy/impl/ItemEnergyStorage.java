package nerdhub.cardinalenergy.impl;

import nerdhub.cardinalenergy.api.IEnergyItemStorage;
import nerdhub.cardinalenergy.impl.example.ItemEnergyImpl;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

/**
 * An implementation of {@link IEnergyItemStorage}
 *
 * An example implementation of this can be found at {@link ItemEnergyImpl}
 */
public class ItemEnergyStorage implements IEnergyItemStorage {

    public static final String ENERGY_TAG = "cardinal";

    private int capacity;

    public ItemEnergyStorage(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int receiveEnergy(ItemStack stack, int amount) {
        CompoundTag energyTag = getEnergyTag(stack);

        int energyStored = energyTag.getInt("stored");
        int received = amount;

        if(energyStored > capacity) {
            received = capacity - energyStored;
        }

        energyTag.putInt("stored", energyStored + amount);
        stack.getTag().put(ENERGY_TAG, energyTag);

        return received;
    }

    @Override
    public boolean extractEnergy(ItemStack stack, int amount) {
        CompoundTag energyTag = getEnergyTag(stack);

        int energyStored = energyTag.getInt("stored");

        if(amount <= energyStored) {
            energyTag.putInt("stored", energyStored - amount);
            stack.getTag().put(ENERGY_TAG, energyTag);

            return true;
        }


        return false;
    }

    @Override
    public void setEnergyStored(ItemStack stack, int amount) {
        CompoundTag energyTag = getEnergyTag(stack);
        energyTag.putInt("stored", amount > capacity ? capacity : amount);
        stack.getTag().put(ENERGY_TAG, energyTag);
    }

    @Override
    public void setEnergyCapacity(ItemStack stack, int amount) {
        CompoundTag energyTag = getEnergyTag(stack);
        energyTag.putInt("capacity", amount);
        stack.getTag().put(ENERGY_TAG, energyTag);
    }

    @Override
    public int getEnergyStored(ItemStack stack) {
        return getEnergyTag(stack).getInt("stored");
    }

    @Override
    public int getEnergyCapacity(ItemStack stack) {
        return getEnergyTag(stack).getInt("capacity") != capacity ? getEnergyTag(stack).getInt("capacity") : capacity;
    }

    private CompoundTag getEnergyTag(ItemStack stack) {
        CompoundTag energyTag;

        if(!stack.hasTag() || !stack.getTag().containsKey(ENERGY_TAG)) {
            stack.setTag(new CompoundTag());
            energyTag = new CompoundTag();
            energyTag.putInt("capacity", this.capacity);
            energyTag.putInt("stored", 0);
        }else {
            energyTag = stack.getTag().getCompound(ENERGY_TAG);
        }

        return energyTag;
    }
}
