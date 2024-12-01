package net.bunmuppet.toolswithquirks;
import net.bunmuppet.toolswithquirks.block.ModBlocks;
import net.bunmuppet.toolswithquirks.block.SlimeBlockEntityBlock;
import net.bunmuppet.toolswithquirks.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolsWithQuirks implements ModInitializer {
	public static final String MOD_ID = "tools-with-quirks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModItems.registerItem();
		ModBlocks.registerModBlocks();
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SLIME_BLOCK_ENTITY, RenderLayer.getTranslucent());
	}
}