package ru.nosqd.nupgame.arenas

import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.World
import org.bukkit.WorldCreator
import ru.nosqd.nupgame.NupGameState
import java.util.*

object ArenaFactory {
    fun createArena(): Arena {
        val id: UUID = UUID.randomUUID();
        val creator = WorldCreator(NamespacedKey(NupGameState.plugin!!, "arena-$id"))
        creator.seed(42)
        creator.generator(ArenaChunkGenerator())
        val world = Bukkit.createWorld(creator) ?: throw Error("failed to create bukkit world")
        val arena = Arena(id, world, ArenaState.FREE)
        return arena
    }
}