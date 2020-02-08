package com.bochelin.chesstd.pathfinding

import com.badlogic.gdx.utils.Array
import kotlin.random.Random

class Pathfinder(val mapSize: Int) {
    private val mapCenter = mapSize / 2f
    private val tileGraph = TileGraph()
    private val tilesMatrix = Array<Array<Tile>>()

    init {
        // Create tiles
        for (y in 0 until mapSize) {
            val row = Array<Tile>()

            for (x in 0 until mapSize) {
                // Random obstacle for testing purposes
                val hasObstacle = Math.random() > 0.8 && x.toFloat() != mapCenter && y.toFloat() != mapCenter
                val tile = Tile(x.toFloat(), y.toFloat(), hasObstacle)
                row.add(tile)
                tileGraph.addTile(tile)
            }

            tilesMatrix.add(row)
        }

        // Create connections
        for (y in 0 until mapSize) {
            for (x in 0 until mapSize) {
                createConnectionsForTile(x, y)
            }
        }

        // Testing only
        val startingPointX = 0
        val startingPointY = Random.nextInt(mapSize)
        println("Finding path to center from: $startingPointX, $startingPointY")
        val paths = tileGraph.findPath(tilesMatrix[startingPointX][startingPointY], tilesMatrix[mapCenter.toInt()][mapCenter.toInt()])

        for (y in 0 until mapSize) {
            var row = "|"

            for (x in 0 until mapSize) {
                val tile = tilesMatrix[y][x]

                if (paths.contains(tile)) {
                    row += " X |"
                } else if (tile.hasObstacle) {
                    row += " T |"
                } else {
                    row += " O |"
                }
            }

            println(row)
            println("_______".repeat(mapSize))
        }
    }

    private fun createConnectionsForTile(x: Int, y: Int) {
        val tile = tilesMatrix[y][x]
        val topCornerX = x - 1
        val topCornerY = y - 1

        for (boundsY in 0..2) {
            val connectionTileY = topCornerY + boundsY
            for (boundsX in 0..2) {
                val connectionTileX = topCornerX + boundsX
                if (isNotOutOfBounds(connectionTileX, connectionTileY) && (connectionTileX != x || connectionTileY != y)){
                    println("$x, $y, $connectionTileX, $connectionTileY")
                    tileGraph.connectTiles(tile, tilesMatrix[connectionTileY][connectionTileX])
                }
            }
        }
    }

    private fun isNotOutOfBounds(x: Int, y: Int): Boolean {
        return x >= 0 && y >= 0 && x < mapSize && y < mapSize
    }
}