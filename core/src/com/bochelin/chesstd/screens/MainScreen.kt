package com.bochelin.chesstd.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.utils.Array
import com.bochelin.chesstd.Game
import com.bochelin.chesstd.maps.BaseMap
import com.bochelin.chesstd.pathfinding.Tile
import com.bochelin.chesstd.pathfinding.TileGraph
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.random.Random

class MainScreen(game: Game): BaseScreen(game) {
    private val map = BaseMap().apply { create() }

//    init {
//        // Path finding test
//        val tileGraph = TileGraph()
//        val size = 17
//        val tilesMatrix = Array<Array<Tile>>()
//        val center = floor(size / 2f)
//
//        // Create tiles and assign weight to them
//        for (y in 0 until size) {
//            val row = Array<Tile>()
//
//            for (x in 0 until size) {
//                val hasObstacule = Math.random() > 0.8
//                val tile = Tile(x.toFloat(), y.toFloat(), hasObstacule && x.toFloat() != center && y.toFloat() != center)
//                row.add(tile)
//                tileGraph.addTile(tile)
//            }
//
//            tilesMatrix.add(row)
//        }
//
//        // Create connections
//        for (y in 0 until size) {
//            val topCornerY = y - 1
//            for (x in 0 until size) {
//                val fromTile = tilesMatrix[y][x]
//                val topCornerX = x - 1
//                for (yy in 0..2) {
//                    val connectionTileY = topCornerY + yy
//                    for (xx in 0..2) {
//                        val connectionTileX = topCornerX + xx
//                        if (connectionTileX >= 0 && connectionTileY >= 0 &&
//                            connectionTileX < size && connectionTileY < size &&
//                            (connectionTileX != y || connectionTileY != y)){
//                            tileGraph.connectTiles(fromTile, tilesMatrix[connectionTileY][connectionTileX])
//                        }
//                    }
//                }
//            }
//        }
//    }

    override fun show() {
        super.show()
        Gdx.input.inputProcessor = GestureDetector(this)
    }

    override fun render(delta: Float) {
        super.render(delta)
        map.render(viewport.camera as OrthographicCamera)
    }
}