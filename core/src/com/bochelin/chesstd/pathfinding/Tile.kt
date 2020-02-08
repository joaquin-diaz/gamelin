package com.bochelin.chesstd.pathfinding

import com.badlogic.gdx.math.Vector2

class Tile(val x: Float, val y: Float, val hasObstacle: Boolean) {
    var index: Int = 0

    fun getCostTo(toTile: Tile): Float {
        val distanceBetweenTiles = Vector2.dst(x, y, toTile.x, toTile.y)

        if (toTile.hasObstacle) {
            return distanceBetweenTiles + 5f
        }

        if (toTile.x + 1 == x && toTile.y + 1 == y) {
            return distanceBetweenTiles + 0.1f
        }

        return distanceBetweenTiles
    }
}