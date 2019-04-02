package nerdhub.cardinalenergy.api;

import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.util.math.Direction;

/**
 * Implemented on a energy handler object to specify that it handles energy
 *
 * Note, this is not needed if implementing {@link IEnergyProvider} or {@link IEnergyReceiver}
 */
public interface IEnergyHandler {

    /**
     * Is used to define weather or not cables and energy connections can connect at a given side
     * Defaults to true unless overridden
     * @param direction - The direction to attempt connection, could be null
     * @return - True if a connection is successful
     */
    default boolean canConnectEnergy(Direction direction) {
        return true;
    }

    /**
     * Get the EnergyStorage
     * @param direction - The direction to get the EnergyStorage from, could be null
     * @return - The EnergyStorage used by the implementing class
     */
    EnergyStorage getEnergyStorage(Direction direction);
}
