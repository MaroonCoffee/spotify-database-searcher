/*
DEFUNCT, Since spotify API doesn't give lyrics, there's no reason to include 
this as part of the project, as we'd have to do every song manually. It would 
also take up way too much space
*/

import java.util.Scanner;

public class Lyrics {
    String lyrics;
    
    public Lyrics(String s){
        lyrics = s;
    }
    
    public Lyrics(Scanner s){
        lyrics = new String("");
        while (true){
            System.out.println("Enter the next line of your lyrics (!quit to quit)");
            String lyric = s.nextLine();
            if (lyric.equals("!quit")) break;
            lyrics = lyrics + lyric + "\n";
        }
    }
    
    public String getLyrics(){
        return lyrics;
    }
}