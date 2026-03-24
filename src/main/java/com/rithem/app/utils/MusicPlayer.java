package com.rithem.app.utils;

import java.io.InputStream;
import javazoom.jl.player.Player;

public class MusicPlayer {
   private Player musicPlayer;

   public MusicPlayer(String music) {
         try {
            InputStream inputStream = MusicPlayer.class.getClassLoader().getResourceAsStream(music);
            Player player = new Player(inputStream);
            musicPlayer = player;
         } catch (Exception e) {
            System.out.println("hello");
            System.out.println(e);
         }
   }

   public Player getMusicPlayer() {
      return musicPlayer;
   }

   public void setMusicPlayer(Player musicPlayer) {
      this.musicPlayer = musicPlayer;
   }

}
