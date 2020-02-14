package com.bochelin.chesstd.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.bochelin.chesstd.Game
import com.bochelin.chesstd.screens.BaseScreen

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        LwjglApplication(Game(), config)

        config.width = BaseScreen.WIDTH.toInt()
        config.height = BaseScreen.HEIGHT.toInt()
    }
}
