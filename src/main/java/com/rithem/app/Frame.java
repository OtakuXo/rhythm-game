package com.rithem.app;

import javax.swing.*;

import com.rithem.app.pages.Game;
import com.rithem.app.pages.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
   private Game game = new Game();
   private Home home = new Home();
   private String activePanel = "home";

   public Frame() {

      this.swapPanel();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setLocationRelativeTo(null);
      this.setVisible(true);

      home.start.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            activePanel = "game";
            swapPanel();
            game.requestFocusInWindow(); 
            startGame();
         }
      });

   }

   public void swapPanel() {
      if (activePanel == "game") {
         this.remove(home);
         this.add(game);
         revalidate();
         repaint();
      } else {
         this.remove(game);
         this.add(home);
      }
   }

   public void startGame() {
      game.gameLoop.loop(game.scoreBoard);
      game.musicPlayer.getClip().start();
   }


   public void setActivePanel(String activePanel) {
      this.activePanel = activePanel;
   }

   public String getActivePanel() {
      return activePanel;
   }
}
