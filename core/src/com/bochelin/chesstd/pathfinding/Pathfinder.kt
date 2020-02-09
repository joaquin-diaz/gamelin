package com.bochelin.chesstd.pathfinding

import com.badlogic.gdx.ai.pfa.GraphPath
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.utils.Array

class Pathfinder(mapLayer: TiledMapTileLayer, tileWidth: Int, tileHeight: Int) {
    // Map is squared
    private val mapSize = mapLayer.width
    private val tileGraph = TileGraph()
    private val tilesMatrix = Array<Array<Tile>>()

    init {
        // Create tiles
        for (y in 0 until mapSize) {
            val row = Array<Tile>()

            for (x in 0 until mapSize) {
                val tile = Tile((tileWidth * x) + (tileWidth / 2f) , (tileHeight * y) + (tileWidth / 2f))
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