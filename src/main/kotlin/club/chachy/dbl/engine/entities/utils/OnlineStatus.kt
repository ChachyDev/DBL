package club.chachy.dbl.engine.entities.utils

enum class OnlineStatus(private val key: String) {
    ONLINE("online"),
    IDLE("idle"),
    DO_NOT_DISTURB("dnd"),
    INVISIBLE("invisible"),
    OFFLINE("offline"),
    UNKNOWN("unknown");

    companion object {
        operator fun get(key: String) = values().find { it.key == key }
    }
}