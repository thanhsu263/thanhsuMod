package com.thanhsu.thanhsumod;

import com.thanhsu.thanhsumod.dimension.ModDimensions;
import com.thanhsu.thanhsumod.event.ModEvents;
import com.thanhsu.thanhsumod.item.ModItems;
import com.thanhsu.thanhsumod.util.PlayerDataTracker;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThanhSuMod implements ModInitializer {
    public static final String MOD_ID = "thanhsumod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        PlayerDataTracker.init();
        ModItems.register();
        ModDimensions.register();
        ModEvents.register();
        LOGGER.info("ThanhSu Mod initialized!");
    }
}
