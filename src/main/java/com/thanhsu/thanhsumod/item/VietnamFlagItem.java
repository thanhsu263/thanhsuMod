package com.thanhsu.thanhsumod.item;

import com.thanhsu.thanhsumod.dimension.ModDimensions;
import com.thanhsu.thanhsumod.util.PlayerDataTracker;
import com.thanhsu.thanhsumod.world.StructureSpawner;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class VietnamFlagItem extends Item {
    private static final int USE_DURATION = 60; // 3 seconds (20 ticks = 1 second)

    public VietnamFlagItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW; // Wave animation
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return USE_DURATION;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof ServerPlayer player) {
            ServerLevel currentLevel = player.serverLevel();

            // Check if player is in Independence Dimension
            if (currentLevel.dimension().equals(ModDimensions.INDEPENDENCE_DIMENSION_KEY)) {
                // Teleport back to Overworld
                ServerLevel overworld = currentLevel.getServer().overworld();
                player.teleportTo(overworld,
                    overworld.getSharedSpawnPos().getX() + 0.5,
                    overworld.getSharedSpawnPos().getY(),
                    overworld.getSharedSpawnPos().getZ() + 0.5,
                    0, 0);
            } else {
                // Teleport to Independence Dimension
                ServerLevel independenceDimension = currentLevel.getServer()
                        .getLevel(ModDimensions.INDEPENDENCE_DIMENSION_KEY);

                if (independenceDimension != null) {
                    BlockPos spawnPos = new BlockPos(0, 65, 0);

                    // Try to spawn structure when player teleports
                    StructureSpawner.spawnStructure(independenceDimension, spawnPos);

                    // Teleport to specific coordinates with facing direction
                    // Position: 240.995, 67.0, 134.008
                    // Facing: Yaw 91.8 (west), Pitch -33.8 (looking slightly up)
                    player.teleportTo(independenceDimension, 240.995, 67.0, 134.008, 91.8f, -33.8f);

                    // Check if player has visited before
                    if (!PlayerDataTracker.hasVisited(player.getUUID())) {
                        // Mark as visited
                        PlayerDataTracker.markAsVisited(player.getUUID());

                        // Play memorial video fullscreen using AnimatedFrames server command
                        player.getServer().getCommands().performPrefixedCommand(
                            player.getServer().createCommandSourceStack(),
                            "medias show @p memorial.mp4 0 fullscreen"
                        );
                    }

                    // Broadcast welcome message using say command
                    player.getServer().getCommands().performPrefixedCommand(
                        player.getServer().createCommandSourceStack(),
                        "say " + player.getName().getString() + " - Chào mừng đến Dinh Độc Lập"
                    );
                }
            }
        }
        return stack;
    }
}
