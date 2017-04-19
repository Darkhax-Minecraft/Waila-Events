Waila Events
=====
Waila Events is a core mod that adds several new events into the Waila mod. These events can be accessed in the same way as any other event on the MincecraftForge.EVENT_BUS

[![Nodecraft](https://i.imgur.com/sz9PUmK.png)](https://nodecraft.com/r/darkhax)    
This project is sponsored by Nodecraft. Use code [Darkhax](https://nodecraft.com/r/darkhax) for 30% off your first month of service!

Required Mods
=============
[WAILA](https://bitbucket.org/ProfMobius/waila) | Currently coded with 1.5.10

Compilation
===========
It is very simple to build versions of Waila Events. Waila Events makes use of the Forge Gradle wrapper, which is included in this project. To compile the mod, simply download a copy of the repository and run the 'gradlew build' command from the command line, within the project directory. A compiled jar file will be generated in the build/libs folder.

Dev Environment
===============
This project is a Core Mod, and will only function if it is set up as one. The easiest way to use this mod is to download the dev jar from [CurseForge](http://minecraft.curseforge.com/mc-mods/235140-waila-events/files) and place it in your mods folder. All versions will have an attatched dev jar file that can be found under the '+1 More' button. Alternatively, if you want to use the raw source for this mod, you will need to create a dummy jar for this mod, or add the following argument to your VM arguments.    

-Dfml.coreMods.load=net.darkhax.wailaevents.asm.WailaEventsLoadingPlugin      

Events
======
WailaRenderingEvent.Pre - This event is triggered before the Waila HUD is rendered. It can be cancelled to prevent it from rendering. 

WailaRenderingEvent.Post - This event is triggered after the Waila HUD is rendered. It can not be cancelled.

Credits
=======
[ProfMobius](https://github.com/ProfMobius) - The developer of [WAILA](http://www.minecraftforum.net/topic/1846244-)

[VikeStep](https://github.com/VikeStep) - Helped out with some of the ASM code included within the mod.

[Darkhax](https://github.com/darkhax) - Developer on the project
