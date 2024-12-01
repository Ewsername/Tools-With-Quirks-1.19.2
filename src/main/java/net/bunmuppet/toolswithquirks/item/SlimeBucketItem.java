package net.bunmuppet.toolswithquirks.item;

import net.bunmuppet.toolswithquirks.block.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;

import java.util.HashMap;
import java.util.Map;

public class SlimeBucketItem extends Item {

    // Individual map check for players on server
    private static final Map<PlayerEntity, Long> lastUseTimes = new HashMap<>();
    private static final long DELAY_TIME = 500L; // Delay in milliseconds (e.g., 1000ms = 1 second)

    public SlimeBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        BlockHitResult hitResult = (BlockHitResult) player.raycast(7.0D, 0.0F, false);

        if (!world.isClient && hitResult.getType() == BlockHitResult.Type.BLOCK) {
            BlockPos targetPos = hitResult.getBlockPos();

            // Check if the player is holding a slime-in-a-bucket
            if (stack.getItem() == ModItems.SLIME_IN_A_BUCKET) {
                // Check if enough time has passed since the last use
                long currentTime = System.currentTimeMillis();
                if (lastUseTimes.containsKey(player) && currentTime - lastUseTimes.get(player) < DELAY_TIME) {
                    // If not enough time has passed, prevent further use and do nothing
                    return new TypedActionResult<>(ActionResult.FAIL, stack); // Return a fail result to prevent usage
                }

                Direction hitSide = hitResult.getSide();
                BlockPos placePos = targetPos.offset(hitSide);

                // Check if the surrounding blocks are air and place a 3x3 grid of slime blocks
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        BlockPos newPos = placePos.add(dx, 0, dz);

                        // Ensure the adjacent block is air before placing a slime block
                        if (world.getBlockState(newPos).isAir()) {
                            // Placing a slime block at the new position
                            world.setBlockState(newPos, ModBlocks.SLIME_BLOCK_ENTITY.getDefaultState());

                            // If it's a server world, schedule a tick for the block to disappear
                            if (world instanceof ServerWorld serverWorld) {
                                serverWorld.createAndScheduleBlockTick(newPos, ModBlocks.SLIME_BLOCK_ENTITY, 10); // 10 ticks
                            }
                        }
                    }
                }

                // Replace the slime-in-a-bucket with an empty bucket (optional)
                // player.setStackInHand(hand, new ItemStack(Items.BUCKET));

                // Play sound effect of placing the blocks
                world.playSound(null, placePos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 0.5F);

                // Update the last use time to the current time
                lastUseTimes.put(player, currentTime);

                return new TypedActionResult<>(ActionResult.SUCCESS, stack);
            }
        }

        return new TypedActionResult<>(ActionResult.PASS, stack);
    }
}
