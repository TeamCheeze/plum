## 0.0.9
### Moved
- GEnchant to the enchants package
### Upgraded
- GInventory tree
### Modified
- On GInventory click, it calls GInventoryClickEvent
### Added
- SlotRanges, ColumnRanges and RowRanges
- Added components for simpler inventory usage
## 0.0.8
### BugPatch
- Options for enums and lists
## 0.0.7
### BugPatch
- UnwrappedEventExecutorException occurs on event type mismatch
### Added
- Attributes on GItem
## 0.0.6
***Inventory Updates***
### Added
#### Dev-1
- Upgraded the slots feature of GInventory
- Added the onOpen and onClose functions, and upgraded the event handling features
- Added GInventoryInteractEvent
### Modified
#### Dev-1
- Updated the dependencies to the latest
#### Dev-2
- Fix BukkitSync requiring 'Any' to be returned
#### Dev-3
- Fixed command not synced after loading during runtime
#### Dev-4
- Fixed ItemMeta not applying in GItem
## 0.0.5
***Patches and Updates!***
### Added
#### Dev-1
- Added the command sender in CommandRegistry
#### Dev-2
- Added auto tab completion
#### Dev-3
- Recovered the CommandRegistry class
#### Final
- Cleaned the utilities package
## 0.0.4
***Command Update!***
Almost the same as Brigadier by Mojang, but more human-readable. 
### Added
- Added better commands feature.
- Added documentation for some packages
### Modified
- All classes in the core/utils package have moved to the utils package
- The Module class has moved from modules/core/Module.kt to utils/Module.kt
- The Version.kt class has been deprecated
## 0.0.3
***I finally released it on GitHub***
### Added
- NMS related configuration settings
- Minecraft version regex system
### Modified
- Changed the Module system
- Changed the package name from `io.github.dolphin2410.gemmyLib` to `io.github.teamcheeze.plum` as well as the maven groupId name
### Finished
- I finished migrating all clientity features to another project called PlumJuice

## 0.0.2
### Notes
- Preparing to move NMS modules outside this library, and create a separate module that handles it.
### Added
- The Serializer class os created, and it easily serializes in the pattern `<class_name>;<param_0>;<param_1>;<param_n>`
### Removed
- The nms module has been removed from the project.
### In maintenance [Not recommended to use now]
- The clientity and some classes might not have been removed from the project completely. I'll do my best to remove all of them.
### Pre-Release
- The module that contains the clientity feature will be found in my GitHub repository soon

## 0.0.1
### Notes
- This project has been moved from [dolphin2410](https://github.com/dolphin2410) 's repository to TeamCheeze and renamed from gemmyLib to plum.
- This project has been uploaded to the mavenCentral repository with `io.github.dolphin2410:plum-io.teamcheeze.plum.api:0.0.1`