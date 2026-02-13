package com.rithem.app;

// import java.awt.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame {
   private JPanel panel = new JPanel();
   private PlayGround playground = new PlayGround();
   private JLabel scoreBoard = new JLabel(); 

   private MusicPlayer musicPlayer = new MusicPlayer();
   private long clipTime;
   private GameLoop gameLoop = new GameLoop(playground, musicPlayer);
   int score = 0;

   public Frame() {

      playground.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            gameLoop.setKey(e.getKeyCode());
         }

         public void keyReleased(KeyEvent e) {
            gameLoop.setKey(0);
         }
      });


      scoreBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
      scoreBoard.setForeground(Color.green);
      scoreBoard.setFont(new Font("Serif", Font.PLAIN, 20));
      scoreBoard.setText("score: "+score);

      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      panel.setBackground(Color.black);
      panel.add(scoreBoard);
      panel.add(playground);


      this.add(panel);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setLocationRelativeTo(null);
      this.setVisible(true);

      startGame();
   }



   public void startGame() {
      gameLoop.loop(scoreBoard);
   }

   public void stopGame() {
      musicPlayer.clip.stop();
   }

   // not implemented
   public void pauseGame() {
      clipTime = musicPlayer.clip.getMicrosecondPosition() / 1000;
      musicPlayer.clip.stop();
   }

   // not implemented
   public void unpauseGame() {
      musicPlayer.clip.start();
      musicPlayer.clip.setMicrosecondPosition(clipTime);
   }
}
