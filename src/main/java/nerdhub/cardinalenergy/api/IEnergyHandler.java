package nerdhub.cardinalenergy.api;

import nerdhub.cardinalenergy.impl.EnergyStorage;

/**
 * Implemented on a energy handler object to specify that it handles energy
 *
 * Note, this is not needed if implementing {@link IEnergyProvider} or {@link IEnergyReceiver}
 */
public interface IEnergyHandler {

    /**
     * Get the EnergyStorage
     * @return - The EnergyStorage used by the implementing class
     */
    EnergyStorage getEnergyStorage();
}
