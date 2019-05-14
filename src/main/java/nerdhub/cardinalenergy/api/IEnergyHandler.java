package nerdhub.cardinalenergy.api;

import nerdhub.cardinalenergy.DefaultTypes;
import net.minecraft.util.math.Direction;

/**
 * Implemented on a energy handler object to specify that it handles energy
 */
public interface IEnergyHandler<T extends EnergyType> {

    /**
     * Is used to define weather or not cables and energy connections can connect at a given side
     * Defaults to true unless overridden
     * @param direction - The direction to attempt connection, could be null
     * @param type - The EnergyType checking for
     * @return - True if a connection is successful
     */
    default boolean canConnectEnergy(Direction direction, T type) {
        return type == DefaultTypes.CARDINAL_ENERGY;
    }

    /**
     * Is used to define weather this block should not receive energy but provide it
     * Default set to false to always receive
     * @param direction - The direction to attempt sending, could be null
     * @param type - The EnergyType checking for
     * @return - True if it provides energy, false if a receiver
     */
    default boolean isEnergyProvider(Direction direction, T type) {
        return false;
    }

    /**
     * Is used to define weather this block can receive energy
     * @param direction - The direction to attempt receiving, could be null
     * @param type - The EnergyType checking for
     * @return - True if it can receive energy
     */
    default boolean isEnergyReceiver(Direction direction, T type) {
        return type == DefaultTypes.CARDINAL_ENERGY;
    }

    /**
     * Get the EnergyType
     * @param direction - The direction to get the EnergyStorage from, could be null
     * @return - The EnergyType used by the implementing class
     */
    T getEnergyStorage(Direction direction);
}
