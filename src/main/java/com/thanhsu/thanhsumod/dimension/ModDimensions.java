package com.thanhsu.thanhsumod.dimension;

import com.thanhsu.thanhsumod.ThanhSuMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {
    public static final ResourceKey<Level> INDEPENDENCE_DIMENSION_KEY = 
            ResourceKey.create(Registries.DIMENSION, ThanhSuMod.id("independence"));
    
    public static final ResourceKey<DimensionType> INDEPENDENCE_DIMENSION_TYPE = 
            ResourceKey.create(Registries.DIMENSION_TYPE, ThanhSuMod.id("independence_type"));

    public static void register() {
        ThanhSuMod.LOGGER.info("Registering dimensions for " + ThanhSuMod.MOD_ID);
    }
}
