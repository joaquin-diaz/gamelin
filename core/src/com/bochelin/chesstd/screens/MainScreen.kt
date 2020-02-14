package com.bochelin.chesstd.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.math.Vector2
import com.bochelin.chesstd.Game
import com.bochelin.chesstd.maps.BaseMap
import com.bochelin.chesstd.system.Debug
import ktx.tiled.layer
import ktx.tiled.tileWidth
import ktx.tiled.width

class MainScreen(game: Game): BaseScreen(game) {
    override fun render(delta: Float) {
        super.render(delta)
        spriteBatch.begin()
        map.render(spriteBatch)
        spriteBatch.end()


        Debug.drawDebugLine(Vector2(0f, -HEIGHT), Vector2(0f, HEIGHT), camera)
        Debug.drawDebugLine(Vector2(-WIDTH, 0f), Vector2(WIDTH, 0f), camera)
    }
}