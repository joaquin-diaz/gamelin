package com.bochelin.chesstd.maps

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer
import com.bochelin.chesstd.pathfinding.Pathfinder
import ktx.tiled.tileHeight
import ktx.tiled.tileWidth

class BaseMap {
    private lateinit var renderer: IsometricTiledMapRenderer
    private lateinit var pathFinder: Pathfinder


    fun create() {
        val map = TmxMapLoader().load("maps/map.tmx")
        pathFinder = Pathfinder(map.layers.get("base") as TiledMapTileLayer, map.tileWidth, map.tileHeight)
        renderer = IsometricTiledMapRenderer(map)
    }

    fun render(camera: OrthographicCamera) {
        renderer.setView(camera)
        renderer.render()
    }
}