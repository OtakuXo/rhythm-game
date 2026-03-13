package com.rithem.app.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.rithem.app.Frame;
import com.rithem.app.musics.Music;
import com.rithem.app.musics.musicfiles.LukaLuka;
import com.rithem.app.musics.musicfiles.Monogatari;

/**
 * StageSelection
 */
public class StageSelection extends JPanel implements ActionListener {
   private Frame frame;
   public Dimension size = new Dimension(1000, 1000);

   public JButton start = new JButton("start");
   public JButton back = new JButton("back");

   private List<JButton> buttonList = new ArrayList<>();

   public Music selectedMusic;
   public Music musicList[] = { new Monogatari().music, new LukaLuka().music };

   public StageSelection(Frame frame) {
      this.frame = frame;
      this.setBackground(Color.BLACK);
      this.setLayout(new GridBagLayout());
      this.setPreferredSize(size);
      this.setMinimumSize(size);
      this.setMaximumSize(size);
      this.setFocusable(true);

      GridBagConstraints gbc = new GridBagConstraints();

      gbc.gridx = 0;
      gbc.insets = new Insets(5, 5, 5, 5);

      back.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            frame.setActivePanel("Home");
            frame.swapPanel();
         }
      });

      for (int i = 0; i < musicList.length; i++) {
         JButton button = new JButton(musicList[i].name);
         button.addActionListener(this);
         button.setActionCommand(String.valueOf(i));

         buttonList.add(button);
         this.add(button, gbc);
      }
      this.add(start, gbc);
      this.add(back, gbc);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();
      frame.setMusic(musicList[Integer.parseInt(command)]);
      frame.setActivePanel("game");
      frame.swapPanel();
   }

}
