package com.rithem.app;

class Song {
   int position;
   int beatLengthMs;// miliseconds

   public Song(int position, int clipTimeMs) {
      this.position = position;
      this.beatLengthMs = clipTimeMs;
   }
}

