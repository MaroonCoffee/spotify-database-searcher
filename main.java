//Powered by WatsonBox https://watsonbox.github.io/exportify
//Objectively the best Watson, both at coding and at being cool B)
//Make sure to enable "Include artists data" in the settings, so this tool works correctly

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        initializeData(input);
        ArrayList dummyList = new ArrayList();
        for (int i=0; i<4; i++) dummyList.add(null);
        while (true){
            clearScreen();
            printHeader("Welcome to Sorenify!");
            System.out.println();
            printHeader("What would you like to search today: ");
            System.out.println("(1) Artists");
            System.out.println("(2) Albums");
            System.out.println("(3) Songs");
            System.out.println("(4) Genres");
            System.out.println("(5) Songs sorted by Popularity");
            System.out.println("(6) Songs sorted by Duration");
            System.out.println("(7) Songs sorted by Date");
            //System.out.println("(8) Add Playlist");
            System.out.println("(8) Info");
            System.out.println("(9) Quit");
            System.out.println("Enter the corresponding number for your selection");
            printLine();
            int userInput = simpleSearch(9, input);
            if(userInput==1) databaseSearchHandler(2, dummyList, input);
            else if(userInput==2) databaseSearchHandler(1, dummyList, input);
            else if(userInput==3) databaseSearchHandler(0, dummyList, input);
            else if(userInput==4) databaseSearchHandler(3, dummyList, input);
            else if(userInput==5) databaseSearchHandler(9, dummyList, input);
            else if(userInput==6) databaseSearchHandler(10, dummyList, input);
            else if(userInput==7) databaseSearchHandler(11, dummyList, input);
            //else if(userInput==8) managePlaylists(input);
            else if(userInput==8){
                clearScreen();
                printHeader("Credits: Soren, Holden, and Elijah (c) 2023");
                System.out.println();
                System.out.println("Powered by WatsonBox https://watsonbox.github.io/exportify");
                System.out.println("Add your own playlists by downloading them through WatsonBox and adding them to sources.csv!");
                System.out.println("Make sure to enable 'Include artists data' in the settings, so this tool works correctly");
                System.out.println("Also, WatsonBox is the objectively the best Watson, both at coding and at being cool B)");
                System.out.println("\nThat's all, I hope you enjoy the program!");
                printHeader("Press enter to continue...");
                input.nextLine();
            }
            else if(userInput==9){
                clearScreen();
                printHeader("Credits: Soren, Holden, and Elijah (c) 2023");
                break;
            }
        }
    }
    
    //This would work but I can't get codehs to write data!
    //If someone figures out how to, let me know and I can implement this!
    /*public static void managePlaylists(Scanner input){
        clearScreen();
        printHeader("Would you like to add or remove a playlist?");
        System.out.println("(1) Add Playlist");
        System.out.println("(2) Remove Playlist");
        System.out.println("(3) Return to Main Menu");
        int userInput = simpleSearch(3, input);
        if (userInput==1){
            printHeader("Please enter the name of the playlist you'd like to add (include the file extension!)");
            String playlistName = input.nextLine();
            try (PrintWriter output = new PrintWriter("test.txt")) {
                output.println("test");
                output.flush();
            }           
            catch(Exception e){
                System.out.println(e);
            }
            System.out.println("Returning to main menu!");
            input.nextLine();
        }
    }*/
    
    public static void initializeData(Scanner input){
        String sources = CSVToString("sources.csv");
        ArrayList<String> playlists = textSplitter(sources, false, ',');
        importSongs(playlists);
        System.out.println("Press enter to continue...");
        printLine();
        input.nextLine();
    }
    
    //0=Songs, 1=Albums, 2=Artists, 3=Genres, 4=Songs by Artist, 5=Songs by Album, 6=Albums by artist, 7=Artists by Genre
    //8=Genre by Artists, 9=Songs by Popularity, 10=Songs by Duration, 11=Songs by Date
    public static int databaseSearch(int mode, ArrayList modeResources, Scanner input){
        clearScreen();
        String searchPrompt = "You can search for the following ";
        ArrayList allMedia = new ArrayList();
        if (mode == 0){
            allMedia = Song.getAllSongs();
            searchPrompt += "songs";
        }
        else if(mode == 1){
            allMedia = Album.getAllAlbums();
            searchPrompt += "albums";
        }
        else if (mode == 2){
            allMedia = Artist.getAllArtists();
            searchPrompt += "artists";
        }
        else if (mode == 3){
            allMedia = Genre.getAllGenres();
            searchPrompt += "genres";
        }
        else if (mode == 4){
            allMedia = getAllSongsByArtist((Artist) modeResources.get(0));
            searchPrompt += "songs by " + ((Artist) modeResources.get(0));
        }
        else if (mode == 5){
            allMedia = ((Album) modeResources.get(2)).getSongs();
            searchPrompt += "songs in " + ((Album) modeResources.get(2));
        }
        else if (mode == 6){
            allMedia = ((Artist) modeResources.get(0)).getAlbums();
            searchPrompt += "albums by " + ((Artist) modeResources.get(0));
        }
        else if (mode == 7){
            allMedia = getAllArtistsUnderGenre((Genre) modeResources.get(3));
            searchPrompt += "artists under the " + ((Genre) modeResources.get(3)) + " genre";
        }
        else if (mode == 8){
            allMedia = ((Artist) modeResources.get(0)).getGenres();
            searchPrompt += "genres explored by " + ((Artist) modeResources.get(0));
        }
        else if (mode == 9){
            allMedia = sortSongs(0, Song.getAllSongs());
            searchPrompt += "songs ranked by popularity";
        }
        else if (mode == 10){
            allMedia = sortSongs(1, Song.getAllSongs());
            searchPrompt += "songs ranked by duration";
        }
        else if (mode == 11){
            allMedia = sortSongs(2, Song.getAllSongs());
            searchPrompt += "songs ranked by date released";
        }
        printHeader(searchPrompt);
        if (allMedia.size() != 0){
            for (int i=0; i<allMedia.size(); i++){
                System.out.print("(" + (i+1) + ") ");
                if (mode==9) System.out.print("(Pop: " + (((Song) allMedia.get(i)).getPopularity()) + ") - ");
                else if (mode==10) System.out.print("(Length: " + (((Song) allMedia.get(i)).getFormattedDuration()) + ") - ");
                else if (mode==11) System.out.print("(Date: " + (((Song) allMedia.get(i)).getDate()) + ") - ");
                System.out.println(allMedia.get(i));
            }
            System.out.println("(" + (allMedia.size()+1) + ") Quit to Main Menu");
            int userInput = simpleSearch(allMedia.size()+1, input);
            if (userInput == allMedia.size()+1) return 0;
            String mediaName = allMedia.get(userInput-1).toString();
        
            printLine();
            if (mode==1||mode==6) modeResources.set(2, Album.getAlbum(mediaName));
            else if(mode==2||mode==7) modeResources.set(0, Artist.getArtist(mediaName));
            else if (mode==3||mode==8) modeResources.set(3, Genre.getGenre(mediaName));
            else modeResources.set(1, Song.getSong(mediaName));
            return 1;
        }
        else return -1;
    }
    
    public static void databaseSearchHandler(int mode, ArrayList modeResources, Scanner input){
        while (true){
            int exitCode = databaseSearch(mode, modeResources, input);
            if (exitCode == 0) break;
            else if (exitCode == -1){
                if (mode==6){
                    System.out.println("No albums by this artist!");
                    System.out.println("(They must only have features in this playlist)");
                    printHeader("Redirecting to all songs by this artist, press enter to continue...");
                    input.nextLine();
                    clearScreen();
                    mode = 4;
                    continue;
                }
                else{
                    System.out.println("An error has occured, as your search has returned no results");
                    System.out.println("If you see this, please let me know!");
                    System.out.println("Returning to main menu...");
                    input.nextLine();
                    break;
                }
            }
            else{
                if (mode==0||mode==4||mode==5||mode==9||mode==10||mode==11){
                    clearScreen();
                    Song song = (Song) modeResources.get(1);
                    showSongInfo(song, input);
                    printHeader("What do you want to search for:");
                    System.out.println("(1) Other Songs by Artist ");
                    System.out.println("(2) Other Songs in Album");
                    System.out.println("(3) Other Albums by Artist");
                    System.out.println("(4) Other artists in a similar genre");
                    System.out.println("(5) Quit");
                    int userInput = simpleSearch(5, input);
                    if (userInput==1){
                        ArrayList<Artist> artists = song.getArtists();
                        userInput = databaseSearchHandlerSpecification(artists, 0, input);
                        mode = 4;
                        modeResources.set(0, artists.get(userInput-1));
                        continue;
                    }
                    else if (userInput==2){
                        mode = 5;
                        modeResources.set(2, song.getAlbum());
                        continue;
                    }
                    else if (userInput==3){
                        ArrayList<Artist> artists = song.getArtists();
                        userInput = databaseSearchHandlerSpecification(artists, 0, input);
                        mode = 6;
                        modeResources.set(0, artists.get(userInput-1));
                        continue;
                    }
                    else if (userInput==4){
                        ArrayList<Genre> genres = song.getGenres();
                        userInput = databaseSearchHandlerSpecification(genres, 1, input);
                        mode = 7;
                        modeResources.set(3, genres.get(userInput-1));
                        continue;
                    }
                    else break;
                }
                else if (mode==1||mode==6){
                    clearScreen();
                    Album album = (Album) modeResources.get(2);
                    showAlbumInfo(album, input);
                    printHeader("What do you want to search for:");
                    System.out.println("(1) Songs in Album");
                    System.out.println("(2) Other Songs by Artist");
                    System.out.println("(3) Other Albums by Artist");
                    System.out.println("(4) Quit");
                    int userInput = simpleSearch(4, input);
                    if (userInput==1){
                        mode = 5;
                        modeResources.set(2, album);
                        continue;
                    }
                    if (userInput==2){
                        userInput = databaseSearchHandlerSpecification(album.getArtists(), 0, input);
                        mode = 4;
                        modeResources.set(0, album.getArtists().get(userInput-1));
                        continue;
                    }
                    if (userInput==3){
                        userInput = databaseSearchHandlerSpecification(album.getArtists(), 0, input);
                        mode = 6;
                        modeResources.set(0, album.getArtists().get(userInput-1));
                        continue;
                    }
                    else break;
                }
                else if (mode==2||mode==7){
                    clearScreen();
                    Artist artist = (Artist) modeResources.get(0);
                    showArtistInfo(artist, input);
                    printHeader("What do you want to search for:");
                    System.out.println("(1) Songs by Artist");
                    System.out.println("(2) Albums by Artist");
                    System.out.println("(3) Other artists in a similar genre");
                    System.out.println("(4) Quit");
                    int userInput = simpleSearch(4, input);
                    if (userInput==1){
                        mode = 4;
                        modeResources.set(0, artist);
                        continue;
                    }
                    else if (userInput==2){
                        mode = 6;
                        modeResources.set(0, artist);
                        continue;
                    }
                    else if (userInput==3){
                        ArrayList<Genre> genres = artist.getGenres();
                        userInput = databaseSearchHandlerSpecification(genres, 1, input);
                        mode = 7;
                        modeResources.set(3, genres.get(userInput-1));
                        continue;
                    }
                    else break;
                }
                else if (mode==3||mode==8){
                    clearScreen();
                    Genre genre = (Genre) modeResources.get(3);
                    mode = 7;
                    modeResources.set(3, genre);
                    continue;
                }
            }
        }
    }
    
    //0=Artist,1=Genre
    public static int databaseSearchHandlerSpecification(ArrayList choices, int mode, Scanner input){
        if (choices.size() == 1) return 1;
        printLine();
        System.out.print("Which ");
        if (mode==0) System.out.println("artist:");
        else System.out.println("genre:");
        printLine();
        for (int i=0; i<choices.size(); i++){
            System.out.println("(" + (i+1) + ") " + choices.get(i));
        }
        int userInput = simpleSearch(choices.size(), input);
        return userInput;
    }
    
    public static int simpleSearch(int options, Scanner input){
        while (true){
            System.out.print("Selection: ");
            String userInput = input.nextLine();
            int userNumericInput = -1;
            try{userNumericInput = Integer.parseInt(userInput);}
            catch(Exception e){}
            if((userNumericInput < 1)||(userNumericInput > options)){
                System.out.println("Invalid response! Press enter to continue...");
                input.nextLine();
                continue;
            }
            else return userNumericInput;
        }
    }
    
    //0=Popularity, 1=Duration, 2=Date
    public static ArrayList<Song> sortSongs(int mode, ArrayList<Song> songs){
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int songCount = songs.size();
        for (int i=0; i<songCount-1; i++){
            int maxIndex = i;
            for (int j=i+1; j < songCount; j++){
                double maxStat = 0.0;
                double indexStat = 0.0;
                if (mode == 0){
                    maxStat = songs.get(maxIndex).getPopularity();
                    indexStat = songs.get(j).getPopularity();
                }
                else if (mode == 1){
                    maxStat = songs.get(maxIndex).getDuration();
                    indexStat = songs.get(j).getDuration();
                }
                else{
                    Date maxDate = songs.get(maxIndex).getDate();
                    Date indexDate = songs.get(j).getDate();
                    if (!(maxDate.isEarlier(indexDate))) maxIndex = j;
                }
                if (mode != 2) if(indexStat > maxStat) maxIndex = j;
            }
            Song temp = songs.get(maxIndex);
            songs.set(maxIndex, songs.get(i));
            songs.set(i, temp);
        }
        return songs;
    }
    
    public static ArrayList<Artist> getAllArtistsUnderGenre(Genre genre){
        ArrayList<Artist> allArtists = Artist.getAllArtists();
        ArrayList<Artist> genreArtists = new ArrayList<Artist>();
        for (int i=0; i<allArtists.size(); i++){
            boolean hasGenre = false;
            ArrayList<Genre> artistGenres = allArtists.get(i).getGenres();
            for (int j=0; j<artistGenres.size(); j++){
                if (artistGenres.get(j).getName().equals(genre.getName())){
                    hasGenre = true;
                    break;
                }
            }
            if (hasGenre) genreArtists.add(allArtists.get(i));
        }
        return genreArtists;
    }
    
    public static ArrayList<Song> getAllSongsByArtist(Artist artist){
        ArrayList<Song> allSongs = new ArrayList<Song>();
        ArrayList<Album> albums = artist.getAlbums();
        for (int i=0; i<albums.size(); i++){
            ArrayList<Song> songs = albums.get(i).getSongs();
            for (int j=0; j<songs.size(); j++) allSongs.add(songs.get(j));
        }
        ArrayList<Song> features = artist.getFeatures();
        for (int i=0; i<features.size(); i++) allSongs.add(features.get(i));
        return allSongs;
    }
    
    public static void showArtistInfo(Artist artist, Scanner input){
        printHeader("Artist Info:");
        System.out.println("Artist Name: " + artist.getName());
        System.out.println("Artist Track Count: " + artist.getSongCount());
        System.out.print("Artist Genres(s): ");
        ArrayList<Genre> artistGenres = artist.getGenres();
        if (artistGenres.size() > 1){
            for (int i=0; i<artistGenres.size()-1; i++) System.out.print(artistGenres.get(i) + ", ");
        }
        System.out.println(artistGenres.get(artistGenres.size()-1));
        printHeader("Artist Albums:");
        for (int i=0; i<artist.getAlbums().size(); i++) System.out.println(artist.getAlbums().get(i).getName());
        printHeader("Press enter to continue...");
        input.nextLine();
    }
    
    public static void showAlbumInfo(Album album, Scanner input){
        printHeader("Album Info:");
        System.out.println("Album Name: " + album.getName());
        System.out.print("Album Artist(s): " );
        ArrayList<Artist> albumArtists = album.getArtists();
        if (albumArtists.size() > 1){
            for (int i=0; i<albumArtists.size()-1; i++) System.out.print(albumArtists.get(i) + ", ");
        }
        System.out.println(albumArtists.get(albumArtists.size()-1));
        System.out.println("Album Release Date: " + album.getDate());
        ArrayList<Song> allAlbumSongs = album.getSongs();
        System.out.println("Track Count: " + album.getSongs().size());
        System.out.println("Album Genre(s): ");
        ArrayList<Genre> novelSongGenres = new ArrayList<Genre>();
        for (int i=0; i<albumArtists.size(); i++){
            ArrayList<Genre> genres = albumArtists.get(i).getGenres();
            for (int j=0; j<genres.size(); j++){
                boolean novel = true;
                for (int k=0; k<novelSongGenres.size(); k++){
                    if (genres.get(j).equals(novelSongGenres.get(k))){
                        novel = false;
                        break;
                    }
                }
                if (novel) novelSongGenres.add(genres.get(j));
            }
        }
        if (novelSongGenres.size() > 1){
            for (int i=0; i<novelSongGenres.size()-1; i++) System.out.print(novelSongGenres.get(i) + ", ");
        }
        System.out.println(novelSongGenres.get(novelSongGenres.size()-1));
        printHeader("Album songs:");
        for (int i=0; i<allAlbumSongs.size(); i++) System.out.println(allAlbumSongs.get(i).getName());
        printHeader("Press enter to continue...");
        input.nextLine();
    }
    
    public static void showSongInfo(Song song, Scanner input){
        printHeader("Song info:");
        System.out.println("Track name: " + song.getName());
        System.out.print("Track Artist(s): ");
        ArrayList<Artist> songArtists = song.getArtists();
        if (songArtists.size() > 1){
            for (int i=0; i<songArtists.size()-1; i++) System.out.print(songArtists.get(i) + ", ");
        }
        System.out.println(songArtists.get(songArtists.size()-1));
        System.out.println("Album name: " + song.getAlbum());
        System.out.print("Album Artist(s): " );
        ArrayList<Artist> albumArtists = song.getAlbum().getArtists();
        if (albumArtists.size() > 1){
            for (int i=0; i<albumArtists.size()-1; i++) System.out.print(albumArtists.get(i) + ", ");
        }
        System.out.println(albumArtists.get(albumArtists.size()-1));
        System.out.println("Track Release Date: " + song.getDate());
        System.out.println("Track Duration: " + song.getFormattedDuration());
        System.out.println("Explicit? : " + song.getFormattedExplicitRating());
        System.out.println("Popularity: " + song.getPopularity());
        System.out.print("Track Genres(s): ");
        ArrayList<Genre> songGenres = song.getGenres();
        if (songGenres.size() > 1){
            for (int i=0; i<songGenres.size()-1; i++) System.out.print(songGenres.get(i) + ", ");
        }
        System.out.println(songGenres.get(songGenres.size()-1));
        printHeader("Press enter to continue...");
        input.nextLine();
    }
    
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
    }
    
    public static void printHeader(String string){
        System.out.println("-------------------------------------------------");
        System.out.println(string);
        System.out.println("-------------------------------------------------");
    }
    
    public static void printLine(){
        System.out.println("-------------------------------------------------");
    }
    
    public static String CSVToString(String filename){
        //Reads csv file and converts it to a string to be processed
        //Stolen from Sentiment Value CodeHS
        String returnString = "";
        try{
            Scanner s = new Scanner(new File(filename));
            s.useDelimiter(",|\n");
            while(s.hasNext()){
                returnString = returnString + s.next() + ",";
            }
            if (returnString.charAt(returnString.length()-1) == ','){
                returnString = returnString.substring(0,returnString.length()-1);
            }
            s.close();
        }
        catch (IOException e){
            System.out.println("File not found!");
        }
        return returnString;
    }
    
    public static ArrayList<ArrayList<String>> processCSV(String filename){
        String input = CSVToString(filename);
        ArrayList<String> tokens = textSplitter(input, false, ',');
        tokens.remove(tokens.size()-1); //Removes final token as it is just white space
        ArrayList<ArrayList<String>> songs = new ArrayList<ArrayList<String>>();
        int originalTokensSize = tokens.size();
        for (int i=0; i<originalTokensSize/20; i++){ //For every song
            ArrayList<String> song = new ArrayList<String>();
            for (int j=0; j<=19; j++){ //For every token
                String token = tokens.remove(0);
                if (j==1||j==3||j==5||j==7||j==8||j==12||j==14||j==15||j==19) song.add(token);
            }
            songs.add(song);
        }
        songs.remove(0); //Removes the Exportify header
            return songs;
    }
    
    public static void importSongs (ArrayList<String> playlists){
        printHeader(playlists.size() + " playlists found!");
        for (int i=0; i<playlists.size(); i++){ //For each playlist
            ArrayList<ArrayList<String>> playlist = processCSV("playlists/" + playlists.get(i));
            printHeader(playlist.size() + " songs found in: " + playlists.get(i));
            for (int j=0; j<playlist.size(); j++){ //For each song
                ArrayList<String> songAspects = playlist.get(j);
                
                //Parsing song genres
                String songGenreNames = removeQuotes(songAspects, 8);
                ArrayList<String> songGenreNamesList = textSplitter(songGenreNames, false, ',');
                ArrayList<Genre> songGenreList = new ArrayList<Genre>();
                for (int k=0; k<songGenreNamesList.size(); k++){
                    Genre genre = Genre.getGenre(songGenreNamesList.get(k)); //Gets genre for album
                    if (genre == null) genre = new Genre(songGenreNamesList.get(k), Genre.uncategorized); //Makes genre if doesn't exist
                    songGenreList.add(genre);
                }
                
                //Parsing artists
                String artistName = removeQuotes(songAspects, 1);
                ArrayList<String> artistNameList = textSplitter(artistName, true, ',');
                ArrayList<Artist> artistList = new ArrayList<Artist>();
                for (int k=0; k<artistNameList.size(); k++){
                    Artist artist = Artist.getArtist(artistNameList.get(k)); //Gets artist for song
                    if (artist == null) artist = new Artist(artistNameList.get(k), songGenreList); //Makes artist if doesn't exist
                    artistList.add(artist);
                }
                
                //Parsing album artists
                String albumArtistName = removeQuotes(songAspects, 3);
                ArrayList<String> albumArtistNameList = textSplitter(albumArtistName, true, ',');
                ArrayList<Artist> albumArtistList = new ArrayList<Artist>();
                for (int k=0; k<albumArtistNameList.size(); k++){
                    Artist artist = Artist.getArtist(albumArtistNameList.get(k)); //Gets artist for album
                    if (artist == null) artist = new Artist(albumArtistNameList.get(k), songGenreList); //Makes artist if doesn't exist
                    albumArtistList.add(artist);
                }
                
                //Parsing song date
                String dateString = removeQuotes(songAspects, 4);
                char delimiter = '!'; 
                for (int k=0; k<dateString.length(); k++){
                    if (dateString.charAt(k) == '-') delimiter = '-';
                    else if (dateString.charAt(k) == '/') delimiter = '/';
                }
                Date songDate = new Date(1,1,1900);
                if (delimiter != '!'){
                    ArrayList<String> dateStringArray = textSplitter(dateString, false, delimiter);
                    if (dateStringArray.size() == 1) songDate = new Date(1,1,Integer.parseInt(dateStringArray.get(0)));
                    else if (dateStringArray.size() == 2) songDate = new Date(1,Integer.parseInt(dateStringArray.get(1)),Integer.parseInt(dateStringArray.get(0)));
                    else if (dateStringArray.size() == 3) songDate = new Date(Integer.parseInt(dateStringArray.get(2)),Integer.parseInt(dateStringArray.get(1)),Integer.parseInt(dateStringArray.get(0)));
                }
                
                //Parsing explicit rating
                String explicitRatingString = removeQuotes(songAspects, 6);
                boolean explicitRating = true;
                if (explicitRatingString.equals("false")) explicitRating = false;
                
                //Parsing song popularity
                String songPopularityString = removeQuotes(songAspects, 7);
                int songPopularity = Integer.parseInt(songPopularityString);
                
                //Parsing song duration
                String songDurationString = removeQuotes(songAspects, 5);
                int songDuration = Integer.parseInt(songDurationString);
                
                //Parsing album
                String albumName = removeQuotes(songAspects, 2);
                Album album = Album.getAlbum(albumName); //Gets album
                if (album == null) album = new Album(albumName, albumArtistList, songDate); //Makes album if doesn't exist
                
                //Parsing song
                String songName = removeQuotes(songAspects, 0);
                Song song = Song.getSong(songName); //Gets song
                System.out.println("Importing " + songName);
                if (song == null) song = new Song(songName, artistList, album, songGenreList, songDate, songDuration, explicitRating, songPopularity); //Makes song if doesn't exist
                
                //Adding Features
                for (int k=0; k<artistList.size(); k++){
                    boolean onAlbum = false;
                    for (int l=0; l<albumArtistList.size(); l++){
                        if(artistList.get(k).equals(albumArtistList.get(l))){
                            onAlbum = true;
                            break;
                        }
                    }
                    if (!onAlbum) artistList.get(k).addFeature(song);
                }
            }
        }
    }
    
    public static String removeQuotes(ArrayList<String> songAspects, int index){
        String returnString = songAspects.get(index);
        returnString = returnString.substring(1, returnString.length()-1);
        return returnString;
    }
    
    public static ArrayList<String> textSplitter(String input, boolean spaceBetween, char delimiter){
        //Separates text by the specified delimiter, and actually works unlike scanner >:(
        //(Scanner just splits text after reading ANY comma, so commas in quotes mess up)
        //Stolen and modified from https://www.baeldung.com/java-split-string-commas
        int spacer = 1;
        if (spaceBetween) spacer = 2;
        ArrayList<String> tokens = new ArrayList<String>();
        int startPosition = 0;
        boolean isInQuotes = false;
        for (int currentPosition = 0; currentPosition < input.length(); currentPosition++) {
            if (input.charAt(currentPosition) == '\"') {
                isInQuotes = !isInQuotes;
            }
            else if (input.charAt(currentPosition) == delimiter && !isInQuotes) {
                tokens.add(input.substring(startPosition, currentPosition));
                startPosition = currentPosition + spacer;
            }
        }
        String lastToken = input.substring(startPosition);
        if (lastToken.equals(",")) {
            tokens.add("");
        } else {
            tokens.add(lastToken);
        }
        return tokens;
    }
    
    public static void listAllSongs(){
        ArrayList<Artist> artists = Artist.getAllArtists();
        ArrayList<Album> novelAlbums = new ArrayList<Album>();
        for (int i=0; i<artists.size(); i++){
            ArrayList<Album> albums = artists.get(i).getAlbums();
            for (int j=0; j<albums.size(); j++){
                boolean novel = true;
                for (int k=0; k<novelAlbums.size(); k++){
                    if (albums.get(j).equals(novelAlbums.get(k))){
                        novel = false;
                        break;
                    }
                }
                if (novel) novelAlbums.add(albums.get(j));
            }
        }
        for (int i=0; i<novelAlbums.size(); i++){
            ArrayList<Song> songs = novelAlbums.get(i).getSongs();
            for (int j=0; j<songs.size(); j++) System.out.println(songs.get(j));
        }
    }
    
    public static void listAllArtists(){
        ArrayList<Artist> artists = Artist.getAllArtists();
        for (int i=0; i<artists.size(); i++){
            System.out.println(artists.get(i));
        }
    }
}