package nerdhub.cardinalenergy.impl.example;

import nerdhub.cardinalenergy.api.IEnergyItemHandler;
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
 * An example implementation of {@link ItemEnergyStorage} and {@link IEnergyItemHandler}
 */
public class ItemEnergyImpl extends Item implements IEnergyItemHandler {

    public ItemEnergyStorage storage = new ItemEnergyStorage(100000);

    public ItemEnergyImpl(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Example extracting energy when the item is used
        storage.extractEnergy(playerEntity.getMainHandStack(), 100);
        return new TypedActionResult(ActionResult.SUCCESS, playerEntity.getMainHandStack());
    }

    @Override
    public void onEntityTick(ItemStack stack, World world, Entity entity, int int_1, boolean boolean_1) {
        //Example adding energy every tick
        storage.receiveEnergy(stack, 1);
    }

    @Override
    public ItemEnergyStorage getEnergyStorage() {
        return this.storage;
    }
}
