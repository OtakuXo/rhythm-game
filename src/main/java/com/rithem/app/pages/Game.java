package com.rithem.app.pages;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rithem.app.Frame;
import com.rithem.app.GameLoop;
// import com.rithem.app.MusicPlayer;
import com.rithem.app.PlayGround;
import com.rithem.app.GameLoop.State;
import com.rithem.app.musics.Music;
import com.rithem.app.utils.MusicPlayer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel {
   private PlayGround playground = new PlayGround();
   private JLabel health = new JLabel();

   // i need to make them private in future
   public JLabel scoreBoard = new JLabel();
   public MusicPlayer musicPlayer = new MusicPlayer();;
   public GameLoop gameLoop;
   // public long clipTime;
   public Frame parentFrame;
   public MusicPlayer clickSound = new MusicPlayer();

   public Game(Frame frame, Music music) {
      this.gameLoop = new GameLoop(playground, musicPlayer, music);
      this.parentFrame = frame;
      // clickSound.playMusic("audio/click3.wav");
      this.requestFocusInWindow();

      this.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            if (gameLoop.getKey() == e.getKeyCode()) {
              return; 
            }
            gameLoop.setKey(e.getKeyCode());

            // clickSound.getClip().stop();
            // clickSound.getClip().setFramePosition(0);
            // clickSound.getClip().start();
         }

         public void keyReleased(KeyEvent e) {
            gameLoop.setKey(0);
         }
      });

      scoreBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
      scoreBoard.setForeground(Color.green);
      scoreBoard.setFont(new Font("Serif", Font.PLAIN, 20));
      scoreBoard.setText("score: " + 0);

      health.setAlignmentX(Component.CENTER_ALIGNMENT);
      health.setForeground(Color.green);
      health.setFont(new Font("Serif", Font.PLAIN, 20));
      health.setText("health: " + 0);

      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      this.setBackground(Color.black);
      this.add(scoreBoard);
      this.add(health);
      this.add(playground);
      this.setFocusable(true);
      this.startGame();

   }

   public void startGame() {
      // music.checkTrack();
      gameLoop.gameState = State.running;
      gameLoop.loop(scoreBoard, health, this.parentFrame);
      // musicPlayer.getClip().start();
   }

   public void stopGame() {
      // this.musicPlayer.getClip().stop();
      this.parentFrame.setScore(this.scoreBoard.getText());
   }

   // not implemented
   public void pauseGame() {
      // this.musicPlayer.getClip().stop();
   }

   // not implemented
   public void unpauseGame() {
      // this.musicPlayer.getClip().start();
      // this.musicPlayer.getClip().setMicrosecondPosition(this.clipTime);
   }

   public GameLoop getGameLoop() {
      return gameLoop;
   }

   public void setGameLoop(GameLoop gameLoop) {
      this.gameLoop = gameLoop;
   }

   public JLabel getHealth() {
      return health;
   }

   public void setHealth(JLabel health) {
      this.health = health;
   }
}
