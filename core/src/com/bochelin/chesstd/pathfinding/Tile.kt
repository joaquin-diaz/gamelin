package com.bochelin.chesstd.pathfinding

import com.badlogic.gdx.math.Vector2

class Tile(val x: Float, val y: Float) {
    var index: Int = 0
    val DIAGONAL_WEIGHT = 1.1f

    fun getCostTo(toTile: Tile): Float {
        val distanceBetweenTiles = Vector2.dst(x, y, toTile.x, toTile.y)

        if (toTile.x + 1 == x && toTile.y + 1 == y) {
            return distanceBetweenTiles * DIAGONAL_WEIGHT
        }

        return distanceBetweenTiles
    }
}