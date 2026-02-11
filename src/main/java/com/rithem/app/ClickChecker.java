package com.rithem.app;

/**
 * ClickChecker
 */
public class ClickChecker {

   private int clickableRowStart;
   private int clickableRowEnd;
   private int key;
   private Note note;

   ClickChecker(int clcikableRowStart, int clickableRowEnd, int key, Note note){
      this.clickableRowStart = clcikableRowStart;
      this.clickableRowEnd = clickableRowEnd;
      this.key = key;
      this.note = note;
   }   

   public void checkTop(){}
   public void checkBottom(){}
   public void checkKeyPressed(){}
}
