### Usage

```kotlin
PCommand.register("plum") {
    option("heal") {
        input { playerName ->
            Bukkit.getPlayer(playerName)?.let { 
                it.health = 20.0
            }
        }
    }
    option("feed") {
        input { playerName ->
            Bukkit.getPlayer(playerName)?.let {
                it.foodLevel = 20
            }
        }
    }
}
```
The code above register

`/plum heal <playerName>` 
and `/plum feed <playerName>`

### The option block
As its name, the option block creates options in the command argument

### The input block
The input block fetches the string from the position.

### TODO
I will create a picture example for this