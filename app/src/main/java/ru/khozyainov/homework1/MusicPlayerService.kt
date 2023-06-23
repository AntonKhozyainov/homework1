package ru.khozyainov.homework1

import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService

@UnstableApi
class MusicPlayerService : MediaSessionService() {

    private var mediaSession: MediaSession? = null

    override fun onCreate() {
        super.onCreate()

        val player = ExoPlayer.Builder(this).build()
        player.addMediaItems(tracks)

        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? =
        mediaSession

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

    companion object {
        private val tracks = listOf(
            MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.richter)),
            MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.tchaikovsky)),
            MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.vivaldi))
        )
    }

}