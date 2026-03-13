package com.rithem.app.musics;

/**
 * NotePosition
 */

public class NotePosition {
   public int position;
   public int beatLengthMs;// miliseconds

   public NotePosition(int position, int clipTimeMs) {
      this.position = position;
      this.beatLengthMs = clipTimeMs;
   }
}
