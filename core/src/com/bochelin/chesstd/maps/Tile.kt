package com.bochelin.chesstd.maps

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Tile(x: Int, y: Int) {
    companion object {
        val width = 64
        val height = 64
    }

    val texture = Texture("sprites/tiles/grass.png")
    var sprite = Sprite(texture, 0 , 0, width, height).apply {
        this.setPosition(x.toFloat(), y.toFloat())
    }

    fun render(batch: SpriteBatch) {
        sprite.draw(batch)
    }
}