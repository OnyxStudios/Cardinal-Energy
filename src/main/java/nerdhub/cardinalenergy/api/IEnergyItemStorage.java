package nerdhub.cardinalenergy.api;

import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Implemented on an item energy handler object
 *
 * An example implementation can be found at {@link nerdhub.cardinalenergy.impl.ItemEnergyStorage}
 */
public interface IEnergyItemStorage extends IEnergyStorage {

    @Override
    void deserialize(CompoundTag tag);

    @Override
    CompoundTag serialize(CompoundTag tag);

    @Override
    Component newInstance();

    @Override
    boolean isComponentEqual(Component other);

    @Override
    default int sendEnergy(World world, BlockPos pos, int amount) {
        throw new IllegalStateException("Tried to access IEnergyStorage methods from an IEnergyItemStorage");
    }

    @Override
    default CompoundTag writeEnergyToTag(CompoundTag tag) {
        throw new IllegalStateException("Tried to access IEnergyStorage methods from an IEnergyItemStorage");
    }

    @Override
    default void readEnergyFromTag(CompoundTag tag) {
        throw new IllegalStateException("Tried to access IEnergyStorage methods from an IEnergyItemStorage");
    }
}
