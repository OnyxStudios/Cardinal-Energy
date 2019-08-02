package nerdhub.cardinalenergy.impl.example;

import nerdhub.cardinal.components.api.event.ItemComponentCallback;
import nerdhub.cardinalenergy.DefaultTypes;
import nerdhub.cardinalenergy.api.IEnergyItemHandler;
import nerdhub.cardinalenergy.api.IEnergyStorage;
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
 * An example implementation of {@link ItemEnergyStorage}, {@link IEnergyItemHandler} and {@link ItemComponentCallback}
 */
public class ItemEnergyImpl extends Item implements IEnergyItemHandler {

    public ItemEnergyImpl(Settings settings) {
        super(settings);
        ItemComponentCallback.event(this).register((stack, components) -> components.put(DefaultTypes.CARDINAL_ENERGY, new EnergyStorage(1000)));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Example extracting energy when the item is used
        ItemStack stack = playerEntity.getMainHandStack();
        IEnergyStorage storage = DefaultTypes.CARDINAL_ENERGY.get(stack);
        storage.extractEnergy(100);

        return new TypedActionResult(ActionResult.SUCCESS, playerEntity.getMainHandStack());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int int_1, boolean boolean_1) {
        //Example adding energy every tick
        IEnergyStorage storage = DefaultTypes.CARDINAL_ENERGY.get(stack);
        storage.receiveEnergy(1);
    }
}
