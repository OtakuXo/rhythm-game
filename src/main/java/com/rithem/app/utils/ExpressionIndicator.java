package com.rithem.app.utils;

/**
 * ExpressionIndicator
 */
public class ExpressionIndicator {

   private String expression;
   private long startedAt;
   private int x = 0;
   private int y = 0;

   public ExpressionIndicator(String expression, long startedAt, int x, int y) {
      this.expression = expression;
      this.startedAt = startedAt;
      this.x = x;
      this.y = y;
   }

   public String getExpression() {
      return expression;
   }

   public long getStartedAt() {
      return startedAt;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public void setExpression(String expression) {
      this.expression = expression;
   }

   public void setStartedAt(long startedAt) {
      this.startedAt = startedAt;
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setY(int y) {
      this.y = y;
   }
}
