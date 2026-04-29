# ThanhSu Mod - Vietnam Flag & Independence Dimension

A Fabric mod for Minecraft 1.21.1 that adds:
- **Vietnam Flag item**: Hold right-click for 3 seconds to teleport to Independence Dimension
- **Independence Dimension**: A flat world with barrier blocks, featuring the Dinh Doc Lap structure
  - No natural mob spawning
  - No weather changes
  - Locked to daytime
  - Players cannot break or place blocks

## Building the Mod

### Prerequisites
- Java 21 or higher
- Minecraft 1.21.1
- Fabric Loader 0.19.2+

### Build Instructions

1. Open terminal in the project directory
2. Run the build command:
   ```bash
   ./gradlew build
   ```
   (On Windows: `gradlew.bat build`)

3. The compiled mod JAR will be in: `build/libs/thanhsumod-1.0.0.jar`

### Installation

1. Install Fabric Loader for Minecraft 1.21.1
2. Download Fabric API 0.116.11+1.21.1 from https://modrinth.com/mod/fabric-api
3. Place both `fabric-api-*.jar` and `thanhsumod-1.0.0.jar` in your `.minecraft/mods` folder
4. Launch Minecraft with Fabric profile

## Usage

1. Get the Vietnam Flag item from Creative inventory (Tools tab) or use:
   ```
   /give @s thanhsumod:vietnam_flag
   ```

2. Hold the flag and right-click for 3 seconds (you'll see the "waving" animation)

3. You'll be teleported to the Independence Dimension where Dinh Doc Lap structure spawns at coordinates (0, 65, 0)

## Development

To run in development:
```bash
./gradlew runClient
```

## Important Notes

- **Missing Texture**: You need to add a 16x16 PNG texture file at:
  `src/main/resources/assets/thanhsumod/textures/item/vietnam_flag.png`
  
  Without this, the item will appear as a purple/black checkerboard in-game.

## Project Structure

```
src/main/java/com/thanhsu/thanhsumod/
├── ThanhSuMod.java              # Main mod initializer
├── item/
│   ├── ModItems.java            # Item registration
│   └── VietnamFlagItem.java     # Custom flag item logic
├── dimension/
│   └── ModDimensions.java       # Dimension registration
├── event/
│   └── ModEvents.java           # Event handlers (block protection, etc.)
└── world/
    └── StructureSpawner.java    # NBT structure spawning

src/main/resources/
├── data/thanhsumod/
│   ├── dimension/               # Dimension definitions
│   ├── dimension_type/          # Dimension type settings
│   └── structures/
│       └── dinh_doc_lap.nbt     # Structure file (5.3MB)
└── assets/thanhsumod/
    ├── models/item/             # Item models
    ├── textures/item/           # Item textures (ADD vietnam_flag.png HERE)
    └── lang/                    # Translations
```

## License

CC0-1.0
