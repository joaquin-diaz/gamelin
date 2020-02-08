package com.bochelin.chesstd.pathfinding

import com.badlogic.gdx.ai.pfa.Connection
import com.badlogic.gdx.ai.pfa.DefaultGraphPath
import com.badlogic.gdx.ai.pfa.GraphPath
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.ObjectMap

class TileGraph: IndexedGraph<Tile> {
    val tileHeuristic: TileHeuristic = TileHeuristic()
    val tiles: Array<Tile> = Array()
    val links: Array<Link> = Array()

    val linksMap: ObjectMap<Tile, Array<Connection<Tile>>> = ObjectMap()

    var lastAddedIndex = 0

    fun addTile(tile: Tile) {
        tile.index = lastAddedIndex
        lastAddedIndex++

        tiles.add(tile)
    }

    fun connectTiles(fromTile: Tile, toTile: Tile) {
        val link = Link(fromTile, toTile)

        if (!linksMap.containsKey(fromTile)) {
            linksMap.put(fromTile, Array())
        }

        linksMap.get(fromTile).add(link)
        links.add(link)
    }

    fun findPath(startTile: Tile, goalTile: Tile): GraphPath<Tile> {
        val tilePath = DefaultGraphPath<Tile>()
        IndexedAStarPathFinder(this).searchNodePath(startTile, goalTile, tileHeuristic, tilePath)

        return tilePath
    }

    override fun getConnections(fromTile: Tile): Array<Connection<Tile>> {
        if (linksMap.containsKey(fromTile)) {
            return linksMap.get(fromTile)
        }

        return Array(0)
    }

    override fun getIndex(tile: Tile): Int {
        return tile.index
    }

    override fun getNodeCount(): Int {
        return lastAddedIndex
    }

}