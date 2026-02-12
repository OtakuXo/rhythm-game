package com.rithem.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PlayGround extends JPanel{

   final int tileWidth = 80;
   final int tileHeight = 80;
   final int rows = 10;
   final int cols = 4;
   final int width = tileWidth * cols;
   final int height = tileHeight * rows;
   final Dimension playgroundSize = new Dimension(width, height);

   final int clickbleRow = 9;
   final int clickbleRowStart = tileHeight * (clickbleRow - 1);
   final int clickbleRowEnd = tileHeight * clickbleRow;

   // private Note note[] = { new Note(1, 0), new Note(2, -80), new Note(1, -160) };
   private ArrayList<Note> note = new ArrayList<>();

   public PlayGround() {
      this.setPreferredSize(playgroundSize);
      this.setMinimumSize(playgroundSize);
      this.setMaximumSize(playgroundSize);
      this.setFocusable(true);
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponents(g);

      Graphics2D g2D = (Graphics2D) g;

      g2D.setBackground(Color.decode("#000"));
      g2D.fillRect(0, 0, width, height);

      g2D.setColor(Color.gray);
      for (int i = 1; i <= 3; i++) {
         g2D.drawLine(i * tileWidth, 0, i * tileWidth, height);
      }
      g2D.drawLine(0, height - tileHeight, width, height - tileHeight);
      g2D.drawLine(0, height - 2 * tileHeight, width, height - 2 * tileHeight);

      for (int i = 0; i < note.size(); i++) {
         g2D.setColor(Color.red);
         g2D.fillRect((note.get(i).getX() * tileWidth) + 2, note.get(i).getY(), note.get(i).getWidth(), note.get(i).getHeight());
      }
   }
   
   public List<Note> getNote(){
      return this.note;
   }

   public void setNote(List<Note> note){
      this.note.clear();
      this.note.addAll(note);
   }
}
