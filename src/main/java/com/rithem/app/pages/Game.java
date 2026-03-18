package com.rithem.app.pages;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rithem.app.Frame;
import com.rithem.app.GameLoop;
import com.rithem.app.MusicPlayer;
import com.rithem.app.PlayGround;
import com.rithem.app.GameLoop.State;
import com.rithem.app.musics.Music;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel {
   private Music music;
   private PlayGround playground = new PlayGround();

   // i need to make them private in future
   public JLabel scoreBoard = new JLabel();
   public MusicPlayer musicPlayer = new MusicPlayer();
   public GameLoop gameLoop;
   public long clipTime;
   public Frame parentFrame;
   public MusicPlayer clickSound = new MusicPlayer();

   public Game(Frame frame, Music music) {
      this.music = music;
      this.gameLoop = new GameLoop(playground, musicPlayer, music);
      this.parentFrame = frame;
      clickSound.playMusic("audio/click3.wav");

      this.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            if (gameLoop.getKey() == e.getKeyCode()) {
              return; 
            }
            gameLoop.setKey(e.getKeyCode());

            clickSound.getClip().stop();
            clickSound.getClip().setFramePosition(0);
            clickSound.getClip().start();
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

   public void startGame() {
      music.checkTrack();
      gameLoop.gameState = State.running;
      gameLoop.loop(scoreBoard, this.parentFrame);
      musicPlayer.getClip().start();
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
