package com.bochelin.chesstd.entities

import com.badlogic.gdx.ai.pfa.DefaultGraphPath
import com.badlogic.gdx.ai.pfa.GraphPath
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector
import com.badlogic.gdx.math.Vector2
import com.bochelin.chesstd.maps.Tile
import kotlin.properties.Delegates

class Enemy(x: Float, y: Float) {
    // Rendering
    val texture = Texture("sprites/actors/zim.png")
    var sprite = Sprite(texture, 0 , 0, texture.width, texture.height).apply {
        this.setPosition(x, y)
    }

    // Movement
    val velocity = 1f
    var currentTileIndex: Int = 0
    var distanceToNextTile = 0.0f
    var shouldMove = false
    lateinit var directionToNextTile: Vector2
    var path: GraphPath<Tile> by Delegates.observable( DefaultGraphPath()) { property, oldValue, newValue ->
        this.shouldMove = true
        setNextTile()
    }

    fun render(batch: SpriteBatch) {
        sprite.draw(batch)
        if (path.count > 0 && this.shouldMove) move()
    }

    fun move() {
        val currentTile = path.get(currentTileIndex)
        sprite.x += directionToNextTile.x * velocity
        sprite.y += directionToNextTile.y * velocity

        if (Vector2(currentTile.position.x, currentTile.position.y).dst(sprite.x, sprite.y) >= distanceToNextTile) {
            if (currentTileIndex + 2 < path.count) {
                currentTileIndex++
                setNextTile()
            } else {
                this.shouldMove = false
            }
        }
    }

    fun setNextTile() {
        val currentTile = path.get(currentTileIndex)
        val nextTile = path.get(currentTileIndex + 1)

        distanceToNextTile = currentTile.getDistanceTo(nextTile)
        directionToNextTile = currentTile.getDirectionTo(nextTile, distanceToNextTile)
    }
}