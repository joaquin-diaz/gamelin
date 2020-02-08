package com.bochelin.chesstd.pathfinding

import com.badlogic.gdx.ai.pfa.Heuristic

class TileHeuristic: Heuristic<Tile> {
    override fun estimate(fromTile: Tile, toTile: Tile): Float {
        return fromTile.getCostTo(toTile)
    }
}