package com.bochelin.chesstd.system

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

class Debug {
    companion object {
        val debugRenderer = ShapeRenderer()

        fun drawDebugLine(start: Vector2, end: Vector2, camera: OrthographicCamera) {
            debugRenderer.projectionMatrix = camera.combined
            debugRenderer.begin(ShapeRenderer.ShapeType.Line)
            debugRenderer.color = Color.RED
            debugRenderer.line(start, end)
            debugRenderer.end()
        }
    }
}