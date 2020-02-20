package com.bochelin.chesstd.maps

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.bochelin.chesstd.pathfinding.Pathfinder

class BaseMap {
    var tiles = arrayOf<Array<Tile>>()
    val mapSize = 16
    val ratio = 0.66
    var pathfinder: Pathfinder

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
                row += Tile(tileX.toFloat(), tileY.toFloat() + initialY.toFloat())
            }

            tiles += row
        }

        pathfinder = Pathfinder(mapSize, tiles)
    }

    fun render(batch: SpriteBatch) {
        for (row in tiles) {
            for (tile in row) {
                tile.render(batch)
            }
        }
    }

    fun getTileAt(row: Int, col: Int): Tile {
        return tiles[row][col]
    }

    fun getTilePosition(row: Int, col: Int): Vector2 {
        val tile = tiles[row][col]
        return Vector2(tile.x, tile.y)
    }
}