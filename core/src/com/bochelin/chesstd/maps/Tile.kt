package com.bochelin.chesstd.maps

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

class Tile(val x: Float, val y: Float) {
    companion object {
        val width = 64
        val height = 64
    }

    var index: Int = 0
    val diagonalWeight = 1.1f
    val texture = Texture("sprites/tiles/grass.png")
    var sprite = Sprite(texture, 0 , 0, width, height)
    val position = Vector2(x, y)

    init {
        sprite.setPosition(x, y)
    }

    fun render(batch: SpriteBatch) {
        sprite.draw(batch)
    }

    fun getCostTo(toTile: Tile): Float {
        val distanceBetweenTiles = Vector2.dst(x, y, toTile.x, toTile.y)

        if (toTile.x + 1 == x && toTile.y + 1 == y) {
            return distanceBetweenTiles * diagonalWeight
        }

        return distanceBetweenTiles
    }

    fun getDistanceTo(toTile: Tile): Float {
        return toTile.position.dst(this.position)
    }

    fun getDirectionTo(toTile: Tile, distanceToTile: Float): Vector2 {
        return Vector2(
                (toTile.position.x - this.position.x) / distanceToTile,
                (toTile.position.y - this.position.y) / distanceToTile
        )
    }
}