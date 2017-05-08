package com.example.com.cs478proj2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class DataProvider {
    public static List<Song> songList = new ArrayList<>();
    public static Map<String, Song> songMap = new HashMap<>();

    static {
        addSong("stoney", "Congratulations Ft. Quavo", "Post Malone", "Stoney",
                "https://www.youtube.com/watch?v=SC4xMk98Pdc", "https://en.wikipedia.org/wiki/Post_Malone",
                "https://en.wikipedia.org/wiki/Congratulations_(Post_Malone_song)");
        addSong("lilboat", "One Night", "Lil Yachty", "Lil Boat",
                "https://www.youtube.com/watch?v=251cxou3yR4", "https://en.wikipedia.org/wiki/Lil_Yachty",
                "https://en.wikipedia.org/wiki/One_Night_(Lil_Yachty_song)");
        addSong("broccoli", "Broccoli Ft. Lil Yachty", "D.R.A.M", "Broccoli",
                "https://www.youtube.com/watch?v=K44j-sb1SRY", "https://en.wikipedia.org/wiki/D.R.A.M.",
                "https://en.wikipedia.org/wiki/Broccoli_(D.R.A.M._song)");
        addSong("single", "Dat $tick", "Rich Chigga", "Single",
                "https://www.youtube.com/watch?v=rzc3_b_KnHc", "https://en.wikipedia.org/wiki/Rich_Chigga",
                "https://en.wikipedia.org/wiki/Dat_Stick");
        addSong("coloringbook", "No Problem Ft. Lil Wayne & 2 Chainz", "Chance The Rapper", "Coloring Book",
                "https://www.youtube.com/watch?v=DVkkYlQNmbc", "https://en.wikipedia.org/wiki/Chance_the_Rapper",
                "https://en.wikipedia.org/wiki/No_Problem_(Chance_the_Rapper_song)");
        addSong("becausetheinternet", "3005", "Childish Gambino", "Because The Internet",
                "https://www.youtube.com/watch?v=tG35R8F2j8k", "https://en.wikipedia.org/wiki/Donald_Glover",
                "https://en.wikipedia.org/wiki/3005_(song)");
    }

    //adds song the the list of songs
    private static void addSong(String songID, String name, String artist, String album,
                                String songVid, String artistWiki, String songWiki) {
        Song song = new Song(songID, name, artist, album, songVid, artistWiki, songWiki);
        songList.add(song);
        songMap.put(songID, song);
    }//end addSong(...)

    //returns and ArrayList of strings of song names
    public static List<String> getSongNames() {
        List<String> list = new ArrayList<>();
        for (Song song : songList) {
            list.add(song.getName());
        }
        return list;
    }//end getSongNames()

}//end DataProvider
