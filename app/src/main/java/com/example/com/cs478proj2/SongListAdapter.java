package com.example.com.cs478proj2;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SongListAdapter extends ArrayAdapter<Song>{
    private List<Song> songs;

    public SongListAdapter(Context context, int resource, List<Song> objects) {
        super(context, resource, objects);
        songs = objects;
    }

    //method called automatically when scrolling up and down the list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //instantiate if the current view is null
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_song_complex, parent, false);
        }

        //get song from the list of songs
        Song song = songs.get(position);

        //set the name of the song in the List View
        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        nameText.setText(song.getName());

        //set the name of the artist in the List View
        TextView artistText = (TextView) convertView.findViewById(R.id.artistText);
        artistText.setText(song.getArtist());

        //set the name of the album in the List View
        TextView albumText = (TextView) convertView.findViewById(R.id.albumText);
        albumText.setText(song.getAlbum());

        //set the album cover of the song in the List View
        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
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
    }
}
