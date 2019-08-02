package nerdhub.cardinalenergy;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinalenergy.api.IEnergyItemStorage;
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
    public static final ComponentType<IEnergyStorage> CARDINAL_ENERGY = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(MODID, "cardinal_energy"), IEnergyStorage.class);

    /**
     * The default Cardinal Energy mana ComponentType, meant for entities
     */
    public static final ComponentType<IEnergyItemStorage> MANA_COMPONENT = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(MODID, "mana_type"), IEnergyItemStorage.class);
}
