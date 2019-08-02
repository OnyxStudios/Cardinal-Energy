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
    boolean isComponentEqual(Component other);

    @Override
    default int sendEnergy(World world, BlockPos pos, int amount) {
        throw new IllegalStateException("Tried to access IEnergyStorage methods from an IEnergyItemStorage");
    }
}
