# ThanhSu Mod - Build and Usage Guide

## System Requirements

- **Java 21** or higher
- **Minecraft 1.21.1**
- **Fabric Loader 0.18.1+**
- **Fabric API 0.116.11+1.21.1**
- **AnimatedFrames 1.6.1** (for Fabric 1.21-1.21.1)
- **WATERMeDIA 2.1.37**

## Building the Mod

### Step 1: Open Terminal/Command Prompt
Open terminal in the `thanhsumod` project directory

### Step 2: Run build command
**On Windows:**
```bash
gradlew.bat build
```

**On Linux/Mac:**
```bash
./gradlew build
```

### Step 3: Get the JAR file
After building, the mod file will be located at:
```
build/libs/thanhsumod-1.0.0.jar
```

## Installing the Mod

### Required Dependencies

1. **Fabric Loader 0.18.1+** - Install from https://fabricmc.net/use/installer/
   - Note: Version 0.18.1 or higher is required for AnimatedFrames compatibility
   
2. **Fabric API 0.116.11+1.21.1** - Download from https://modrinth.com/mod/fabric-api

3. **AnimatedFrames 1.6.1** - Required for memorial video playback
   - Download the version for Fabric 1.21-1.21.1
   - This mod enables fullscreen video playback in-game

4. **WATERMeDIA 2.1.37** - Required dependency for AnimatedFrames
   - Provides video rendering capabilities

### Installation Steps

1. Install **Fabric Loader** for Minecraft 1.21.1 from https://fabricmc.net/use/installer/
2. Download all required mods listed above
3. Copy all JAR files to your `.minecraft/mods` folder:
   - `fabric-api-0.116.11+1.21.1.jar`
   - `animatedframes-1.6.1.jar`
   - `watermedia-2.1.37.jar`
   - `thanhsumod-1.0.0.jar`
4. Launch Minecraft with Fabric profile

## How to Use

### Crafting Items

**Vietnam Star:**
1. Open a crafting table
2. Place 8 **Gold Blocks** around the edges
3. Place 1 **Nether Star** in the center
4. Result: 1 Vietnam Star

**Vietnam Flag:**
1. Craft or obtain a Vietnam Star first
2. Use the custom recipe with Vietnam Star and sticks
3. Check the in-game recipe book for the exact pattern

### Getting Vietnam Flag (Creative Mode)
In Creative mode, open inventory and find it in the **Tools and Utilities** tab, or use command:
```
/give @s thanhsumod:vietnam_flag
```

### Teleporting to Independence Dimension
1. Hold the Vietnam Flag in your hand
2. Hold right-click for **3 seconds** (you'll see the waving animation)
3. You'll be teleported to Independence Dimension at coordinates (240.995, 67.0, 134.008)
4. The Dinh Doc Lap structure spawns at (0, 65, 0)
5. **First visit only**: A memorial video will play fullscreen automatically
6. A welcome message will be broadcast: "[Your Name] - Chào mừng đến Dinh Độc Lập"

### Returning to Overworld
1. While in Independence Dimension, hold the Vietnam Flag
2. Hold right-click for 3 seconds again
3. You'll be teleported back to the Overworld spawn point

### Independence Dimension Features
- Flat world with a single **barrier block** layer
- **No mob spawning** naturally (unless spawned with commands)
- **No weather changes**
- **Always daytime** (time locked at noon)
- **Night Vision effect** automatically applied to all players (no particles, not visible in inventory)
- **Block protection**: Cannot break or place blocks in survival/adventure mode
- **Creative mode exception**: Creative players can break and place blocks freely
- **Memorial video**: Plays once per player on first visit

## Development

To test the mod in development environment:
```bash
./gradlew runClient
```

## Important Notes

### Textures
You need to add 16x16 PNG textures for items at:
```
src/main/resources/assets/thanhsumod/textures/item/vietnam_flag.png
src/main/resources/assets/thanhsumod/textures/item/vietnam_star.png
```

Without these textures, items will display as purple/black checkered squares in-game.

You can:
- Draw textures using an image editor (16x16 pixels)
- Find suitable textures online
- Use Vietnam flag emoji and resize to 16x16

### Memorial Video
The mod includes a memorial video (`memorial.mp4`) that will be automatically copied to the AnimatedFrames media folder on first run:
```
.minecraft/config/animatedframes/media/memorial.mp4
```

The video plays fullscreen when a player visits Independence Dimension for the first time. Player visit data is tracked in:
```
.minecraft/config/thanhsumod/visited_players.json
```

### AnimatedFrames Commands
If you need to manually control video playback, you can use:
```
/medias show @p memorial.mp4 0 fullscreen
```

### Player Data Management
To reset a player's visit status (make the video play again), edit the `visited_players.json` file in the config directory and remove their UUID from the list.

## Project Structure

```
thanhsumod/
├── src/main/java/com/thanhsu/thanhsumod/
│   ├── ThanhSuMod.java              # Main mod initializer
│   ├── item/
│   │   ├── ModItems.java            # Item registration
│   │   ├── VietnamFlagItem.java     # Vietnam Flag logic and teleportation
│   │   └── VietnamStarItem.java     # Vietnam Star item
│   ├── dimension/
│   │   └── ModDimensions.java       # Dimension registration
│   ├── event/
│   │   └── ModEvents.java           # Event handlers (block protection, Night Vision)
│   ├── util/
│   │   └── PlayerDataTracker.java   # Player visit tracking and video management
│   └── world/
│       └── StructureSpawner.java    # NBT structure spawning
│
├── src/main/resources/
│   ├── data/thanhsumod/
│   │   ├── dimension/               # Dimension definitions
│   │   ├── dimension_type/          # Dimension type settings
│   │   ├── structures/
│   │   │   └── dinh_doc_lap.nbt     # Structure file (5.3MB)
│   │   └── recipes/                 # Crafting recipes
│   │       ├── vietnam_star.json    # Vietnam Star recipe
│   │       └── vietnam_flag.json    # Vietnam Flag recipe
│   └── assets/thanhsumod/
│       ├── models/item/             # Item models
│       ├── textures/item/           # Textures (ADD PNG files HERE)
│       ├── lang/                    # Translations
│       └── videos/
│           └── memorial.mp4         # Memorial video
│
├── build.gradle                     # Gradle build configuration
├── gradle.properties                # Versions and properties
└── settings.gradle                  # Project settings
```

## Troubleshooting

### Java Version Error
Ensure you're using Java 21 or higher:
```bash
java -version
```

### Mod Not Loading
- Check that Fabric Loader is installed with correct version (0.18.1+)
- Verify Fabric API is in the mods folder
- Check that AnimatedFrames and WATERMeDIA are installed
- Review log file at `.minecraft/logs/latest.log`

### Items Have No Texture
- Add texture files to `src/main/resources/assets/thanhsumod/textures/item/`
  - `vietnam_flag.png`
  - `vietnam_star.png`
- Rebuild the mod

### Cannot Teleport
- Ensure you hold right-click for the full 3 seconds
- Check console log for errors
- Verify the dimension is properly loaded

### Video Not Playing
- Confirm AnimatedFrames 1.6.1 and WATERMeDIA 2.1.37 are installed
- Check that `memorial.mp4` exists in `.minecraft/config/animatedframes/media/`
- Review the log for AnimatedFrames errors
- Try the command manually: `/medias show @p memorial.mp4 0 fullscreen`

### Video Plays Every Time
- This is expected behavior if the player data file is missing or corrupted
- Check `.minecraft/config/thanhsumod/visited_players.json` exists
- Verify the file contains valid JSON with player UUIDs

### Fabric Loader Version Issues
If you're using TLauncher or another launcher:
1. Download the correct Fabric Loader installer from https://fabricmc.net/use/installer/
2. Run the installer and select Minecraft 1.21.1
3. Choose "Install for all users" or your specific profile
4. Launch Minecraft with the Fabric profile

## License
CC0-1.0
