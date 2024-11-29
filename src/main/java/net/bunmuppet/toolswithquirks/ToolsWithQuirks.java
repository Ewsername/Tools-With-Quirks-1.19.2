package net.bunmuppet.toolswithquirks;
import net.bunmuppet.toolswithquirks.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolsWithQuirks implements ModInitializer {
	public static final String MOD_ID = "tools-with-quirks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
	}
}