package ru.nosqd.nupgame.arenas

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import java.util.UUID

class ArenaList {
    private var arenas: ArrayList<Arena> = ArrayList<Arena>()
    private val arenasCap = 32

    fun getArenas(): List<Arena> {
        return arenas
    }

    fun getFreeArenas(): List<Arena> {
        return arenas.filter { it.state == ArenaState.FREE }
    }

    fun pushArena(arena: Arena) {
        arenas.add(arena)
    }

    fun getFreeArena(): Arena? {
        val ar: Arena? = arenas.find { it.state == ArenaState.FREE }
        return ar
    }

    fun getFreeOrCreate(): Arena {
        val free = getFreeArena()
        if (free != null) {
            return free
        }
        return createArena()
    }

    fun createArena(): Arena {
        if (arenas.size < arenasCap) {
            val arena = ArenaFactory.createArena()
            pushArena(arena)
            return arena
        }
        else {
            throw Error("Failed, tried to allocate more than $arenasCap areans, fix matchmaking system pls, nyancat.")
        }
    }

    fun safeRemoveArena(id: UUID): Arena? {
        val arena = arenas.find { it.index == id }

        if (arena == null) return null

        if (arena.index == id && arena.state == ArenaState.FREE) {
            return removeArena(arena)
        }
        else if (arena.index == id && arena.state == ArenaState.INGAME) {
            throw Error("Tried to safe remove arena $id, but it is ingame")
        }
        return null
    }

    fun forceRemoveArena(id: UUID): Arena? {
        val arena = arenas.find { it.index == id }
        if (arena != null) {
            arena.world.players.forEach {it.kick(Component.text("Arena where you was forced removed."))}
            return removeArena(arena)
        }
        return null
    }

    private fun removeArena(arena: Arena): Arena {
        arenas.removeIf { it.index == arena.index }
        Bukkit.unloadWorld(arena.world, true)
        val worldDirectory = Bukkit.getWorldContainer().listFiles()?.filter { it.isDirectory }?.find {it.name == arena.world.name}
        worldDirectory!!.deleteRecursively()
        return arena
    }

}