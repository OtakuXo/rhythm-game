package com.rithem.app;

import java.nio.channels.Pipe.SourceChannel;
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

   // private PlayMusic tmp = new PlayMusic();

   public Frame() {
      this.swapPanel();
      this.pack();
      this.setLocationRelativeTo(null);
      this.setVisible(true);

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // System.out.println(tmp.player.getMusicPlayer().getPosition());

   }

   public void swapPanel() {
      System.out.println(panels.size());
      switch (activePanel) {
         case "game":
            panels.add(new Game(this, music));
            swap();
            break;
         case "stage":
            panels.add(new StageSelection(this));
            swap();
            break;
         case "home":
            panels.add(new Home(this));
            swap();
            // swap(new Home(this));
            break;
         case "result":
            panels.add(new Result(this));
            swap();
            // swap(new Result(this));
            break;
      }
   }

   public void swap() {
      // if statement is to prevent crash at start
      // i am unsure if the instances of jpanels are being removed properly
      System.out.println(panels.size());
      if (panels.size() >= 2) {
         this.remove(panels.get(0));
         panels.remove(0);
      }

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
