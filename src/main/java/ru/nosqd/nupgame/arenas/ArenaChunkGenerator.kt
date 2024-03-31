package ru.nosqd.nupgame.arenas

import org.bukkit.Material
import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import java.util.*

class ArenaChunkGenerator : ChunkGenerator() {
    override fun shouldGenerateCaves(): Boolean {
        return false
    }

    override fun shouldGenerateDecorations(): Boolean {
        return false
    }

    override fun shouldGenerateMobs(): Boolean {
        return false
    }

    override fun shouldGenerateNoise(): Boolean {
        return false
    }

    override fun shouldGenerateStructures(): Boolean {
        return false
    }

    override fun shouldGenerateSurface(): Boolean {
        return true
    }

    override fun generateSurface(worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int, chunkData: ChunkData) {
        if (chunkX == 0 && chunkZ == 0) {
            for (x in 0..16) {
                for (z in 0..16) {
                    chunkData.setBlock(x, 69, z, Material.STONE);
                }
            }
        }
    }
}