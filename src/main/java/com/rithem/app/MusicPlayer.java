package com.rithem.app;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.*;

public class MusicPlayer {

   private Clip clip;

   public Clip getClip() {
	return clip;
}

   public void setClip(Clip clip) {
	this.clip = clip;
   }

   public void playMusic() {
      try {
         InputStream audioFile =  MusicPlayer.class.getClassLoader().getResourceAsStream("audio/mono.wav");
         InputStream buffer = new BufferedInputStream(audioFile);
         AudioInputStream audioStream = AudioSystem.getAudioInputStream(buffer);

         Clip clip = AudioSystem.getClip();
         clip.open(audioStream);
         this.clip = clip;

         Thread.sleep(1000);
      } catch (Exception e) {
         System.out.println(e);
      }

   }
}
