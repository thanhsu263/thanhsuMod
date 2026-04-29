package com.thanhsu.thanhsumod.world;

import com.thanhsu.thanhsumod.ThanhSuMod;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class StructureSpawner {
    private static final Set<String> spawnedDimensions = new HashSet<>();

    public static void spawnStructure(ServerLevel level, BlockPos pos) {
        String dimensionKey = level.dimension().location().toString();

        if (spawnedDimensions.contains(dimensionKey)) {
            ThanhSuMod.LOGGER.info("Structure already spawned in this dimension");
            return; // Already spawned in this dimension
        }

        ThanhSuMod.LOGGER.info("Attempting to spawn structure at " + pos);

        // Load structure from NBT file
        boolean loaded = tryLoadNBT(level, pos);

        if (loaded) {
            spawnedDimensions.add(dimensionKey);
            ThanhSuMod.LOGGER.info("Dinh Doc Lap structure spawned successfully at " + pos);
        } else {
            ThanhSuMod.LOGGER.error("Failed to load structure from .nbt file");
        }
    }

    private static boolean tryLoadSchematic(ServerLevel level, BlockPos pos) {
        try {
            InputStream stream = StructureSpawner.class.getResourceAsStream(
                    "/data/thanhsumod/structures/dinh_doc_lap.schem");

            if (stream == null) {
                ThanhSuMod.LOGGER.info("No .schem file found, will try .nbt");
                return false;
            }

            ThanhSuMod.LOGGER.info("Loading structure from .schem file...");
            CompoundTag nbt = NbtIo.readCompressed(stream, NbtAccounter.unlimitedHeap());
            stream.close();

            // Sponge schematic format has structure data under different tags
            if (nbt.contains("Blocks") || nbt.contains("BlockData")) {
                StructureTemplate template = new StructureTemplate();
                template.load(level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.BLOCK), nbt);

                StructurePlaceSettings settings = new StructurePlaceSettings()
                        .setIgnoreEntities(false);

                template.placeInWorld(level, pos, pos, settings, level.random, 2);
                return true;
            }

            ThanhSuMod.LOGGER.warn(".schem file format not recognized");
            return false;
        } catch (Exception e) {
            ThanhSuMod.LOGGER.warn("Failed to load .schem: " + e.getMessage());
            return false;
        }
    }

    private static boolean tryLoadNBT(ServerLevel level, BlockPos pos) {
        try {
            InputStream stream = StructureSpawner.class.getResourceAsStream(
                    "/data/thanhsumod/structures/dinh_doc_lap.nbt");

            if (stream == null) {
                ThanhSuMod.LOGGER.error("Could not find structure file: dinh_doc_lap.nbt");
                return false;
            }

            ThanhSuMod.LOGGER.info("Loading structure from .nbt file...");
            CompoundTag nbt = NbtIo.readCompressed(stream, NbtAccounter.unlimitedHeap());
            stream.close();

            StructureTemplate template = new StructureTemplate();
            template.load(level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.BLOCK), nbt);

            StructurePlaceSettings settings = new StructurePlaceSettings()
                    .setIgnoreEntities(false);

            template.placeInWorld(level, pos, pos, settings, level.random, 2);
            return true;
        } catch (Exception e) {
            ThanhSuMod.LOGGER.error("Failed to load .nbt: " + e.getMessage(), e);
            return false;
        }
    }
}
