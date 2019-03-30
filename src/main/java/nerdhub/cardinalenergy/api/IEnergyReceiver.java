package nerdhub.cardinalenergy.api;

import net.minecraft.util.math.Direction;

/**
 * Implemented on an energy handler object to specify that it can receive energy
 *
 * Note that this can also be used with {@link IEnergyProvider}
 */
public interface IEnergyReceiver extends IEnergyHandler {

    /**
     * Receive energy from a given direction
     * @param direction - The direction to receive from, could be null
     * @param amount - The amount of energy to receive
     * @return - Returns the amount of energy received
     */
    int receiveEnergy(Direction direction, int amount);
}
