package net.braia.tutorialmod;

import net.braia.tutorialmod.datagen.ModBlockLootTableProvider;
import net.braia.tutorialmod.datagen.ModBlockTagsProvider;
import net.braia.tutorialmod.datagen.ModModelProvider;
import net.braia.tutorialmod.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jspecify.annotations.NonNull;

public class TutorialModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(@NonNull FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
