package dev.rndmorris.tfixins.common.biomes;

import static dev.rndmorris.tfixins.config.FixinsConfig.eerieBiomeColors;
import static dev.rndmorris.tfixins.config.FixinsConfig.eldritchBiomeColors;
import static dev.rndmorris.tfixins.config.FixinsConfig.magicalForestBiomeColors;
import static dev.rndmorris.tfixins.config.FixinsConfig.taintBiomeColors;
import static thaumcraft.common.lib.world.ThaumcraftWorldGenerator.biomeEerie;
import static thaumcraft.common.lib.world.ThaumcraftWorldGenerator.biomeEldritchLands;
import static thaumcraft.common.lib.world.ThaumcraftWorldGenerator.biomeMagicalForest;
import static thaumcraft.common.lib.world.ThaumcraftWorldGenerator.biomeTaint;

public class BiomeOverrides {

    public static void apply() {

        if (eerieBiomeColors.baseSet && biomeEerie != null) {
            biomeEerie.setColor(eerieBiomeColors.base);
        }

        if (eldritchBiomeColors.baseSet && biomeEldritchLands != null) {
            biomeEldritchLands.setColor(eldritchBiomeColors.base);
        }

        if (magicalForestBiomeColors.baseSet && biomeMagicalForest != null) {
            biomeMagicalForest.setColor(magicalForestBiomeColors.base);
        }

        if (taintBiomeColors.baseSet && biomeTaint != null) {
            biomeTaint.setColor(taintBiomeColors.base);
        }

    }

}
