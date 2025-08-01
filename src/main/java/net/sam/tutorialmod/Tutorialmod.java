package net.sam.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.sam.tutorialmod.block.ModBlocks;
import net.sam.tutorialmod.item.ModItemGroups;
import net.sam.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// very important comment
public class Tutorialmod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}