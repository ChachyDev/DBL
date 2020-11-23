package club.chachy.dbl.config

import com.google.gson.Gson
import java.io.File

object Config {
    val gson = Gson()

    inline operator fun <reified T> get(path: String): T = gson.fromJson(File(path).readText(), T::class.java)
}