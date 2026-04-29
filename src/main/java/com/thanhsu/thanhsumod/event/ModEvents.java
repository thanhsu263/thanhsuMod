package com.thanhsu.thanhsumod.event;

import com.thanhsu.thanhsumod.ThanhSuMod;
import com.thanhsu.thanhsumod.dimension.ModDimensions;
import com.thanhsu.thanhsumod.world.StructureSpawner;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.GameRules;

public class ModEvents {
    public static void register() {
        ThanhSuMod.LOGGER.info("Registering events for " + ThanhSuMod.MOD_ID);

        // Prevent block breaking in Independence dimension (except creative mode)
        PlayerBlockBreakEvents.BEFORE.register((level, player, pos, state, blockEntity) -> {
            if (level.dimension().equals(ModDimensions.INDEPENDENCE_DIMENSION_KEY)) {
                // Allow breaking blocks in creative mode
                if (player.isCreative()) {
                    return true;
                }
                return false; // Cancel block break for non-creative players
            }
            return true;
        });

        // Prevent block placing in Independence dimension (except creative mode)
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            if (level.dimension().equals(ModDimensions.INDEPENDENCE_DIMENSION_KEY)) {
                // Allow placing blocks in creative mode
                if (player.isCreative()) {
                    return InteractionResult.PASS;
                }
                return InteractionResult.FAIL; // Cancel block place for non-creative players
            }
            return InteractionResult.PASS;
        });

        // Setup dimension when it loads
        ServerWorldEvents.LOAD.register((server, level) -> {
            if (level.dimension().equals(ModDimensions.INDEPENDENCE_DIMENSION_KEY)) {
                setupIndependenceDimension(level);
            }
        });

        // Apply Night Vision to players in Independence Dimension every tick
        ServerTickEvents.END_WORLD_TICK.register(level -> {
            if (level.dimension().equals(ModDimensions.INDEPENDENCE_DIMENSION_KEY)) {
                for (ServerPlayer player : level.players()) {
                    // Apply Night Vision with 20 seconds duration, ambient (no particles), not visible
                    MobEffectInstance nightVision = new MobEffectInstance(
                        MobEffects.NIGHT_VISION,
                        400, // 20 seconds (refreshed every tick)
                        0,   // Amplifier 0 (level 1)
                        true, // Ambient (no particles)
                        false // Not visible (no icon in inventory)
                    );
                    player.addEffect(nightVision);
                }
            }
        });
    }

    private static void setupIndependenceDimension(ServerLevel level) {
        // Disable mob spawning
        level.getGameRules().getRule(GameRules.RULE_DOMOBSPAWNING).set(false, level.getServer());

        // Disable weather
        level.getGameRules().getRule(GameRules.RULE_WEATHER_CYCLE).set(false, level.getServer());
        level.setWeatherParameters(0, 0, false, false);

        // Lock time to day
        level.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(false, level.getServer());
        level.setDayTime(6000); // Noon

        // Spawn structure at origin if not already present
        BlockPos spawnPos = new BlockPos(0, 65, 0);
        StructureSpawner.spawnStructure(level, spawnPos);
    }
}
