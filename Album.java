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
            if (albums.get(i).name.toLowerCase().equals(n.toLowerCase())) return albums.get(i);
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
    
    public static ArrayList<Album> sortAlbumsAlphabetically(ArrayList<Album> albums){
        int albumCount = albums.size();
        for (int i=0; i<albumCount-1; i++){
            int maxIndex = i;
            for (int j=i+1; j < albumCount; j++){
                String maxString = albums.get(maxIndex).getName().toLowerCase();
                String indexString = albums.get(j).getName().toLowerCase();
                if(indexString.compareTo(maxString)<0) maxIndex = j;
            }
            Album temp = albums.get(maxIndex);
            albums.set(maxIndex, albums.get(i));
            albums.set(i, temp);
        }
        return albums;
    }
    
    public String toString(){
        return name;
    }
}