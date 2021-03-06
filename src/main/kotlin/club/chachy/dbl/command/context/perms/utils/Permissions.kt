package club.chachy.dbl.command.context.perms.utils

/**
 * Taken from JDAs Permission Enum.
 */

enum class Permissions(val offset: Int, val isGuild: Boolean, val isChannel: Boolean, val permissionName: String) {
    CREATE_INSTANT_INVITE(0, true, true, "Create Instant Invite"),
    KICK_MEMBERS(1, true, false, "Kick Members"),
    BAN_MEMBERS(2, true, false, "Ban Members"),
    ADMINISTRATOR(3, true, false, "Administrator"),
    MANAGE_CHANNEL(4, true, true, "Manage Channels"),
    MANAGE_SERVER(5, true, false, "Manage Server"),
    MESSAGE_ADD_REACTION(6, true, true, "Add Reactions"),
    VIEW_AUDIT_LOGS(7, true, false, "View Audit Logs"),
    PRIORITY_SPEAKER(8, true, true, "Priority Speaker"),
    VIEW_GUILD_INSIGHTS(19, true, false, "View Server Insights"),

    // Applicable to all channel types
    VIEW_CHANNEL(10, true, true, "Read Text Channels & See Voice Channels"),

    // Text Permissions
    MESSAGE_READ(10, true, true, "Read Messages"),
    MESSAGE_WRITE(11, true, true, "Send Messages"),
    MESSAGE_TTS(12, true, true, "Send TTS Messages"),
    MESSAGE_MANAGE(13, true, true, "Manage Messages"),
    MESSAGE_EMBED_LINKS(14, true, true, "Embed Links"),
    MESSAGE_ATTACH_FILES(15, true, true, "Attach Files"),
    MESSAGE_HISTORY(16, true, true, "Read History"),
    MESSAGE_MENTION_EVERYONE(17, true, true, "Mention Everyone"),
    MESSAGE_EXT_EMOJI(18, true, true, "Use External Emojis"),

    // Voice Permissions
    VOICE_STREAM(9, true, true, "Stream"),
    VOICE_CONNECT(20, true, true, "Connect"),
    VOICE_SPEAK(21, true, true, "Speak"),
    VOICE_MUTE_OTHERS(22, true, true, "Mute Members"),
    VOICE_DEAF_OTHERS(23, true, true, "Deafen Members"),
    VOICE_MOVE_OTHERS(24, true, true, "Move Members"),
    VOICE_USE_VAD(25, true, true, "Use Voice Activity"),

    NICKNAME_CHANGE(26, true, false, "Change Nickname"),
    NICKNAME_MANAGE(27, true, false, "Manage Nicknames"),

    MANAGE_ROLES(28, true, false, "Manage Roles"),
    MANAGE_PERMISSIONS(28, false, true, "Manage Permissions"),
    MANAGE_WEBHOOKS(29, true, true, "Manage Webhooks"),
    MANAGE_EMOTES(30, true, false, "Manage Emojis"),

    UNKNOWN(-1, false, false, "Unknown");
}

fun getByOffset(offset: Int) = Permissions.values().find { it.offset == offset }
