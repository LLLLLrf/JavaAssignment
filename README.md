# README
## JavaAssignment
***
## How to begin
make sure you run the command in **assigment** directory
```./Report/assignment```

use  ```mvn clean test``` to run the test
use ```mvn compile exec:java -D"exec.mainClass"="abdn.scnu.cs.RunGame" -D"exec.args"="arg1 arg2 arg3"``` to run the main function (arg1: height of game grid; arg2: width of game grid; arg3: number of ships)
A version that do not need arguments was provided too, you can check that in **RunGame.java**

the separator between numbers can be **"." "/" "," " "**
The following are some examples
```1,2      1 2      1/2      1.2```

enter **exit** to quit the game whenever you want
enter **check** if you want to check how many ships are still alive and how many hits they have got

## File Structure

```
│  .codio
│  files_structure.txt
|  JavaReport.pdf
│  
└─assignment
    │  pom.xml
    │  
    ├─src
    │  ├─main
    │  │  └─java
    │  │      └─abdn
    │  │          └─scnu
    │  │              └─cs
    │  │                      AbstractBattleShip.java
    │  │                      AbstractGameGrid.java
    │  │                      BattleShip.java
    │  │                      Game.java
    │  │                      GameControls.java
    │  │                      GameGrid.java
    │  │                      OpponentGameGrid.java
    │  │                      PlayerGameGrid.java
    │  │                      RunGame.java
    │  │                      
    │  └─test
    │      └─java
    │          └─abdn
    │              └─scnu
    │                  └─cs
    │                          GameTests.java
    │                          
    └─target
        ├─classes
        │  └─abdn
        │      └─scnu
        │          └─cs
        │                  AbstractBattleShip.class
        │                  AbstractGameGrid.class
        │                  BattleShip.class
        │                  Game.class
        │                  GameControls.class
        │                  GameGrid.class
        │                  OpponentGameGrid.class
        │                  PlayerGameGrid.class
        │                  RunGame.class
        │                  
        ├─generated-sources
        │  └─annotations
        ├─generated-test-sources
        │  └─test-annotations
        ├─maven-status
        │  └─maven-compiler-plugin
        │      ├─compile
        │      │  └─default-compile
        │      │          createdFiles.lst
        │      │          inputFiles.lst
        │      │          
        │      └─testCompile
        │          └─default-testCompile
        │                  createdFiles.lst
        │                  inputFiles.lst
        │                  
        ├─surefire-reports
        │      abdn.scnu.cs.GameTests.txt
        │      TEST-abdn.scnu.cs.GameTests.xml
        │      
        └─test-classes
            └─abdn
                └─scnu
                    └─cs
                            GameTests.class
```