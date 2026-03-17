package com.rithem.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.rithem.app.utils.ExpressionIndicator;

public class PlayGround extends JPanel {

   final int tileWidth = 90;
   final int tileHeight = 90;
   final int rows = 10;
   final int cols = 4;
   final int width = tileWidth * cols;
   final int height = tileHeight * rows;
   final Dimension playgroundSize = new Dimension(width, height);

   final int clickbleRow = 9;
   final int clickbleRowStart = tileHeight * (clickbleRow - 1);
   final int clickbleRowEnd = tileHeight * clickbleRow;
   private Image arrowRight;
   private Image arrowLeft;
   private Image arrowUp;
   private Image arrowDown;

   private Image missed;
   private Image mah;
   private Image cool;

   private ArrayList<Note> note = new ArrayList<>();
   private ArrayList<ExpressionIndicator> expressions = new ArrayList<>();

   public PlayGround() {
      this.setPreferredSize(playgroundSize);
      this.setMinimumSize(playgroundSize);
      this.setMaximumSize(playgroundSize);
      this.setFocusable(true);
      this.requestFocusInWindow(true);

      try {
         arrowRight = ImageIO.read(getClass().getResourceAsStream("/images/arrowRight.png"));
         arrowLeft = ImageIO.read(getClass().getResourceAsStream("/images/arrowLeft.png"));
         arrowDown = ImageIO.read(getClass().getResourceAsStream("/images/arrowDown.png"));
         arrowUp = ImageIO.read(getClass().getResourceAsStream("/images/arrowUp.png"));
         missed = ImageIO.read(getClass().getResourceAsStream("/images/missed.png"));
         mah = ImageIO.read(getClass().getResourceAsStream("/images/mah.png"));
         cool = ImageIO.read(getClass().getResourceAsStream("/images/cool.png"));
      } catch (Exception e) {
         System.out.println("hello");
      }
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
         switch (note.get(i).getX()) {
            case 0:
               g2D.drawImage(arrowLeft, note.get(i).getX() * tileWidth, note.get(i).getY(), null);
               break;
            case 1:
               g2D.drawImage(arrowDown, (note.get(i).getX() * tileWidth) + 5, note.get(i).getY(), null);
               break;
            case 2:
               g2D.drawImage(arrowUp, (note.get(i).getX() * tileWidth) + 5, note.get(i).getY(), null);
               break;
            case 3:
               g2D.drawImage(arrowRight, note.get(i).getX() * tileWidth, note.get(i).getY(), null);
               break;
         }

      }

      for (int i = 0; i < expressions.size(); i++) {
         switch (expressions.get(i).getExpression()) {
            case "missed":
               g2D.drawImage(missed, expressions.get(i).getX() * tileWidth, expressions.get(i).getY(), null);
               break;
            case "mah":
               g2D.drawImage(mah, expressions.get(i).getX() * tileWidth, expressions.get(i).getY(), null);
               break;
            case "cool":
               g2D.drawImage(cool, expressions.get(i).getX() * tileWidth, expressions.get(i).getY(), null);
               break;
         }

      }

   }

   public List<Note> getNote() {
      return this.note;
   }

   public void setNote(List<Note> note) {
      this.note.clear();
      this.note.addAll(note);
   }

   public void setExpression(List<ExpressionIndicator> expression) {
      this.expressions.clear();
      this.expressions.addAll(expression);
   }
}
