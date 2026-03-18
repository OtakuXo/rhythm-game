package com.rithem.app;

/**
 * ClickChecker
 */
public class Judge {

   private int clickableRowStart;
   private int clickableRowEnd;

   Judge(int clcikableRowStart, int clickableRowEnd) {
      this.clickableRowStart = clcikableRowStart;
      this.clickableRowEnd = clickableRowEnd;
   }

   public void checkNote(Note note, int key) {
      checkMissed(note);
      if (note.getY() + note.getHeight() >= this.clickableRowStart && note.getY() <= this.clickableRowEnd) {
         note.setClickble(true);
         ScoreTheNote(note);
      }
   }

   private void checkMissed(Note note) {
      if (note.getY() > this.clickableRowEnd - 1) {
         note.setMissed(true);
      }
   }

   private void ScoreTheNote(Note note) {
      if (note.getY() + note.getHeight() < this.clickableRowEnd + 20 && note.getY() > this.clickableRowStart - 20) {
         note.setScore(10);
      } else {
         note.setScore(5);
      }
   }

}
