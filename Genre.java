import java.util.ArrayList;

public class Genre {
        private static ArrayList<Genre> genres = new ArrayList<Genre>();
        public static final Category uncategorized = new Category("Uncategorized");
        private ArrayList<Song> songs;
        private String name;
        private Category category;
        
        public Genre(String n, Category c){
            name = n;
            category = c;
            songs = new ArrayList<Song>();
            genres.add(this);
            c.addGenre(this);
        }
        
        public static Genre getGenre(String n){
            for (int i=0; i<genres.size(); i++){
                if (genres.get(i).name.toLowerCase().equals(n.toLowerCase())) return genres.get(i);
            }
            return null;
        }
        
        public static ArrayList<Genre> getAllGenres(){
            return genres;
        }
        
        public ArrayList<Song> getSongs(){
            return songs;
        }
        
        public String getName(){
            return name;
        }
        
        public Category getCategory(){
            return category;
        }
        
        public static ArrayList<Genre> sortGenresAlphabetically(ArrayList<Genre> genres){
        int genreCount = genres.size();
        for (int i=0; i<genreCount-1; i++){
            int maxIndex = i;
            for (int j=i+1; j < genreCount; j++){
                String maxString = genres.get(maxIndex).getName().toLowerCase();
                String indexString = genres.get(j).getName().toLowerCase();
                if(indexString.compareTo(maxString)<0) maxIndex = j;
            }
            Genre temp = genres.get(maxIndex);
            genres.set(maxIndex, genres.get(i));
            genres.set(i, temp);
        }
        return genres;
    }
        
        public String toString(){
            return name;
        }   
}