package nerdhub.cardinalenergy.impl.example;

import nerdhub.cardinal.components.api.BlockComponentProvider;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinalenergy.DefaultTypes;
import nerdhub.cardinalenergy.api.EnergyType;
import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

import java.util.Collections;
import java.util.Set;

/**
 * An example impl of {@link IEnergyHandler}
 */
public class BlockEntityEnergyImpl extends BlockEntity implements IEnergyHandler, BlockComponentProvider {

    //Create an EnergyStorage instance that stores 10,000 energy
    public EnergyStorage storage = new EnergyStorage(10000);

    public BlockEntityEnergyImpl(BlockEntityType<?> blockEntityType_1) {
        super(blockEntityType_1);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        //Write energy to nbt
        this.storage.writeEnergyToTag(tag);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        //Read energy from nbt
        this.storage.readEnergyFromTag(tag);
    }

    @Override
    public EnergyType getEnergyStorage(Direction direction) {
        //Return the EnergyType based on direction, here we discard it
        return this.storage;
    }

    //Example with components
    //IEnergyHandler is not technically needed if using Components
    @Override
    public <T> boolean hasComponent(BlockView blockView, BlockPos pos, ComponentType<T> type, Direction side) {
        return type == DefaultTypes.CARDINAL_ENERGY_COMPONENT;
    }

    @Override
    public <T> T getComponent(BlockView blockView, BlockPos pos, ComponentType<T> type, Direction side) {
        return (T) storage;
    }

    @Override
    public Set<ComponentType<?>> getComponentTypes(BlockView blockView, BlockPos pos, Direction side) {
        return Collections.singleton(DefaultTypes.CARDINAL_ENERGY_COMPONENT);
    }
}
