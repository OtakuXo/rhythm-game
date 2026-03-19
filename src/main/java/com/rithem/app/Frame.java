package com.rithem.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.rithem.app.musics.Music;
import com.rithem.app.pages.Game;
import com.rithem.app.pages.Home;
import com.rithem.app.pages.Result;
import com.rithem.app.pages.StageSelection;

public class Frame extends JFrame {
   // private Home home = new Home(this);
   private Music music;
   // private Game game;
   // private Result result;
   // private StageSelection stage;
   private String score;
   private String activePanel = "home";

   private List<JPanel> panels = new ArrayList<>();

   public Frame() {
      this.swapPanel();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setLocationRelativeTo(null);
      this.setVisible(true);

   }

   public void swapPanel() {
      switch (activePanel) {
         case "game":
            swap(new Game(this, music));
            break;
         case "stage":
            swap(new StageSelection(this));
            break;
         case "home":
            swap(new Home(this));
            break;
         case "result":
            swap(new Result(this));
            break;
      }
   }

   public void swap(JPanel panel) {
      if (!panels.isEmpty()) {
         this.remove(panels.get(0));
      }
      panels.clear();
      panels.add(panel);
      this.add(panels.get(0));
      panels.get(0).requestFocusInWindow();
      revalidate();
      repaint();
   }

   public void setActivePanel(String activePanel) {
      this.activePanel = activePanel;
   }

   public String getActivePanel() {
      return activePanel;
   }

   public void setMusic(Music music) {
      this.music = music;
   }

   public String getScore() {
      return score;
   }

   public void setScore(String score) {
      this.score = score;
   }
}
