# Spotify Database Searcher

A program I wrote in Java for my AP Comp Sci class. After providing it a Spotify playlist file, it will parse it and let you search for different attributes within the playlist.

Nicknamed "Sorenify" after the one and only Soren Troffkin who helped make this program.\
Special thanks to Holden McNeil for his additional help in making this program as well!

## Obtaining a CSV file for your Spotifiy playlist
You can get your own Spotify playlist file using [exportify](https://watsonbox.github.io/exportify/)\
Make sure to check "Include artists data" in the settings, otherwise this program will not be able to parse the file!

### Adding your playlist to the database
Add your CSV file to the playlists directory, and add its filename to a new line on the sources.csv file.

## Using the program
After first launching the program, it will import every song from the playlists provided in the sources.csv file.\
The following can be searched for in the database

1. Artists (Alphabetically)
2. Albums (Alphabetically)
3. Songs (Alphabetically)
4. Genres (Alphabetically)
5. Songs sorted by Popularity (A metric provided by Spotify)
6. Songs sorted by Duration
7. Songs sorted by Date released

## Special note regarding dates
Spotify doesn't always have access to accurate data on the date a song/album was published. This means that occasionally the date data within the database may be incorrect. Unfortunately, there is no easy way to solve this problem, so please acknowledge that the "date released" attribute may be incorrect occasionally.
