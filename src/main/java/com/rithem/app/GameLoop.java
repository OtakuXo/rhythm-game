
package com.rithem.app;

import javax.swing.JLabel;
import javax.swing.Timer;

import com.rithem.app.musics.Music;
import com.rithem.app.utils.ExpressionIndicator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameLoop {
   public enum State {
      running, paused, stoped
   };

   private Music music;

   private ArrayList<Note> note = new ArrayList<>();
   private ArrayList<ExpressionIndicator> expression = new ArrayList<>();
   private int trackPosition = 0;
   private long clipTimeMs = 0;
   private int score = 0;
   private int speed = 7;
   public State gameState = State.stoped;

   private MusicPlayer musicPlayer;
   private long nextNoteSpawnTime;
   private PlayGround playGround;
   private Note activeNote;
   private Judge judge;
   private int key;

   public GameLoop(PlayGround playGround, MusicPlayer musicPlayer, Music music) {

      this.playGround = playGround;
      this.judge = new Judge(playGround.clickbleRowStart, playGround.clickbleRowEnd);
      this.nextNoteSpawnTime = music.firstNoteSpawnTime;
      this.music = music;
      this.musicPlayer = musicPlayer;
      // this.gameState = State.running;
      musicPlayer.playMusic(music.musicPath);
      // musicPlayer.getClip().start();
   }

   public void loop(JLabel scoreBoard, Frame frame) {
      int delay = 16;

      Timer timer = new Timer(delay, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            if (musicPlayer.getClip().getFrameLength() == musicPlayer.getClip().getFramePosition()) {
               resetGame(e, frame);
            }

            if (gameState == State.stoped || gameState == State.paused) {
               // ((Timer) e.getSource()).stop();
               musicPlayer.getClip().stop();
               checkKeyEvents();
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
      if (clipTimeMs > nextNoteSpawnTime && music.track.length != trackPosition) {
         note.add(new Note(music.track[trackPosition].position, 0));
         nextNoteSpawnTime = clipTimeMs + music.track[trackPosition].beatLengthMs;

         trackPosition++;
      }

      // slide note downward
      for (int i = 0; i < note.size(); i++) {
         note.get(i).setY(note.get(i).getY() + this.speed);
      }

      playGround.setNote(note);
      playGround.setExpression(expression);

      if (!expression.isEmpty()) {
         if (expression.get(0).getStartedAt() + 1000 <= clipTimeMs) {
            expression.remove(0);
         }
      }

      if (!note.isEmpty()) {
         activeNote = note.get(0);
         judge.checkNote(activeNote, key);

         if (activeNote.isMissed()) {
            note.remove(0);
            expression.add(new ExpressionIndicator("missed", clipTimeMs, activeNote.getX(), activeNote.getY()));
         }

         if (activeNote.isClickble()) {
            checkKeyEvents();
            playGround.repaint();
         }
      }

   }

   public void resetGame(ActionEvent e, Frame frame) {
      note.clear();
      clipTimeMs = 0;
      trackPosition = 0;
      score = 0;
      nextNoteSpawnTime = music.firstNoteSpawnTime;
      musicPlayer.getClip().setMicrosecondPosition(0);
      frame.setActivePanel("home");
      frame.swapPanel();
   }

   public void noteClicked() {
      key = 0;
      score = score + activeNote.getScore();
      note.remove(0);
      if (activeNote.getScore() == 5) {
         expression.add(new ExpressionIndicator("mah", clipTimeMs, activeNote.getX(), activeNote.getY()));
         return;
      } else {
         expression.add(new ExpressionIndicator("cool", clipTimeMs, activeNote.getX(), activeNote.getY()));
         return;
      }

   }

   // maybe this should be somewhere else
   public void checkKeyEvents() {
      switch (key) {
         // left
         case 65: // a
         case 37: // arrow left
         case 72: // h
            if (activeNote.getX() == 0 && gameState == State.running) {
               noteClicked();
            }
            break;
         // down
         case 83:// s
         case 40:// arrow down
         case 74:// j
            if (activeNote.getX() == 1 && gameState == State.running) {
               noteClicked();
            }
            break;
         // up
         case 87:// w
         case 38:// arrow up
         case 75:// k
            if (activeNote.getX() == 2 && gameState == State.running) {
               noteClicked();
            }
            break;
         // right
         case 68:// d
         case 39:// arrow right
         case 76:// l
            if (activeNote.getX() == 3 && gameState == State.running) {
               noteClicked();
            }
            break;
         case 32: // space
            if (this.gameState == State.running) {
               key = 0;
               System.out.println(key);
               this.gameState = State.paused;
               return;
            } else {
               key = 0;
               System.out.println(key);
               this.gameState = State.running;
               musicPlayer.getClip().start();
               return;
            }
      }

   }

   public int getKey() {
      return key;
   }

   public void setKey(int key) {
      this.key = key;

   }
}
