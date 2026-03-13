package com.rithem.app.musics;

public class Music {
   public long musicLength;
   public int firstNoteSpawnTime;
   public NotePosition track[];
   public String name;

   private int tmp;

   public Music(String name, long songLength, int firstNoteSpawnTime, NotePosition track[]) {
      this.name = name;
      this.musicLength = songLength;
      this.firstNoteSpawnTime = firstNoteSpawnTime;
      this.track = track;

      System.out.println(tmp);
   }

   public void checkTrack() {
      for (int i = 0; i < track.length; i++) {
        tmp = tmp + track[i].beatLengthMs;
      }
      if (tmp < musicLength - 5000) {
         System.out.println("this music has problem");
         System.out.println("notes for "+ tmp + " length has been added");
      }
   }
}

// class Songs {
// public Song monogatariOp4 = new Song(91508004 / 1000, 2900,
// monogatariOp4Notes);
// }
// monogatri openinig 4
