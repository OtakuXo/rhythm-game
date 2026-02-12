package com.rithem.app;

// import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Frame extends JFrame {
   public enum State {
      running, paused, stoped
   };

   private JPanel panel = new JPanel();
   private PlayGround playground = new PlayGround();

   private Song song[] = { new Song(1, 1000), new Song(2, 2000), new Song(0, 500), new Song(3, 1000) };
   private int arrpos = 0;

   private State gameState = State.stoped;
   private MusicPlayer musicPlayer = new MusicPlayer();
   private long clipTime;
   private long tmp = 0;
   private int speed = 5;

   private ArrayList<Note> note = new ArrayList<>();
   private int key;
   private int score = 0;

   private Judge judge = new Judge(playground.clickbleRowStart, playground.clickbleRowEnd);

   public Frame() {
      playground.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            key = e.getKeyCode();
         }

         public void keyReleased(KeyEvent e) {
            key = ' ';
         }
      });

      panel.setBackground(Color.black);
      panel.add(playground);

      this.add(panel);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setLocationRelativeTo(null);
      this.setVisible(true);

      startGame();
   }

   // gameloop dose gameloop thing
   public void gameLoop() {
      int delay = 16;

      Timer timer = new Timer(delay, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            if (gameState == State.stoped || gameState == State.paused) {
               ((Timer) e.getSource()).stop();
            }

            update();
            playground.repaint();
         }
      });
      timer.start();
   }

   // main logic
   public void update() {

      System.out.println(score);

      // adds notes to note
      if (musicPlayer.clip.getMicrosecondPosition() > tmp) {
         tmp = musicPlayer.clip.getMicrosecondPosition() + song[arrpos].beatLengthMs * 1000;
         note.add(new Note(song[arrpos].position, 0));

         // temperory
         if (song.length == arrpos + 1) {
            arrpos = 0;
            return;
         } else {
            arrpos++;
         }
      }

      playground.setNote(note);
      if (note.isEmpty()) {
         return;
      }

      for (int i = 0; i < note.size(); i++) {
         note.get(i).setY(note.get(i).getY() + this.speed);
      }

      judge.checkNote(note.get(0), key);
      if (note.get(0).isClickble()) {
         checkTileCkicked();
      }
   }

   public void startGame() {
      musicPlayer.playMusic();
      musicPlayer.clip.start();
      gameState = State.running;
      gameLoop();
   }

   public void stopGame() {
      musicPlayer.clip.stop();
      gameState = State.stoped;
   }

   // not implemented
   public void pauseGame() {
      clipTime = musicPlayer.clip.getMicrosecondPosition() / 1000;
      musicPlayer.clip.stop();
      gameState = State.paused;
   }

   // not implemented
   public void unpauseGame() {
      musicPlayer.clip.start();
      musicPlayer.clip.setMicrosecondPosition(clipTime);
      gameState = State.running;
   }

   // this needs to be optimized in another class
   public void checkTileCkicked() {

      if (note.get(0).isMissed()) {
         note.remove(0);
      }

      switch (key) {
         // left
         case 65: // a
         case 37: // arrow left
         case 72: // h
            if (note.get(0).getX() == 0) {
               score = score + note.get(0).getScore();
               key = ' ';
               note.remove(0);
            }
            break;
         // down
         case 83:// s
         case 40:// arrow down
         case 74:// j
            if (note.get(0).getX() == 1) {
               score = score + note.get(0).getScore();
               key = ' ';
               note.remove(0);
            }
            break;
         // up
         case 87:// w
         case 38:// arrow up
         case 75:// k
            if (note.get(0).getX() == 2) {
               score = score + note.get(0).getScore();
               key = ' ';
               note.remove(0);
            }
            break;
         // right
         case 68:// d
         case 39:// arrow right
         case 76:// l
            if (note.get(0).getX() == 3) {
               score = score + note.get(0).getScore();
               key = ' ';
               note.remove(0);
            }
            break;
      }

   }

   // this needs to rework aswell
   // public void checkLose() {
   // if (note.get(0).getY() >= playground.height - playground.tileHeight) {
   // // stopGame();
   // }
   // }
}
