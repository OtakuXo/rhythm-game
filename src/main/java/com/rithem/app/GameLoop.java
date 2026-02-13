
package com.rithem.app;

import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GameLoop
 */
public class GameLoop {
   public enum State {
      running, paused, stoped
   };

   private Song song[] = { new Song(1, 1000), new Song(2, 2000), new Song(0, 500), new Song(3, 1000) };
   private int arrPosition = 0;
   private ArrayList<Note> note = new ArrayList<>();

   private MusicPlayer musicPlayer;
   private long clipTimeMs;
   private State gameState = State.stoped;
   private int speed = 5;
   private long tmp = 0;
   private int score = 0;

   private PlayGround playGround;
   private Note activeNote;
   private Judge judge;
   private int key;

   public GameLoop(PlayGround playGround, MusicPlayer musicPlayer) {
      this.playGround = playGround;
      judge = new Judge(playGround.clickbleRowStart, playGround.clickbleRowEnd);
      this.musicPlayer = musicPlayer;
      musicPlayer.playMusic();
      this.gameState = State.running;
   }

   public void loop(JLabel scoreBoard) {
      int delay = 16;

      Timer timer = new Timer(delay, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
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
      // adds notes to note
      scoreBoard.setText("score: "+this.score);
      clipTimeMs = musicPlayer.clip.getMicrosecondPosition() / 1000;
      if (clipTimeMs > tmp) {
         tmp = clipTimeMs + song[arrPosition].beatLengthMs;
         note.add(new Note(song[arrPosition].position, 0));

         if (song.length == arrPosition + 1) {
            arrPosition = 0;
            return;
         } else {
            arrPosition++;
         }
      }

      playGround.setNote(note);

      for (int i = 0; i < note.size(); i++) {
         note.get(i).setY(note.get(i).getY() + this.speed);
      }

      if (!note.isEmpty()) {
         activeNote = note.get(0);
         judge.checkNote(activeNote, key);

         if (activeNote.isClickble()) {
            checkTileCkicked();
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
               score = score + activeNote.getScore();
               key = ' ';
               note.remove(0);
            }
            break;
         // down
         case 83:// s
         case 40:// arrow down
         case 74:// j
            if (activeNote.getX() == 1) {
               score = score + activeNote.getScore();
               key = ' ';
               note.remove(0);
            }
            break;
         // up
         case 87:// w
         case 38:// arrow up
         case 75:// k
            if (activeNote.getX() == 2) {
               score = score + activeNote.getScore();
               key = ' ';
               note.remove(0);
            }
            break;
         // right
         case 68:// d
         case 39:// arrow right
         case 76:// l
            if (activeNote.getX() == 3) {
               score = score + activeNote.getScore();
               key = ' ';
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
