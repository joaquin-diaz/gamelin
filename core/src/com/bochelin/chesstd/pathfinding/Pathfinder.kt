package com.bochelin.chesstd.pathfinding

import com.badlogic.gdx.ai.pfa.GraphPath
import com.bochelin.chesstd.maps.Tile

class Pathfinder(val mapSize: Int, val tilesMatrix: Array<Array<Tile>>) {
    // Map is squared
    private val tileGraph = TileGraph()

    init {
        // Create connections
        for (y in 0 until mapSize) {
            for (x in 0 until mapSize) {
                tileGraph.addTile(tilesMatrix[y][x])
            }
        }

        for (y in 0 until mapSize) {
            for (x in 0 until mapSize) {
                createConnectionsForTile(x, y)
            }
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
                    tileGraph.connectTiles(tile, tilesMatrix[connectionTileY][connectionTileX])
                }
            }
        }
    }

    private fun isNotOutOfBounds(x: Int, y: Int): Boolean {
        return x >= 0 && y >= 0 && x < mapSize && y < mapSize
    }

    fun getTileAt(x: Int, y: Int): Tile {
        return tilesMatrix[x][y]
    }

    fun findPath(fromTile: Tile, toTile: Tile): GraphPath<Tile> {
        return tileGraph.findPath(fromTile, toTile)
    }
}