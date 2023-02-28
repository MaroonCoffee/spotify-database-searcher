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
                if (genres.get(i).name.equals(n)) return genres.get(i);
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
        
        public String toString(){
            return name;
        }   
}