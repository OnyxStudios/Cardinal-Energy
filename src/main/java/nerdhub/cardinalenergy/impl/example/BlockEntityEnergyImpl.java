package nerdhub.cardinalenergy.impl.example;

import nerdhub.cardinalenergy.api.IEnergyProvider;
import nerdhub.cardinalenergy.api.IEnergyReceiver;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

/**
 * An example impl of {@link IEnergyReceiver}, {@link IEnergyProvider} and {@link EnergyStorage}
 */
public class BlockEntityEnergyImpl extends BlockEntity implements IEnergyReceiver, IEnergyProvider {

    public EnergyStorage storage = new EnergyStorage(10000);

    public BlockEntityEnergyImpl(BlockEntityType<?> blockEntityType_1) {
        super(blockEntityType_1);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        this.storage.writeEnergyToTag(tag);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        this.storage.readEnergyFromTag(tag);
    }

    @Override
    public boolean sendEnergy(BlockPos pos, int amount) {
        return this.storage.sendEnergy(world, pos, amount);
    }

    @Override
    public int receiveEnergy(Direction direction, int amount) {
        //Can also check for a specific direction here
        return this.storage.receiveEnergy(amount);
    }

    @Override
    public EnergyStorage getEnergyStorage() {
        return this.storage;
    }
}
