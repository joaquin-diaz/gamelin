package com.bochelin.chesstd.pathfinding

import com.badlogic.gdx.ai.pfa.Connection

class Link(val fromTile: Tile, val toTile: Tile): Connection<Tile> {
    override fun getFromNode(): Tile {
        return fromTile
    }

    override fun getToNode(): Tile {
        return toTile
    }

    override fun getCost(): Float {
        return fromTile.getCostTo(toTile)
    }
}
