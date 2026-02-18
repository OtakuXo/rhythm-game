package com.rithem.app;

// 91sec
//
class NotePosition {
   int position;
   int beatLengthMs;// miliseconds

   public NotePosition(int position, int clipTimeMs) {
      this.position = position;
      this.beatLengthMs = clipTimeMs;
   }
}

class Song {
   int firstNoteSpawnTime;
   NotePosition track[];

   public Song(int firstNoteSpawnTime, NotePosition track[]) {
      this.firstNoteSpawnTime = firstNoteSpawnTime;
      this.track = track;
   }
}

class Songs {
   private NotePosition monogatariOp4Notes[] = { new NotePosition(1, 500), new NotePosition(0, 500),
         new NotePosition(3, 500), new NotePosition(2, 500), new NotePosition(0, 500), new NotePosition(1, 500),
         new NotePosition(3, 500), new NotePosition(2, 500), new NotePosition(0, 500), new NotePosition(1, 500),
         new NotePosition(3, 500), new NotePosition(2, 500), new NotePosition(0, 500), new NotePosition(1, 500),
         new NotePosition(3, 500), new NotePosition(2, 500), new NotePosition(0, 500), new NotePosition(1, 500),
         new NotePosition(3, 500), new NotePosition(2, 500), new NotePosition(0, 500), new NotePosition(1, 500),
         new NotePosition(3, 500), new NotePosition(2, 500), new NotePosition(0, 500), new NotePosition(1, 500),
         new NotePosition(3, 500), new NotePosition(2, 500), new NotePosition(0, 500), new NotePosition(1, 500),
         new NotePosition(3, 500), new NotePosition(2, 500), new NotePosition(0, 500), new NotePosition(1, 500),
         new NotePosition(0, 500) };
   public Song monogatariOp4 = new Song(1900, monogatariOp4Notes);
}
// monogatri openinig 4
