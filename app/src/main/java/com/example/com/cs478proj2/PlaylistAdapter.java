package com.example.com.cs478proj2;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class PlaylistAdapter extends ArrayAdapter {
    private List<Song> songs;

    public PlaylistAdapter(Context context, int resource, List<Song> objects) {
        super(context, resource, objects);
        songs = objects;
    }

    //method called automatically when scrolling up and down the list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //instantiate if the current view is null
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_song_simple, parent, false);
        }

        Song song = songs.get(position);

        //set the album cover of the song in the Grid View
        ImageView iv = (ImageView) convertView.findViewById(R.id.albumImage);
        Bitmap bitmap = getBitmapFromAsset(song.getSongID());
        iv.setImageBitmap(bitmap);

        return convertView;
    }

    //returns a bitmap based on the song's ID using the images in the assets folder
    private Bitmap getBitmapFromAsset(String songID){
        AssetManager assetManager = getContext().getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(songID + ".jpg");
            return BitmapFactory.decodeStream(stream);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }//end getBitmapFromAsset(...)

}//end PlayListAdapter
