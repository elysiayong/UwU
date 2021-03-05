# UwU
"UwU" is a small bullet-hell game, made for code practicing purposes. However, the game can be played without an IDE if you just want to try it out. 
Note: The game has frequent lag spikes due to the lack of an Object Pooling design pattern. As of now, I am not implementing this design pattern due to the nature of how the objects are created. I might revamp the code to make the program more efficient but for now, this will be the most 'stable' version of the game. 

# Game Description
You are a square called "UwU", and there are enemies that want to hurt you. The point of this game is to try to survive until the end of the level by dodging your enemies, managing your health, and upgrading your stats like health or speed.  

# How to run the game
### Option A 
- Download the 'executable' folder and launch the game from 'UwU.exe'; ensure that 'UwU.exe' and 'gamething' are in the same folder or else the game will not launch. 

OR

### Option B 
- Clone the git repository and ensure the following in your IDE of choice: 
    1) Ensure the Java Build Path libraries contains all associated .jar files in */UwU/gamething/res/jars*
    2) Ensure that lwjgl.jar is able to locate and access the Native Library files in */UwU/gamething/res/native/** (please select the folder associated with your operating system). 
- Once the above necessary .jar files are set up, you should be able to execute the main Game class. 

# License
MIT License (MIT)

Copyright © 2020 elysiay

You can find a copy of the licence at https://mit-license.org/
