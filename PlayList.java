/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if (this.size<this.maxSize) // if this condition is true, there is space available in the list to add the track.
        {
        tracks[this.size]=track;
        this.size++;
        return true;
        }
        else 
        {
        return false;
        }
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        StringBuilder dataToList = new StringBuilder();
        for (int i=0; i<=this.size; i++)
        {
          dataToList.append(tracks[i].toString()); 
          dataToList.append("\n");
        }
        String result = dataToList.toString();  
        return result;
    }


    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        if (this.size!=0)
        {
        this.size=this.size-1; 
        tracks[this.size]=null;
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int totalSecounds = 0;
        for (int i=0; i<this.size; i++)
        {
            totalSecounds = totalSecounds + tracks[i].getDuration();
        }
        return totalSecounds;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) 
    {
    title = title.toLowerCase();
    for (int i=0; i<this.size; i++)
       {
        if ( title == tracks[i].getTitle().toLowerCase())
        {
            return i;
        }
       }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
       boolean add =false;
        if (i<0 || i>this.maxSize || this.size==this.maxSize)
        {
        return add;
        }
        else if (this.size==0 && i==0)
        {
            tracks[0]=track;
            this.size++;
            add = true;
        } 
        else if (i==this.size && this.size<=this.maxSize+1)
        {
            tracks[this.size]=track;
            this.size++;
            add = true;
        }
        else if (this.size<=this.maxSize+1)
        {
        for(int j=this.size; j>i ;j--)
        {
        tracks[j]=tracks[j-1];
        }
        tracks[i]=track;
        this.size++;
        add=true;
        }
        return add;
    }
    
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        if (this.size!=0 && i>=0 && i<this.maxSize)
        {
        // Shift elements to the left to fill the gap created by removing the track
           for(int j=i;j<this.size;j++)
           {
            tracks[j]=tracks[j+1];        
           }  
           tracks[this.size-1]=null;
           this.size--;
        }
        }
    

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        int index = this.indexOf(title);
        if (index != -1) 
        {
            this.remove(index);
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        tracks[0]=null;
        for (int j=0; j<this.size; j++)
        {
            tracks[j]=tracks[j+1];
        }
        tracks[this.size]=null;
        this.size--;
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        if (this.size + other.size > this.maxSize){
        }
        else
        {
            for (int i=0; i<=other.size; i++)
            tracks[this.size]=other.tracks[i]; // how to use it?
            this.size++;
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        int minimum = start;
        if (start<0 || start>this.size){
            minimum=-1;
        }
        else {
            for (int i = start; i<this.size; i++)
            if (tracks[i].getDuration()<start)
            {
                minimum = tracks[i].getDuration();
            }
        }
        return minimum;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        for (int i = 0; i < this.size - 1; i++) {
            int minimum = minIndex(i);
            if (i != minimum) {
                Track temp = tracks[i];
                tracks[i] = tracks[minimum];
                tracks[minimum] = temp;
            }
        }
    }
}
