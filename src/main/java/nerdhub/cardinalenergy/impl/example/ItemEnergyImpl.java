package nerdhub.cardinalenergy.impl.example;

import nerdhub.cardinal.components.api.ItemComponentProvider;
import nerdhub.cardinal.components.api.accessor.StackComponentAccessor;
import nerdhub.cardinalenergy.DefaultTypes;
import nerdhub.cardinalenergy.api.IEnergyItemHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import nerdhub.cardinalenergy.impl.ItemEnergyStorage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * An example implementation of {@link ItemEnergyStorage}, {@link IEnergyItemHandler} and {@link ItemComponentProvider}
 */
public class ItemEnergyImpl extends Item implements IEnergyItemHandler, ItemComponentProvider {

    public ItemEnergyImpl(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Example extracting energy when the item is used
        ItemStack stack = playerEntity.getMainHandStack();
        StackComponentAccessor stackComponentAccessor = (StackComponentAccessor) (Object) stack;

        if(stackComponentAccessor.hasComponent(DefaultTypes.CARDINAL_ENERGY)) {
            stackComponentAccessor.getComponent(DefaultTypes.CARDINAL_ENERGY).extractEnergy(100);
        }

        return new TypedActionResult(ActionResult.SUCCESS, playerEntity.getMainHandStack());
    }

    @Override
    public void onEntityTick(ItemStack stack, World world, Entity entity, int int_1, boolean boolean_1) {
        //Example adding energy every tick
        StackComponentAccessor stackComponentAccessor = (StackComponentAccessor) (Object) stack;

        if(stackComponentAccessor.hasComponent(DefaultTypes.CARDINAL_ENERGY)) {
            stackComponentAccessor.getComponent(DefaultTypes.CARDINAL_ENERGY).receiveEnergy(1);
        }
    }

    /**
     * Overridden from {@link ItemComponentProvider} to create our components
     */
    @Override
    public void createComponents(ItemStack stack) {
        //Here we call addComponent and set a default EnergyStorage component with a capacity of 1000
        addComponent(stack, DefaultTypes.CARDINAL_ENERGY, new EnergyStorage(1000));
    }
}
