package nerdhub.cardinalenergy.impl.example;

import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

/**
 * An example impl of {@link EnergyStorage}
 */
public class BlockEntityEnergyImpl extends BlockEntity implements IEnergyHandler {

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
    public EnergyStorage getEnergyStorage(Direction direction) {
        //Return the direction regardless of the direction
        return this.storage;
    }
}
