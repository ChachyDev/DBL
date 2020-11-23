package club.chachy.dbl.command.context.perms

import club.chachy.dbl.engine.entities.guild.Member

class Permissions {
    var canRun = true

    operator fun get(member: Member) = member.permissions
}