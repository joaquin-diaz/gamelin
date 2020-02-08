package com.bochelin.chesstd

import com.bochelin.chesstd.screens.MainScreen

import ktx.app.KtxGame
import ktx.app.KtxScreen

class Game : KtxGame<KtxScreen>() {
    override fun create() {
        addScreen(MainScreen(this))
        setScreen<MainScreen>()
        super.create()
    }

    override fun dispose() {
        super.dispose()
    }
}
