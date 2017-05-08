package com.example.com.cs478proj2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected static final String EXTRA_RES_ID = "POS";

    //retrieve list of songs through the data provider class
    public List<Song> songList = DataProvider.songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get adapter using the list_song_simple xml
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_song_simple, R.id.songName, DataProvider.getSongNames());
        //get reference to list view
        ListView lv = (ListView) findViewById(R.id.songs_list_view);
        //connect adapter to list view; fill list view with songs
        lv.setAdapter(adapter);

        //set listener for create playlist button below the list view
        final Button createPlaylistButton = (Button) findViewById(R.id.createPlaylistButton);
        createPlaylistButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                createPlaylist();
            }
        });
    }//end onCreate(...)

    //creates the menu for this activity using main_menu xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }//end onCreateOptionsMenu(...)

    //handles click events for menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_create_playlist){
            createPlaylist();
            return true;
        }
        else if(item.getItemId() == R.id.action_clear_all_selection){
            clearAll();
            return true;
        }
        else if(item.getItemId() == R.id.action_invert_selections){
            invertAll();
            return true;
        }
        else if(item.getItemId() == R.id.action_check_all_items){
            checkAll();
            return true;
        }
        else if(item.getItemId() == R.id.action_about){
            startAboutActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected(...)


    //displays a toast message based on text parameter; used for debugging
    public void displayToast(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }//end displayToast(...)

    //return song from song list based on search string parameters
    public Song getSongFromList(String searchString) {
        for (Song song : songList) {
            if(song.getName().equals(searchString))
                return song;
        }
        return null;
    }//end getSongFromList(...)

    //create a playlist from the checked songs in the List View
    public void createPlaylist(){
        //get reference the list view
        ListView lv = (ListView) findViewById(R.id.songs_list_view);
        //get number of songs
        int numSongs = lv.getChildCount();

        ArrayList<Song> checkedSongs = new ArrayList<>();

        //iterate through every song in list view, checking if the check box is marked
        for(int i = 0; i < numSongs; i++){
            CheckBox cb = ((CheckBox) lv.getChildAt(i).findViewById(R.id.checkBox));

            if(cb.isChecked()){
                //add it to the checkedSongs list
                TextView tv = ((TextView) lv.getChildAt(i).findViewById(R.id.songName));
                checkedSongs.add(getSongFromList(tv.getText().toString()));
            }
        }

        //if one or more were selected, start the playlist activity passing an ArrayList of songs to new activity
        if(checkedSongs.size() > 0){
            displayToast("Creating Playlist");
            Intent intent = new Intent(MainActivity.this, PlaylistActivity.class);
            intent.putParcelableArrayListExtra(EXTRA_RES_ID, checkedSongs);
            startActivity(intent);
        }
        else
            displayToast("Select at least one song to create a new playlist!");
    }//end createPlaylist()

    //iterate through all the songs in the List View and unchecks the checkboxes
    public void clearAll(){
        //get reference the list view
        ListView lv = (ListView) findViewById(R.id.songs_list_view);
        //get number of songs
        int numSongs = lv.getChildCount();

        for(int i = 0; i < numSongs; i++)
            ((CheckBox) lv.getChildAt(i).findViewById(R.id.checkBox)).setChecked(false);

        displayToast("Selections Cleared");
    }//end clearAll()

    //iterate through all the songs in the List View and inverts the checkbox statuses
    public void invertAll(){
        //get reference the list view
        ListView lv = (ListView) findViewById(R.id.songs_list_view);
        //get number of songs
        int numSongs = lv.getChildCount();

        for(int i = 0; i < numSongs; i++) {
            CheckBox child = ((CheckBox) lv.getChildAt(i).findViewById(R.id.checkBox));
            if(child.isChecked())
                child.setChecked(false);
            else
                child.setChecked(true);
        }
        displayToast("Selections Inverted");
    }//end invertAll()

    //iterate through all the songs in the List View and checks the checkboxes
    public void checkAll(){
        //get reference the list view
        ListView lv = (ListView) findViewById(R.id.songs_list_view);
        //get number of songs
        int numSongs = lv.getChildCount();

        for(int i = 0; i < numSongs; i++)
            ((CheckBox) lv.getChildAt(i).findViewById(R.id.checkBox)).setChecked(true);
        displayToast("Selecting All Songs");
    }//end checkAll()

    //start a new intent to go to the About page
    public void startAboutActivity(){
        displayToast("Going to About Page");
    }//end startAboutActivity()


}//end MainActivity
