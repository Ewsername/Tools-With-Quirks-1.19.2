package net.bunmuppet.toolswithquirks.block;

import net.bunmuppet.toolswithquirks.ToolsWithQuirks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    // Define the slime block
    public static final Block SLIME_BLOCK_ENTITY = registerBlock(
            new SlimeBlockEntityBlock(FabricBlockSettings.of(Material.ICE)
                    .strength(1f)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SLIME)
                    .ticksRandomly()

            ));
    private static Block registerBlock(Block block) {
        registerBlockItem(block); // Register the BlockItem
        return Registry.register(Registry.BLOCK, new Identifier(ToolsWithQuirks.MOD_ID, "slime_block_entity"), block);
    }
    private static void registerBlockItem(Block block) {
        Registry.register(Registry.ITEM, new Identifier(ToolsWithQuirks.MOD_ID, "slime_block_entity"),
                new BlockItem(block, new FabricItemSettings().group(ItemGroup.MISC)));
    }
    public static void registerModBlocks() {
        ToolsWithQuirks.LOGGER.debug("Registering ModBlocks for " + ToolsWithQuirks.MOD_ID);
    }
}
