package com.rithem.app.pages;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rithem.app.Frame;

/**
 * Result
 */
public class Result extends JPanel {
   private Frame frame;
   private JButton home = new JButton("home");
   private JButton replay = new JButton("replay");
   private JButton stage = new JButton("stage");
   private JLabel score = new JLabel();
   
   public Result(Frame frame){
      this.frame = frame;
      this.setBackground(Color.BLACK);
      this.setFocusable(true);

      this.score.setText(this.frame.getScore());

      this.add(score);
      this.add(home);
      this.add(stage);
      this.add(replay);

      stage.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e){
            frame.setActivePanel("stage");
            frame.swapPanel();
         }
      });
      home.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e){
            frame.setActivePanel("home");
            frame.swapPanel();
         }
      });
      replay.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e){
            frame.setActivePanel("game");
            frame.swapPanel();
         }
      });
   }
   
}
