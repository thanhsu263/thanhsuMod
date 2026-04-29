package com.thanhsu.thanhsumod.item;

import com.thanhsu.thanhsumod.ThanhSuMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Item VIETNAM_STAR = register("vietnam_star",
            new VietnamStarItem(new Item.Properties()));
    
    public static final Item FLAG = register("flag",
            new FlagItem(new Item.Properties()));

    public static final Item VIETNAM_FLAG = register("vietnam_flag",
            new VietnamFlagItem(new Item.Properties().stacksTo(1)));

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ThanhSuMod.id(name), item);
    }

    public static void register() {
        ThanhSuMod.LOGGER.info("Registering items for " + ThanhSuMod.MOD_ID);

        // Add to Tools creative tab
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(VIETNAM_STAR);
            entries.accept(FLAG);
            entries.accept(VIETNAM_FLAG);
        });
    }
}
