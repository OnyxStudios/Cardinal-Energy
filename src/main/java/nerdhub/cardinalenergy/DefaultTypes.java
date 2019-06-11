package nerdhub.cardinalenergy;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinalenergy.api.IEnergyStorage;
import net.minecraft.util.Identifier;

/**
 * Default Cardinal Energy component type
 * Can be used or a custom Energy Component can be created using a new ComponentType
 */
public class DefaultTypes {

    public static final String MODID = "cardinalenergy";

    /**
     * The default Cardinal Energy ComponentType
     * To create your own energy type simply create a new ComponentType of IEnergyHandler
     */
    public static final ComponentType<IEnergyStorage> CARDINAL_ENERGY = ComponentRegistry.getOrCreate(IEnergyStorage.class, new Identifier(MODID, "cardinal_energy"));

    /**
     * The default Cardinal Energy mana ComponentType
     */
    public static final ComponentType<IEnergyStorage> MANA_COMPONENT = ComponentRegistry.getOrCreate(IEnergyStorage.class, new Identifier(MODID, "mana_type"));
}
