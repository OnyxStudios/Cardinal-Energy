package nerdhub.cardinalenergy.api;

import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.util.math.BlockPos;

/**
 * Implemented on an energy handler object to specify that it provides energy
 *
 * Note that this can also be used with {@link IEnergyReceiver}
 */
public interface IEnergyProvider extends IEnergyHandler {

    /**
     * Used to send energy to a specified BlockPos or adjacent blocks, distribution is left to the energy handler object to implement
     * Could also be used to call {@link EnergyStorage#sendEnergy}
     * @param pos - The BlockPos to send energy too
     * @param amount - The amount of energy to send
     * @return - Returns true if successful, false if failure
     */
    boolean sendEnergy(BlockPos pos, int amount);
}
