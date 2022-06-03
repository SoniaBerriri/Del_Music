package com.example.music_player;

import android.net.Uri;

public class ModeSong {

   private String songTitle;
   private String songDuration;
    private String songArtist;
   private Uri songCover;
   private Uri songUri;

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public Uri getSongCover() {
        return songCover;
    }

    public void setSongCover(Uri songCover) {
        this.songCover = songCover;
    }

    public Uri getSongUri() {
        return songUri;
    }

    public void setSongUri(Uri songUri) {
        this.songUri = songUri;
    }

    public ModeSong(String songTitle, String songDuration, String songArtist, Uri songCover, Uri songUri) {
        this.songTitle = songTitle;
        this.songDuration = songDuration;
        this.songArtist = songArtist;
        this.songCover = songCover;
        this.songUri = songUri;
    }
}
