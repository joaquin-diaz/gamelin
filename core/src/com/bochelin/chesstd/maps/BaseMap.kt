package com.bochelin.chesstd.maps

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer
import com.bochelin.chesstd.pathfinding.Pathfinder
import ktx.tiled.height
import ktx.tiled.tileHeight
import ktx.tiled.tileWidth
import ktx.tiled.width

class BaseMap {
    var tiles = arrayOf<Array<Tile>>()
    val mapSize = 16
    val ratio = 0.66

    init {
        // Magic number
        val adjustedProximityWidth = 0.30
        val adjustedProximityHeight = adjustedProximityWidth / ratio
        val initialY = - (Tile.height * ratio) * mapSize / 2

        for (x in mapSize downTo 1) {
            var row = arrayOf<Tile>()

            for (y in mapSize downTo 1) {
                val tileY = (y * Tile.width * adjustedProximityWidth) + (x * Tile.width * adjustedProximityWidth) - Tile.width / 2
                val tileX = (x * Tile.height * adjustedProximityHeight) - (y * Tile.height * adjustedProximityHeight) - Tile.height / 2
                row += Tile(tileX.toInt(), tileY.toInt() + initialY.toInt())
            }

            tiles += row
        }
    }

    fun render(batch: SpriteBatch) {
        for (row in tiles) {
            for (tile in row) {
                tile.render(batch)
            }
        }
    }
}