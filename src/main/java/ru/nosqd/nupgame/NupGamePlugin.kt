package ru.nosqd.nupgame

import org.bukkit.Bukkit
import org.bukkit.WorldCreator
import org.bukkit.generator.ChunkGenerator
import org.bukkit.plugin.java.JavaPlugin
import ru.nosqd.nupgame.arenas.ArenaChunkGenerator
import ru.nosqd.nupgame.commands.NupCommand

class NupGamePlugin : JavaPlugin() {

    override fun onEnable() {
        super.onEnable()
        NupGameState.plugin = this
        getCommand("nup")!!.setExecutor(NupCommand())
    }

    override fun onDisable() {
        super.onDisable()
    }

    override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator {
        return ArenaChunkGenerator()
    }
}