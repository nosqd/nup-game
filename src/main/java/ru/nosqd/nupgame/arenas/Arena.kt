package ru.nosqd.nupgame.arenas

import org.bukkit.Location
import org.bukkit.World
import java.util.*

data class Arena(val index: UUID, val world: World, var state: ArenaState = ArenaState.FREE) {
    fun getSpawnLocation(): Location {
        return Location(this.world, .0, 70.0, .0)
    }
}
