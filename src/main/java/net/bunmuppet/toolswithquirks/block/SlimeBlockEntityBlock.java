package net.bunmuppet.toolswithquirks.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.World;


public class SlimeBlockEntityBlock extends SlimeBlock {

    public SlimeBlockEntityBlock(Settings settings) {
        super(settings);
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        // Schedule the block to disappear after 20 ticks (1 second)
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.createAndScheduleBlockTick(pos, this, 10); // Schedule a tick after 20 ticks (1 second)
        }
    }

    // This is the method to handle block ticking after the scheduled time
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Replace the block with air when it ticks
        world.setBlockState(pos, net.minecraft.block.Blocks.AIR.getDefaultState());
    }

    // Ensure that this block ticks randomly
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }
}
