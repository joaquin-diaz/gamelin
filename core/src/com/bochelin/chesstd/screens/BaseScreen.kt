package com.bochelin.chesstd.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.FitViewport
import com.bochelin.chesstd.Game
import com.bochelin.chesstd.system.GameCamera
import ktx.app.KtxScreen

open class BaseScreen(val game: Game): KtxScreen, GestureDetector.GestureListener {
    protected val WIDTH = 1920f
    protected val HEIGHT = 1080f
    protected val camera = GameCamera(WIDTH, HEIGHT).apply { setToOrtho(false, WIDTH, HEIGHT) }
    protected val viewport = FitViewport(WIDTH / 4, HEIGHT / 4, camera)

    init {
        Gdx.input.inputProcessor = GestureDetector(this)
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height)

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