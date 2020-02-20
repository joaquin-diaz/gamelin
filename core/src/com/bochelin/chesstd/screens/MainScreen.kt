package com.bochelin.chesstd.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.math.Vector2
import com.bochelin.chesstd.Game
import com.bochelin.chesstd.entities.Enemy
import com.bochelin.chesstd.maps.BaseMap
import com.bochelin.chesstd.system.Debug
import ktx.tiled.layer
import ktx.tiled.tileWidth
import ktx.tiled.width
import kotlin.random.Random

class MainScreen(game: Game): BaseScreen(game) {
    var enemies = arrayOf<Enemy>()

    init {
        val finalTile = map.getTileAt(7, 7)

        for (x in 1..Random.nextInt(1, 10)) {
            val startTile = map.getTileAt(0, Random.nextInt(0, 15))
            val path = map.pathfinder.findPath(startTile, finalTile)
            val enemy = Enemy(startTile.x, startTile.y)
            enemy.path = path

            enemies += enemy
        }

        for (x in 1..Random.nextInt(1, 10)) {
            val startTile = map.getTileAt(Random.nextInt(0, 15), 0)
            val path = map.pathfinder.findPath(startTile, finalTile)
            val enemy = Enemy(startTile.x, startTile.y)
            enemy.path = path

            enemies += enemy
        }
    }

    override fun render(delta: Float) {
        super.render(delta)

        spriteBatch.begin()
        map.render(spriteBatch)
        for (enemy in enemies) {
            enemy.render(spriteBatch)
        }
        spriteBatch.end()


        Debug.drawDebugLine(Vector2(0f, -HEIGHT), Vector2(0f, HEIGHT), camera)
        Debug.drawDebugLine(Vector2(-WIDTH, 0f), Vector2(WIDTH, 0f), camera)
    }
}