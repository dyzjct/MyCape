package dev.dyzjct.mycape

import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory


object MyCape : ModInitializer {

    override fun onInitialize() {
    }

    const val MODID = "mycape"

    @JvmField
    val NAME = "MyCape"

    @JvmField
    val VERSION = "1.0"

    @JvmField
    val LOGGER = LoggerFactory.getLogger("mycape")!!

    @JvmStatic
    fun load() {
        LOGGER.info("Initialized MyCape")
    }

    fun getCape(): Identifier {
        return Identifier("mycape", "capes/cape.png")
    }
}