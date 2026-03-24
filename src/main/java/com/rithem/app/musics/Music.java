package com.rithem.app.musics;

public class Music {
   public long musicLength;// i cant get duration of mp3 files i am stupid
   public int firstNoteSpawnTime;
   public NotePosition track[];
   public String name;
   public String musicPath;

   private int tmp;

   public Music(String name, String musicPath, long songLength, int firstNoteSpawnTime, NotePosition track[]) {
      this.name = name;
      this.musicLength = songLength;
      this.firstNoteSpawnTime = firstNoteSpawnTime;
      this.track = track;
      this.musicPath = musicPath;

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

