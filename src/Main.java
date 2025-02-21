public class Main{
    public static void main(String[] args) {
        
        MusicBox mbox = new MusicBox("S34TG65", 'Y', "Raindrops", "Misty", 
        "The path less traveled", "Country", "BZEE Music", "Rhythm Divine");

        System.out.println("Now playing the song");
        mbox.playSong(mbox.getSongID(), mbox.getPremiumSong(), 2);
        
        MusicBox anotherMbox = new MusicBox("S78XY90", 'N', "Sunshine Days", "Sunny",
        "Happy Vibes", "Pop", "Star Studio", "Melody Magic");
        System.out.println("Now playing another song");
        anotherMbox.playSong(anotherMbox.getSongID(), anotherMbox.getPremiumSong(), 1);

    }
}




