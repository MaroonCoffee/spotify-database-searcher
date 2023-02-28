import java.util.ArrayList;

public class Artist {
    private static ArrayList<Artist> artists = new ArrayList<Artist>();
    private String name;
    private ArrayList<Album> albums;
    private ArrayList<Song> features;
    private ArrayList<Genre> genres;
    
    public Artist(String n, ArrayList<Genre> g){
        name = n;
        artists.add(this);
        albums = new ArrayList<Album>();
        features = new ArrayList<Song>();
        genres = g;
    }
    
    public static Artist getArtist(String n){
        for (int i=0; i<artists.size(); i++){
            if (artists.get(i).name.equals(n)) return artists.get(i);
        }
        return null;
    }
    
    public ArrayList<Genre> getGenres(){
        return genres;
    }
    
    public void addAlbum(Album album){
        albums.add(album);
    }
    
    public void addFeature(Song song){
        features.add(song);
    }
    
    public String getName(){
        return name;
    }
    
    public ArrayList<Album> getAlbums(){
        return albums;
    }
    
    public static ArrayList<Artist> getAllArtists(){
        return artists;
    }
    
    public ArrayList<Song> getFeatures(){
        return features;
    }
    
    public int getSongCount(){
        int counter = 0;
        for (int i=0; i<albums.size(); i++){
            counter += albums.get(i).getSongs().size();
        }
        counter += features.size();
        return counter;
    }
    
    public String toString(){
        return name;
    }
}