package nerdhub.cardinalenergy.api;

import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.util.math.Direction;

/**
 * Implemented on a energy handler item to specify that it handles energy
 */
public interface IEnergyItemHandler extends IEnergyHandler {

    @Override
    default boolean canConnectEnergy(Direction direction, ComponentType type) {
        throw new IllegalStateException("Tried to access IEnergyHandler methods from an IEnergyItemHandler");
    }

    @Override
    default boolean isEnergyProvider(Direction direction, ComponentType type) {
        throw new IllegalStateException("Tried to access IEnergyHandler methods from an IEnergyItemHandler");
    }

    @Override
    default boolean isEnergyReceiver(Direction direction, ComponentType type) {
        throw new IllegalStateException("Tried to access IEnergyHandler methods from an IEnergyItemHandler");
    }
}
