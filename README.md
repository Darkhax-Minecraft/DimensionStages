# Dimension Stages [![](http://cf.way2muchnoise.eu/269398.svg)](https://minecraft.curseforge.com/projects/dimension-stages) [![](http://cf.way2muchnoise.eu/versions/269398.svg)](https://minecraft.curseforge.com/projects/dimension-stages)

This mod hooks in to the GameStage API, and allows travel to dimensions to be restricted based on the stages the player has unlocked. 

[![Nodecraft](https://i.imgur.com/sz9PUmK.png)](https://nodecraft.com/r/darkhax)    
This project is sponsored by Nodecraft. Use code [Darkhax](https://nodecraft.com/r/darkhax) for 30% off your first month of service!

This mod is an addon for the GameStage API. This allows for access to dimensions to be gated behind stages. To learn more about stages, you should check out the GameStage API mod's description. To give a brief rundown, stages are an abstract concept, and players can unlock them. The way players can go about unlocking them is up to the modpack/server. 

To add a dimension restriction, you have to use CraftTweaker. The script method for adding a dimension stage is mods.DimensionStages.addDimensionStage(String stage, int dimensionId);. Replace the stage string with the name of the stage, and replace the integer dimension id with the id of the desired dimension. If a stage does not exist with the name you use, it will be created. There are a few rules for stage names. They must be all lower case, and be one word. You can use special characters like underscores, but exotic characters like emoji are discouraged. 

When a restriction is made for a dimension, players will not be able to travel to that dimension unless they have the stage. This mod will also check player-ownable entities, to see if the owner has access to the correct stage and prevent them from traveling to the dimension if they do not. This includes dropped items, arrows, snowballs, ender pearls, and other throwable entities. 