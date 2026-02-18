package com.rithem.app.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Home
 */
public class Home extends JPanel {
   public Dimension size = new Dimension(1000,1000);
   public JButton start = new JButton("start");
   public JButton exit = new JButton("exit");


   public Home() {
      this.setBackground(Color.BLACK);
      this.setLayout(new GridBagLayout());
      this.setPreferredSize(size);
      this.setMinimumSize(size);
      this.setMaximumSize(size);
      this.setFocusable(true);

      GridBagConstraints gbc = new GridBagConstraints();

      gbc.gridx = 0;
      gbc.insets = new Insets(5, 5, 5, 5);

      this.add(start, gbc);
      this.add(exit, gbc);

      exit.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e){
            System.exit(0);
         }
      });

   }

}
