package ru.nosqd.nupgame.commands

import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ru.nosqd.nupgame.NupGameState
import ru.nosqd.nupgame.arenas.Arena

class NupCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val p: Player = sender

            if (args.isEmpty()) {
                p.sendMessage(Component.text("Usage: /nup <tp|clear-arenas>"))
                return false
            }
            else if (args[0] == "clear-arenas") {
                NupGameState.arenas.getArenas().forEach { NupGameState.arenas.forceRemoveArena(it.index) }
            }
            else if (args[0] == "tp") {
                if (args.size == 1) {
                    p.sendMessage(Component.text("Usage: /nup tp <alloc|free>"))
                    return false
                }
                if (args[1] == "alloc" || args[1] == "free") {
                    var arena: Arena?
                    if (args[1] == "alloc") {
                        arena = NupGameState.arenas.createArena()
                    }
                    else {
                        val ar = NupGameState.arenas.getFreeArena()
                        if (ar == null) {
                            p.sendMessage(Component.text("No free arenas use /nup tp free"))
                            return false
                        }
                        arena = ar
                    }
                    p.teleport(arena.getSpawnLocation())
                }
                else {
                    p.sendMessage(Component.text("Usage: /nup tp <alloc|free>"))
                }
            }
            return true
        }
        return false
    }
}