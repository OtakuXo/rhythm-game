package com.rithem.app.pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
      this.setLayout(new GridBagLayout());

      GridBagConstraints gbc = new GridBagConstraints();

      gbc.gridx = 0;
      gbc.insets = new Insets(5, 5, 5, 5);
      

      this.score.setText("your score = " + this.frame.getScore());
      this.score.setForeground(Color.green);
      this.score.setFont(new Font("Serif", Font.PLAIN, 20));

      this.add(score, gbc);
      this.add(home, gbc);
      this.add(stage, gbc);
      this.add(replay, gbc);

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
