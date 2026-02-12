package com.rithem.app;

/**
 * Note
 */
public class Note {
   private final int width = 79;
   private final int height = 80;
   private int x = 0;
   private int y = 0;
   private int score = 5;
   private boolean clickble = false;
   private boolean missed = false;


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

   public int getScore() {
      return score;
   }

   public void setScore(int speed) {
      this.score = speed;
   }

   public boolean isClickble() {
      return clickble;
   }

   public void setClickble(boolean clickble) {
      this.clickble = clickble;
   }

   public boolean isMissed() {
	return missed;
}

   public void setMissed(boolean missed) {
	this.missed = missed;
   }

}
