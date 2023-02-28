import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Genre> genres;
    private ArrayList<Song> songs;
    
    public Category(String n){
        name = n;
        genres = new ArrayList<Genre>();
        songs = new ArrayList<Song>();
    }
    
    public void addSong(Song song){
        songs.add(song);
    }
    
    public String getName(){
        return name;
    }
    
    public void addGenre(Genre genre){
        genres.add(genre);
    }
    
    public ArrayList<Genre> getGenres(){
        return genres;
    }
    
    public String toString(){
        return name;
    }  
}