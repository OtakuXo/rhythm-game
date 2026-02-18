
package com.rithem.app;

import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameLoop {
   public enum State {
      running, paused, stoped
   };

   private Song song = new Songs().monogatariOp4;

   private ArrayList<Note> note = new ArrayList<>();
   private int trackPosition = 0;

   private MusicPlayer musicPlayer;
   private long clipTimeMs;
   private State gameState = State.stoped;
   // private int bpm = 120;
   // private int beatLength = 60/bpm;
   private int speed = 10;
   private long nestNoteSpawnTime;
   // private double timeTaken;
   private int score = 0;

   private PlayGround playGround;
   private Note activeNote;
   private Judge judge;
   private int key;

   public GameLoop(PlayGround playGround, MusicPlayer musicPlayer) {
      this.playGround = playGround;
      this.judge = new Judge(playGround.clickbleRowStart, playGround.clickbleRowEnd);
      this.nestNoteSpawnTime = song.firstNoteSpawnTime;
      this.musicPlayer = musicPlayer;
      this.gameState = State.running;
      musicPlayer.playMusic();
   }

   public void loop(JLabel scoreBoard) {
      int delay = 16;

      Timer timer = new Timer(delay, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if(musicPlayer.getClip().getFrameLength() == musicPlayer.getClip().getFramePosition()){

            }

            if (gameState == State.stoped || gameState == State.paused) {
               ((Timer) e.getSource()).stop();
               return;
            }

            update(scoreBoard);
            playGround.repaint();

         }
      });
      timer.start();
   }

   public void update(JLabel scoreBoard) {

      scoreBoard.setText("score: " + this.score);

      // adds note
      clipTimeMs = musicPlayer.getClip().getMicrosecondPosition() / 1000;
      if (clipTimeMs > nestNoteSpawnTime) {
         note.add(new Note(song.track[trackPosition].position, 0));
         nestNoteSpawnTime = clipTimeMs + song.track[trackPosition].beatLengthMs;

         // temporary
         if (song.track.length == trackPosition + 1) {
            trackPosition = 0;
         } else {
            trackPosition++;
         }
      }

      for (int i = 0; i < note.size(); i++) {
         note.get(i).setY(note.get(i).getY() + this.speed);
      }
      playGround.setNote(note);

      if (!note.isEmpty()) {
         activeNote = note.get(0);
         judge.checkNote(activeNote, key);

         if (activeNote.isClickble()) {
            checkTileCkicked();
            playGround.repaint();
         }
      }

   }

   public void checkTileCkicked() {
      if (activeNote.isMissed()) {
         note.remove(0);
      }

      switch (key) {
         // left
         case 65: // a
         case 37: // arrow left
         case 72: // h
            if (activeNote.getX() == 0) {
               key = ' ';
               score = score + activeNote.getScore();
               note.remove(0);
            }
            break;
         // down
         case 83:// s
         case 40:// arrow down
         case 74:// j
            if (activeNote.getX() == 1) {
               key = ' ';
               score = score + activeNote.getScore();
               note.remove(0);
            }
            break;
         // up
         case 87:// w
         case 38:// arrow up
         case 75:// k
            if (activeNote.getX() == 2) {
               key = ' ';
               score = score + activeNote.getScore();
               note.remove(0);
            }
            break;
         // right
         case 68:// d
         case 39:// arrow right
         case 76:// l
            if (activeNote.getX() == 3) {
               key = ' ';
               score = score + activeNote.getScore();
               note.remove(0);
            }
            break;
      }
   }

   public int getKey() {
      return key;
   }

   public void setKey(int key) {
      this.key = key;

   }
}
