# Dimension Stages [![](http://cf.way2muchnoise.eu/269398.svg)](https://www.curseforge.com/minecraft/mc-mods/dimension-stages) [![](http://cf.way2muchnoise.eu/versions/269398.svg)](https://www.curseforge.com/minecraft/mc-mods/dimension-stages)

This mod hooks in to the [GameStage API](https://www.curseforge.com/minecraft/mc-mods/game-stages), and allows access to dimensions to be restricted based on the stages a player has unlocked.

## Documentation

You are intended to use [CraftTweaker](https://www.curseforge.com/minecraft/mc-mods/crafttweaker) to configure this mod. An example script file can be found [here](https://github.com/Darkhax-Minecraft/DimensionStages/blob/1.16.5/src/main/resources/data/dimstages/scripts/dimstages/example.zs).

By default all dimensions are unstaged meaning they will function like normal until you stage them using a script. To stage a dimension you will need the ID of the dimension and the stages you want to require. You can specify any number of stages.

```zs
// Requires stage "one" and "two" to enter the nether.
// DimensionStages.stageDimension(dimensionId, stages);
DimensionStages.stageDimension("minecraft:the_nether", "one", "two");
```

Additionally you can specify a [Message](https://docs.blamejared.com/1.16/en/vanilla/api/util/text/TextFormatting/) that will be displayed to players when they are prevented from entering a dimension.

```zs
// Requires stage "one" and "two" to enter the end.
// DimensionStages.stageDimensionWithMessage(dimensionId, message, stages);
DimensionStages.stageDimensionWithMessage("minecraft:the_end", "Access Denied!", "one", "two");
```

## Maven Dependency
If you are using [Gradle](https://gradle.org) to manage your dependencies, add the following into your `build.gradle` file. Make sure to replace the version with the correct one. All versions can be viewed [here](https://maven.blamejared.com/net/darkhax/dimstages/).
```
repositories {

    maven { url 'https://maven.blamejared.com' }
}

dependencies {

    // Example: compile "net.darkhax.dimstages:DimensionStages-1.16.5:3.0.1"
    compile "net.darkhax.dimstages:DimensionStages-MCVERSION:PUT_VERSION_HERE"
}
```

## Jar Signing

As of January 11th 2021 officially published builds will be signed. You can validate the integrity of these builds by comparing their signatures with the public fingerprints.

| Hash   | Fingerprint                                                        |
|--------|--------------------------------------------------------------------|
| MD5    | `12F89108EF8DCC223D6723275E87208F`                                 |
| SHA1   | `46D93AD2DC8ADED38A606D3C36A80CB33EFA69D1`                         |
| SHA256 | `EBC4B1678BF90CDBDC4F01B18E6164394C10850BA6C4C748F0FA95F2CB083AE5` |

## Sponsors
<img src="https://nodecraft.com/assets/images/logo-dark.png" width="384" height="90">

This project is sponsored by Nodecraft. Use code [Darkhax](https://nodecraft.com/r/darkhax) for 30% off!