package com.bochelin.chesstd.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2
import com.bochelin.chesstd.Game
import com.bochelin.chesstd.maps.BaseMap
import com.bochelin.chesstd.system.Debug
import com.bochelin.chesstd.system.GameCamera
import ktx.app.KtxScreen

open class BaseScreen(val game: Game): KtxScreen, GestureDetector.GestureListener {
    val spriteBatch = SpriteBatch()
    val map = BaseMap()

    companion object {
        val WIDTH = 1280f
        val HEIGHT = 720f
    }

    val camera = GameCamera(WIDTH, HEIGHT).apply {
        setToOrtho(false, WIDTH, HEIGHT)
        this.position.x = 0f
        this.position.y = 0f
        this.update()
    }

    init {
        Gdx.input.inputProcessor = GestureDetector(this)
    }

    override fun render(delta: Float) {
        super.render(delta)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0)

        spriteBatch.projectionMatrix = camera.combined
        camera.update()
    }

    override fun resize(width: Int, height: Int) {
        camera.viewportWidth = width.toFloat()
        camera.viewportHeight = height.toFloat()
        camera.update()

        super.resize(width, height)
    }

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        return false
    }

    override fun zoom(initialDistance: Float, distance: Float): Boolean {
        camera.adjustZoomLevels(initialDistance, distance)
        return true
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        camera.adjustCameraPosition(deltaX, deltaY)
        return true
    }

    override fun pinchStop() {

    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        return false
    }

    override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun longPress(x: Float, y: Float): Boolean {
        return false
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun pinch(initialPointer1: Vector2?, initialPointer2: Vector2?, pointer1: Vector2?, pointer2: Vector2?): Boolean {
        return false
    }
}