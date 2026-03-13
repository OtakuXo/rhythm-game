package com.rithem.app;

import javax.swing.*;

import com.rithem.app.musics.Music;
import com.rithem.app.musics.musicfiles.Monogatari;
import com.rithem.app.pages.Game;
import com.rithem.app.pages.Home;
import com.rithem.app.pages.StageSelection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame extends JFrame {
   private Home home = new Home(this);
   private Music music;
   private Game game;
   private StageSelection stage;
   private String activePanel = "home";

   public Frame() {
      this.swapPanel();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setLocationRelativeTo(null);
      this.setVisible(true);

      // home.start.addActionListener(new ActionListener() {
      //    public void actionPerformed(ActionEvent e) {
      //       activePanel = "stage";
      //       swapPanel();
      //       game.requestFocusInWindow();
      //       // game.startGame();
      //    }
      // });

      // stage.back.addActionListener(new ActionListener() {
      //    public void actionPerformed(ActionEvent e) {
      //       activePanel = "home";
      //       swapPanel();
      //       game.requestFocusInWindow();
      //       // game.startGame();
      //    }
      // });

   }

   public void swapPanel() {
      switch (activePanel) {
         case "game":
            game = new Game(this, music);
            this.remove(stage);
            this.add(game);
            game.requestFocusInWindow();
            revalidate();
            repaint();
            game.startGame();
            break;
         case "stage":
            stage = new StageSelection(this);
            this.remove(home);
            this.add(stage);
            revalidate();
            repaint();
            break;
         case "home":
            // game.musicPlayer.getClip().stop();
            // this.remove(stage);
            this.add(home);
            revalidate();
            repaint();
            break;
      }
   }

   public void setActivePanel(String activePanel) {
      this.activePanel = activePanel;
   }

   public String getActivePanel() {
      return activePanel;
   }

   public void setMusic(Music music){
      this.music = music;
   }
}
