package com.bochelin.chesstd.system

import com.badlogic.gdx.graphics.OrthographicCamera

class GameCamera(val screenWidth: Float, val screenHeight: Float) : OrthographicCamera() {
    protected val SCREEN_MOVEMENT_VELOCITY = 0.7f;

    protected val MAX_ZOOM_DISTANCE = 1.7f
    protected val MIN_ZOOM_DISTANCE = 0.9f

    init {
        zoom = MIN_ZOOM_DISTANCE
        position.set(screenWidth / 2f, 0.0f, 0.0f)
        update()
    }

    fun adjustZoomLevels(initialDistance: Float, distance: Float) {
        val newZoomDistance = distance / initialDistance

        if (newZoomDistance <= MAX_ZOOM_DISTANCE && newZoomDistance > MIN_ZOOM_DISTANCE) {
            this.zoom = newZoomDistance
            this.update()
        }
    }

    fun adjustCameraPosition(deltaX: Float, deltaY: Float) {
        val zoomModifier = this.zoom / 2f
        var newX = this.position.x - (deltaX * SCREEN_MOVEMENT_VELOCITY) * zoomModifier
        var newY = this.position.y + (deltaY * SCREEN_MOVEMENT_VELOCITY) * zoomModifier

        if (newX > screenWidth || newX < 0) {
            newX = position.x
        }

        if (newY > screenHeight / 2f || newY < -screenHeight / 2f  ) {
            newY = position.y
        }

        if (newX != position.x || newY != position.y) {
            this.position.set(newX, newY, 0f)
            this.update()
        }
    }
}