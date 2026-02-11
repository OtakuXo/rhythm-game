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
import java.util.Arrays;

class Students {
   char name;
}

public class Frame extends JFrame {
   private boolean running = false;
   private Note arr[] = { new Note(1, 0), new Note(2, -80), new Note(1, -160) };

   private JPanel panel = new JPanel();
   private PlayGround playground = new PlayGround();

   ArrayList<Note> note = new ArrayList<>(Arrays.asList(arr));
   private char key;

   private MusicPlayer musicPlayer = new MusicPlayer();

   public Frame() {
      playground.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            key = e.getKeyChar();
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

   public void gameLoop() {
      int delay = 16;

      Timer timer = new Timer(delay, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            if (!running) {
               ((Timer) e.getSource()).stop();
            }

            update();
            playground.repaint();
         }
      });
      timer.start();
   }

   public void startGame() {
      running = true;
      musicPlayer.playMusic();
      gameLoop();
   }

   public void stopGame() {
      running = false;
   }

   int tmp = 100000;
   public void update() {
      // System.out.println(musicPlayer.clip.getFramePosition());
      if(musicPlayer.clip.getFramePosition() > tmp){
         tmp = musicPlayer.clip.getFramePosition() + 180000;
         note.add(new Note(3, 0));
      }
      playground.setNote(note);
      if (note.isEmpty()) {
         return;
      }
      for (int i = 0; i < note.size(); i++) {
         note.get(i).setY(note.get(i).getY() + note.get(i).getSpeed());
      }
      checkLose();
      checkTileCkicked();
   }

   public void checkTileCkicked() {
      if (note.get(0).getY() + note.get(0).getHeight() >= playground.clickbleRowStart && key == 'a') {
         note.remove(0);
         // musicPlayer.clip.setFramePosition(0);
         key = ' ';
      }
   }

   public void checkLose() {
      if (note.get(0).getY() >= playground.height - playground.tileHeight) {
         stopGame();
      }
   }
}
