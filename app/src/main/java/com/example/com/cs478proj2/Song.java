package com.example.com.cs478proj2;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    private String songID;
    private String name;
    private String artist;
    private String album;
    private String songVideo;
    private String artistWiki;
    private String songWiki;

    public Song(String i, String n, String ar, String al, String v, String aw, String sw){
        this.songID = i;
        this.name = n;
        this.artist = ar;
        this.album = al;
        this.songVideo = v;
        this.artistWiki = aw;
        this.songWiki = sw;
    }

    public String getSongID(){ return songID; }
    public String getName(){ return name; }
    public String getArtist(){ return artist; }
    public String getAlbum(){ return album; }
    public String getSongVideo(){ return songVideo; }
    public String getArtistWiki(){ return artistWiki; }
    public String getSongWiki(){ return songWiki; }


    protected Song(Parcel in) {
        songID = in.readString();
        name = in.readString();
        artist = in.readString();
        album = in.readString();
        songVideo = in.readString();
        artistWiki = in.readString();
        songWiki = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songID);
        dest.writeString(name);
        dest.writeString(artist);
        dest.writeString(album);
        dest.writeString(songVideo);
        dest.writeString(artistWiki);
        dest.writeString(songWiki);
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}