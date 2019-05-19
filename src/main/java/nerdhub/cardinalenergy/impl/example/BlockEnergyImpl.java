package nerdhub.cardinalenergy.impl.example;

import nerdhub.cardinal.components.api.BlockComponentProvider;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.Component;
import nerdhub.cardinalenergy.DefaultTypes;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

import java.util.Collections;
import java.util.Set;

/**
 * An example impl of {@link BlockComponentProvider}
 */
public class BlockEnergyImpl extends BlockWithEntity implements BlockComponentProvider {

    protected BlockEnergyImpl(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new BlockEntityEnergyImpl(null);
    }

    /**
     * Check if the ComponentType given is the Cardinal Energy Component
     */
    @Override
    public <T extends Component> boolean hasComponent(BlockView blockView, BlockPos pos, ComponentType<T> type, Direction side) {
        return type == DefaultTypes.CARDINAL_ENERGY;
    }

    /**
     * Get the IEnergyHandler Component the ComponentType given is the Cardinal Energy Component
     */
    @Override
    public <T extends Component> T getComponent(BlockView blockView, BlockPos pos, ComponentType<T> type, Direction side) {
        return type == DefaultTypes.CARDINAL_ENERGY ? (T) blockView.getBlockEntity(pos) :  null;
    }

    /**
     * Get a list of all valid components this block supports
     */
    @Override
    public Set<ComponentType<? extends Component>> getComponentTypes(BlockView blockView, BlockPos pos, Direction side) {
        return Collections.singleton(DefaultTypes.CARDINAL_ENERGY);
    }
}
