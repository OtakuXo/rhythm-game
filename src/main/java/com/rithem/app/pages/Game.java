package com.rithem.app.pages;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rithem.app.GameLoop;
import com.rithem.app.MusicPlayer;
import com.rithem.app.PlayGround;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Game
 */
public class Game extends JPanel {
   private PlayGround playground = new PlayGround();

   public JLabel scoreBoard = new JLabel();
   public long clipTime;
   public MusicPlayer musicPlayer = new MusicPlayer();
   public GameLoop gameLoop = new GameLoop(playground, musicPlayer );

   public Game() {
      this.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            gameLoop.setKey(e.getKeyCode());
         }

         public void keyReleased(KeyEvent e) {
            gameLoop.setKey(0);
         }
      });

      scoreBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
      scoreBoard.setForeground(Color.green);
      scoreBoard.setFont(new Font("Serif", Font.PLAIN, 20));
      scoreBoard.setText("score: " + 0);

      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.setBackground(Color.black);
      this.add(scoreBoard);
      this.add(playground);
      this.setFocusable(true);

   }

   public void stopGame() {
      this.musicPlayer.getClip().stop();
   }

   // not implemented
   public void pauseGame() {
      this.clipTime = this.musicPlayer.getClip().getMicrosecondPosition() / 1000;
      this.musicPlayer.getClip().stop();
   }

   // not implemented
   public void unpauseGame() {
      this.musicPlayer.getClip().start();
      this.musicPlayer.getClip().setMicrosecondPosition(this.clipTime);
   }

   public GameLoop getGameLoop() {
      return gameLoop;
   }

   public void setGameLoop(GameLoop gameLoop) {
      this.gameLoop = gameLoop;
   }

}
