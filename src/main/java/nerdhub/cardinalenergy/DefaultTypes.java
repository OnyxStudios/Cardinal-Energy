package nerdhub.cardinalenergy;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinalenergy.api.EnergyType;
import nerdhub.cardinalenergy.api.IEnergyItemStorage;
import nerdhub.cardinalenergy.api.IEnergyStorage;
import nerdhub.cardinalenergy.impl.EnergyStorage;

public class DefaultTypes {

    public static final ComponentType<IEnergyStorage> CARDINAL_ENERGY_COMPONENT = ComponentRegistry.getOrCreate(IEnergyStorage.class);
    public static final ComponentType<IEnergyItemStorage> CARDINAL_ENERGY_ITEM_COMPONENT = ComponentRegistry.getOrCreate(IEnergyItemStorage.class);
    public static EnergyType CARDINAL_ENERGY = new EnergyStorage();
}
