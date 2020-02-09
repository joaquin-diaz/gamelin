package com.bochelin.chesstd.screens

import com.badlogic.gdx.graphics.OrthographicCamera
import com.bochelin.chesstd.Game
import com.bochelin.chesstd.maps.BaseMap

class MainScreen(game: Game): BaseScreen(game) {
    private val map = BaseMap().apply { create() }

    override fun render(delta: Float) {
        super.render(delta)
        map.render(viewport.camera as OrthographicCamera)
    }
}