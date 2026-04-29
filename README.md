# ThanhSu Mod - Vietnam Flag & Independence Dimension

A Fabric mod for Minecraft 1.21.1 that adds:
- **Vietnam Flag item**: Hold right-click for 3 seconds to teleport to Dinh Doc Lap structure

## Building the Mod

### Prerequisites
- Java 21 or higher
- Minecraft 1.21.1
- Fabric Loader 0.18.1+

### Build Instructions

1. Open terminal in the project directory
2. Run the build command:
   ```bash
   ./gradlew build
   ```
   (On Windows: `gradlew.bat build`)

3. The compiled mod JAR will be in: `build/libs/thanhsumod-1.0.0.jar`

### Installation

#### Required Mods

1. **Fabric Loader 0.18.1+** - Install from https://fabricmc.net/use/installer/
2. **Fabric API 0.116.11+1.21.1** - Download from https://modrinth.com/mod/fabric-api
3. **AnimatedFrames 1.6.1** (for Fabric 1.21-1.21.1) - Required for memorial video playback
4. **WATERMeDIA 2.1.37** - Required dependency for AnimatedFrames

#### Installation Steps

1. Install Fabric Loader for Minecraft 1.21.1
2. Install thanhsuMod at [Releases](../../releases) page
3. Download all required mods listed above
4. Place all JAR files in your `.minecraft/mods` folder:
   - `fabric-api-0.116.11+1.21.1.jar`
   - `animatedframes-1.6.1.jar`
   - `watermedia-2.1.37.jar`
   - `thanhsumod-1.0.0.jar`
5. Launch Minecraft with Fabric profile

## Usage

### Crafting

**Vietnam Star:**
- 8 Gold Blocks + 1 Nether Star (center) in a crafting table
<img width="712" height="339" alt="image" src="https://github.com/user-attachments/assets/8f41076a-2e08-43c0-ad5b-fc44b23eb3f6" />

**Vietnam Flag:**
- Custom recipe using Vietnam Star and wools
<img width="711" height="340" alt="image" src="https://github.com/user-attachments/assets/6c54d8b4-53d7-4e6a-985f-eb1da88ecb2e" />
  
**Vietnam Flag:**
- Custom recipe using Flag and sticks (see in-game recipe book)
<img width="711" height="344" alt="image" src="https://github.com/user-attachments/assets/a2e8e19d-32d1-4563-ae62-cc87c288ddbc" />

### Teleportation

1. Get the Vietnam Flag item from Creative inventory (Tools tab) or craft it in Survival
   - Command: `/give @s thanhsumod:vietnam_flag`

2. Hold the flag and right-click for 3 seconds (you'll see the "waving" animation)

3. You'll be teleported to the Independence Dimension at coordinates (240.995, 67.0, 134.008)
   - The Dinh Doc Lap structure spawns at Independence Dimension
   - On your first visit, a memorial video will play fullscreen
   - A welcome message will be broadcast: "[Player] - Chào mừng đến Dinh Độc Lập"

4. Use the flag again while in the Independence Dimension to return to the Overworld spawn point

## Development

To run in development:
```bash
./gradlew runClient
```

## Features

### Independence Dimension
- Flat world with a single barrier block layer
- Dinh Doc Lap structure (5.3MB NBT file)
- No natural mob spawning
- No weather changes
- Locked to daytime (noon)
- Night Vision effect applied to all players (ambient, no particles)
- Block breaking/placing disabled for survival and adventure modes
- Creative mode players can modify blocks freely

### Memorial Video
- Plays automatically on first visit to Independence Dimension
- Stored in `config/animatedframes/media/memorial.mp4`
- Automatically copied from mod resources on initialization
- Player visit tracking persisted in `config/thanhsumod/visited_players.json`

### Player Data Tracking
- Tracks which players have visited the dimension
- Data stored in JSON format in config directory
- Persists across server restarts
- Can be manually edited if needed

## Important Notes

- **Texture Files**: You need to add 16x16 PNG texture files at:
  - `src/main/resources/assets/thanhsumod/textures/item/vietnam_flag.png`
  - `src/main/resources/assets/thanhsumod/textures/item/vietnam_star.png`
  
  Without these, items will appear as purple/black checkerboards in-game.

- **Memorial Video**: The mod includes a memorial video that will be automatically copied to the AnimatedFrames media folder on first run. Ensure AnimatedFrames and WATERMeDIA are installed for this feature to work.

## Project Structure

```
src/main/java/com/thanhsu/thanhsumod/
├── ThanhSuMod.java              # Main mod initializer
├── item/
│   ├── ModItems.java            # Item registration
│   ├── VietnamFlagItem.java     # Custom flag item logic
│   └── VietnamStarItem.java     # Vietnam Star item
├── dimension/
│   └── ModDimensions.java       # Dimension registration
├── event/
│   └── ModEvents.java           # Event handlers (block protection, Night Vision)
├── util/
│   └── PlayerDataTracker.java   # Player visit tracking and video management
└── world/
    └── StructureSpawner.java    # NBT structure spawning

src/main/resources/
├── data/thanhsumod/
│   ├── dimension/               # Dimension definitions
│   ├── dimension_type/          # Dimension type settings
│   ├── structures/
│   │   └── dinh_doc_lap.nbt     # Structure file (5.3MB)
│   └── recipes/                 # Crafting recipes
│       ├── vietnam_star.json    # Vietnam Star recipe
│       └── vietnam_flag.json    # Vietnam Flag recipe
├── assets/thanhsumod/
│   ├── models/item/             # Item models
│   ├── textures/item/           # Item textures (ADD PNG files HERE)
│   ├── lang/                    # Translations
│   └── videos/
│       └── memorial.mp4         # Memorial video (copied to AnimatedFrames)
└── fabric.mod.json              # Mod metadata
```

## License

CC0-1.0
