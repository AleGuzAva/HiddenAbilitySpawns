package HiddenAbilitySpawns.util

import net.luckperms.api.LuckPerms
import net.luckperms.api.LuckPermsProvider

/**
 * Provides utility functions for checking permissions using LuckPerms.
 * Primarily used to validate if a player has certain privileges within the EEssentials mod.
 */
class PermissionHelper {
    var luckperms: LuckPerms = LuckPermsProvider.get()
}