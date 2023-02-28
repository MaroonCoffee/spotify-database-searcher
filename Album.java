import java.util.ArrayList;

public class Album {
    private static ArrayList<Album> albums = new ArrayList<Album>();
    private String name;
    private Date date;
    private ArrayList<Artist> artists;
    private ArrayList<Song> songs;
    
    public Album(String n, ArrayList<Artist> a, Date d){
        name = n;
        date = d;
        artists = a;
        songs = new ArrayList<Song>();
        albums.add(this);
        for (int i=0; i<a.size(); i++) a.get(i).addAlbum(this);
    }
    
    public static Album getAlbum(String n){
        for (int i=0; i<albums.size(); i++){
            if (albums.get(i).name.equals(n)) return albums.get(i);
        }
        return null;
    }
    
    public static ArrayList<Album> getAllAlbums(){
        return albums;
    }
    
    public void addSong(Song song){
        songs.add(song);
    }
    
    public ArrayList<Song> getSongs(){
        return songs;
    }
    
    public String getName(){
        return name;
    }
    
    public Date getDate(){
        return date;
    }
    
    public ArrayList<Artist> getArtists(){
        return artists;
    }
    
    public String toString(){
        return name;
    }
}