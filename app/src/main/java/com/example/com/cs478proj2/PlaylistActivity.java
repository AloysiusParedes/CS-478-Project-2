package com.example.com.cs478proj2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import static com.example.com.cs478proj2.MainActivity.EXTRA_RES_ID;

public class PlaylistActivity extends AppCompatActivity {

    private ArrayList<Song> songs = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        //retrieve ArrayList of songs from parent activity
        Bundle data = getIntent().getExtras();
        songs = data.getParcelableArrayList(EXTRA_RES_ID);

        //declare an adapter using the grid_song_simple xml
        PlaylistAdapter adapter = new PlaylistAdapter(this, R.layout.grid_song_simple, songs);

        //reference to grid view
        GridView gv = (GridView) findViewById(R.id.playlistGridView);

        //sets adapter for grid view and fills it with data
        gv.setAdapter(adapter);

        //handles the click event for when an item on the grid view is clicked
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //launch the YouTube page for the song
                Song song = (Song) parent.getItemAtPosition(position);
                launchBrowser(song.getSongVideo());
            }
        });

        //register the long click event to start a context menu
        registerForContextMenu(gv);
    }//end onCreate(...)

    //inflates the context menu from the playlist_context_menu xml
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.playlist_context_menu, menu);
    }//end onCreateContextMenu(...)

    //handles click events for context menu on long click
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.playVideo:
                launchBrowser(songs.get(info.position).getSongVideo());
                return true;
            case R.id.artistWiki:
                launchBrowser(songs.get(info.position).getArtistWiki());
                return true;
            case R.id.songWiki:
                launchBrowser(songs.get(info.position).getSongWiki());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }//end onContextItemSelected(...)

    //opens browser, and goes to site based on text parameter
    public void launchBrowser(String url){
        Intent openURL = new Intent(Intent.ACTION_VIEW);
        openURL.setData(Uri.parse(url));
        startActivity(openURL);
    }//end launchBrowser(...)

}//end PlaylistActivity
