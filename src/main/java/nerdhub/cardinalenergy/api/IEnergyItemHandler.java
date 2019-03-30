package nerdhub.cardinalenergy.api;

import nerdhub.cardinalenergy.impl.ItemEnergyStorage;

/**
 * Implemented on a energy handler item to specify that it handles energy
 */
public interface IEnergyItemHandler {

    /**
     * Get the ItemEnergyStorage
     * @return - The ItemEnergyStorage used by the implementing class
     */
    ItemEnergyStorage getEnergyStorage();
}
