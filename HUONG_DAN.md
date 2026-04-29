# Hướng Dẫn Build và Sử Dụng ThanhSu Mod

## Yêu Cầu Hệ Thống

- **Java 21** hoặc cao hơn
- **Minecraft 1.21.1**
- **Fabric Loader 0.19.2+**
- **Fabric API 0.116.11+1.21.1**

## Cách Build Mod

### Bước 1: Mở Terminal/Command Prompt
Mở terminal trong thư mục project `thanhsumod`

### Bước 2: Chạy lệnh build
**Trên Windows:**
```bash
gradlew.bat build
```

**Trên Linux/Mac:**
```bash
./gradlew build
```

### Bước 3: Lấy file JAR
Sau khi build xong, file mod sẽ nằm ở:
```
build/libs/thanhsumod-1.0.0.jar
```

## Cài Đặt Mod

1. Cài đặt **Fabric Loader** cho Minecraft 1.21.1 từ https://fabricmc.net/use/installer/
2. Tải **Fabric API** từ https://modrinth.com/mod/fabric-api (phiên bản 0.116.11+1.21.1)
3. Copy cả 2 file vào thư mục mods:
   - `fabric-api-0.116.11+1.21.1.jar`
   - `thanhsumod-1.0.0.jar`
4. Khởi động Minecraft với profile Fabric

## Cách Sử Dụng

### Lấy Vietnam Flag
Trong Creative mode, mở inventory và tìm trong tab **Tools and Utilities**, hoặc dùng lệnh:
```
/give @s thanhsumod:vietnam_flag
```

### Teleport đến Independence Dimension
1. Cầm Vietnam Flag trong tay
2. Giữ chuột phải trong **3 giây** (bạn sẽ thấy animation vẫy cờ)
3. Bạn sẽ được teleport đến Independence Dimension tại tọa độ (0, 65, 0)
4. Công trình Dinh Độc Lập sẽ spawn tại đó

### Đặc Điểm Independence Dimension
- Flat world với 1 layer duy nhất là **barrier block**
- **Không có mob** spawn tự nhiên (trừ khi dùng lệnh)
- **Không thay đổi thời tiết**
- **Luôn là ban ngày** (thời gian bị khóa)
- **Không thể phá hoặc đặt block** (được bảo vệ)

## Chạy Thử Trong Development

Để test mod trong môi trường development:
```bash
./gradlew runClient
```

## LƯU Ý QUAN TRỌNG

### Thiếu Texture
Bạn cần thêm texture 16x16 PNG cho Vietnam Flag tại:
```
src/main/resources/assets/thanhsumod/textures/item/vietnam_flag.png
```

Nếu không có texture này, item sẽ hiển thị dạng ô vuông tím/đen trong game.

Bạn có thể:
- Tự vẽ texture bằng image editor (16x16 pixels)
- Tìm texture phù hợp online
- Dùng emoji cờ Việt Nam và resize về 16x16

## Cấu Trúc Project

```
thanhsumod/
├── src/main/java/com/thanhsu/thanhsumod/
│   ├── ThanhSuMod.java              # Main mod initializer
│   ├── item/
│   │   ├── ModItems.java            # Đăng ký items
│   │   └── VietnamFlagItem.java     # Logic của Vietnam Flag
│   ├── dimension/
│   │   └── ModDimensions.java       # Đăng ký dimension
│   ├── event/
│   │   └── ModEvents.java           # Xử lý events (bảo vệ block, etc.)
│   └── world/
│       └── StructureSpawner.java    # Spawn structure từ NBT
│
├── src/main/resources/
│   ├── data/thanhsumod/
│   │   ├── dimension/               # Định nghĩa dimension
│   │   ├── dimension_type/          # Cài đặt dimension type
│   │   └── structures/
│   │       └── dinh_doc_lap.nbt     # File structure (5.3MB)
│   └── assets/thanhsumod/
│       ├── models/item/             # Models cho items
│       ├── textures/item/           # Textures (THÊM vietnam_flag.png Ở ĐÂY)
│       └── lang/                    # Translations
│
├── build.gradle                     # Gradle build config
├── gradle.properties                # Versions và properties
└── settings.gradle                  # Project settings
```

## Troubleshooting

### Lỗi "Java version"
Đảm bảo bạn đang dùng Java 21 trở lên:
```bash
java -version
```

### Mod không load
- Kiểm tra Fabric Loader đã cài đúng version
- Kiểm tra Fabric API đã có trong thư mục mods
- Xem log file trong `.minecraft/logs/latest.log`

### Item không có texture
- Thêm file `vietnam_flag.png` vào `src/main/resources/assets/thanhsumod/textures/item/`
- Build lại mod

### Không teleport được
- Đảm bảo giữ chuột phải đủ 3 giây
- Kiểm tra console log xem có lỗi gì không

## License
CC0-1.0
