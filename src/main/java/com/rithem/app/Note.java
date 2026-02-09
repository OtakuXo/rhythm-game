package com.rithem.app;

/**
 * Note
 */
public class Note {
   private final int width = 79;
   private final int height = 80;
   private int x = 0;
   private int y = 0;
   private int speed = 2;

   Note(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public int getWidth() {
      return width;
   }

   public int getHeight() {
      return height;
   }

   public int getX() {
      return x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public int getSpeed() {
      return speed;
   }

   public void setSpeed(int speed){
      this.speed = speed;
   }


}
