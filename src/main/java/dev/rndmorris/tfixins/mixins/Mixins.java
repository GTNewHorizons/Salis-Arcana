package dev.rndmorris.tfixins.mixins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;
import dev.rndmorris.tfixins.config.FixinsConfig;
import dev.rndmorris.tfixins.config.IEnabler;

public enum Mixins {

    // spotless:off

    // Biome colors
    BIOME_EERIE_MODULE(MixinSide.BOTH, FixinsConfig.biomeColorModule, "world.biomes.MixinBiomeGenEerie"),
    BIOME_ELDRITCH_MODULE(MixinSide.BOTH, FixinsConfig.biomeColorModule, "world.biomes.MixinBiomeGenEldritch"),
    BIOME_MAGICALFOREST_MODULE(MixinSide.BOTH, FixinsConfig.biomeColorModule, "world.biomes.MixinBiomeGenMagicalForest"),
    BIOME_TAINT_MODULE(MixinSide.BOTH, FixinsConfig.biomeColorModule, "world.biomes.MixinBiomeGenTaint"),

    // Bugfixes
    ARCANE_FURNACE_DUPE_FIX(MixinSide.BOTH, FixinsConfig.bugfixesModule.infernalFurnaceDupeFix, "blocks.MixinBlockArcaneFurnace"),
    BEACON_BLOCKS(MixinSide.BOTH, FixinsConfig.bugfixesModule.blockCosmeticSolidBeaconFix, "blocks.MixinBlockCosmeticSolid"),
    BLOCKCANDLE_OOB(MixinSide.BOTH, FixinsConfig.bugfixesModule.candleRendererCrashes, "blocks.MixinBlockCandleRenderer", "blocks.MixinBlockCandle"),
    DEAD_MOBS_DONT_ATTACK(MixinSide.BOTH, FixinsConfig.bugfixesModule.deadMobsDontAttack, "entities.MixinEntityTaintacle", "entities.MixinEntityEldritchCrab", "entities.MixinEntityThaumicSlime"),
    INTEGER_INFUSION_MATRIX(MixinSide.BOTH, FixinsConfig.bugfixesModule.integerInfusionMatrixMath, "tiles.MixinTileInfusionMatrix_IntegerStabilizers"),
    ITEMSHARD_OOB(MixinSide.BOTH, FixinsConfig.bugfixesModule.itemShardColor, "items.MixinItemShard"),
    RENDER_REDSTONE_FIX(MixinSide.BOTH, FixinsConfig.bugfixesModule.renderRedstoneFix, "blocks.MixinBlockCustomOre"),
    SUPPRESS_CREATIVE_WARP(MixinSide.BOTH, FixinsConfig.bugfixesModule.suppressWarpEventsInCreative, "events.MixinEventHandlerEntity"),
    STRICT_INFUSION_INPUTS(MixinSide.BOTH, FixinsConfig.bugfixesModule.strictInfusionMatrixInputChecks, "tiles.MixinTileInfusionMatrix_InputEnforcement"),

    // Baubles
    EXTENDED_BAUBLES_SUPPORT(MixinSide.BOTH, FixinsConfig.bugfixesModule.useAllBaublesSlots, "events.MixinEventHandlerRunic", "items.MixinWandManager"),
    EXTENDED_BAUBLES_SUPPORT_CLIENT(MixinSide.CLIENT, FixinsConfig.bugfixesModule.useAllBaublesSlots, "gui.MixinREHWandHandler"),

    // Thaumonomicon
    CTRL_SCROLL_NAVIGATION(MixinSide.CLIENT, FixinsConfig.researchBrowserModule.scrollwheelEnabled, "gui.MixinGuiResearchBrowser"),
    RESEARCH_ID_POPUP(MixinSide.CLIENT, FixinsConfig.researchBrowserModule.showResearchId, "gui.MixinGuiResearchBrowser"),
    RIGHT_CLICK_NAVIAGTION(MixinSide.CLIENT, FixinsConfig.researchBrowserModule.rightClickClose, "gui.MixinGuiResearchBrowser", "gui.MixinGuiResearchRecipe"),

    // Tweaks
    NODE_GENERATION_MODIFIER_WEIGHTS(MixinSide.BOTH, FixinsConfig.tweaksModule.nodeModifierWeights, "world.MixinThaumcraftWorldGenerator"),
    NODE_GENERATION_TYPE_WEIGHTS(MixinSide.BOTH, FixinsConfig.tweaksModule.nodeTypeWeights, "world.MixinThaumcraftWorldGenerator"),

    ;
    // spotless:on

    private final List<String> classes;
    private final MixinSide side;
    private final IEnabler config;

    public static List<String> getMixins() {
        final List<String> mixins = new ArrayList<>();
        for (Mixins mixin : Mixins.values()) {
            if (mixin.isEnabled()) {
                mixins.addAll(mixin.classes);
            }
        }
        return mixins;
    }

    Mixins(MixinSide side, IEnabler config, String... classes) {
        this.side = side;
        this.config = config;
        this.classes = Arrays.asList(classes);
    }

    private boolean isEnabled() {
        return side.matchesFMLSide(FMLLaunchHandler.side()) && this.config.isEnabled();
    }

    private enum MixinSide {

        CLIENT,
        SERVER,
        BOTH;

        public boolean matchesFMLSide(Side side) {
            return switch (this) {
                case BOTH -> true;
                case CLIENT -> side == Side.CLIENT;
                case SERVER -> side == Side.SERVER;
            };
        }
    }
}
