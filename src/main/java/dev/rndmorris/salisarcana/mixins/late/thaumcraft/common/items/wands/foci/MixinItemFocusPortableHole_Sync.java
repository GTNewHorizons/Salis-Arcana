package dev.rndmorris.salisarcana.mixins.late.thaumcraft.common.items.wands.foci;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import dev.rndmorris.salisarcana.network.MessagePortableHoleSync;
import dev.rndmorris.salisarcana.network.NetworkHandler;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.foci.ItemFocusPortableHole;

@Mixin(value = ItemFocusPortableHole.class, remap = false)
public class MixinItemFocusPortableHole_Sync {

    @Inject(method = "onFocusRightClick", at = @At("HEAD"), cancellable = true)
    private void sa$skipClientPrediction(ItemStack itemstack, World world, EntityPlayer player,
        MovingObjectPosition mop, CallbackInfoReturnable<ItemStack> cir) {
        if (world.isRemote) {
            if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK) {
                player.swingItem();
            }
            cir.setReturnValue(itemstack);
        }
    }

    @Inject(method = "onFocusRightClick", at = @At("RETURN"))
    private void sa$syncOpeningSparkle(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop,
        CallbackInfoReturnable<ItemStack> cir) {
        if (world.isRemote || mop == null || mop.typeOfHit != MovingObjectType.BLOCK) {
            return;
        }

        if (world.getBlock(mop.blockX, mop.blockY, mop.blockZ) == ConfigBlocks.blockHole) {
            NetworkHandler.instance.sendToAllAround(
                new MessagePortableHoleSync(mop.blockX, mop.blockY, mop.blockZ),
                new TargetPoint(
                    world.provider.dimensionId,
                    mop.blockX + 0.5D,
                    mop.blockY + 0.5D,
                    mop.blockZ + 0.5D,
                    64.0D));
        }
    }
}
