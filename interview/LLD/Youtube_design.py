class SongNode:
    def __init__(self, songID=0, songName=""):
        self.song_id = songID
        self.song_name = songName
        self.next = None
        self.prev = None


class YoutubePlayList:
    def __init__(self):
        self.song_map = {}
        self.head = SongNode()
        self.tail = SongNode()
        self.head.next = self.tail
        self.tail.prev = self.head
        self.head.prev = None
        self.tail.next = None
        self.curr_song = None
        self.total_song = 0

    def addSong(self, songID: int, songName: str):
        if songID not in self.song_map:
            new_song = SongNode(songID, songName)
            last_song = self.tail.prev

            last_song.next = new_song
            new_song.prev = last_song

            new_song.next = self.tail
            self.tail.prev = new_song

            self.song_map[songID] = new_song
            self.total_song += 1

            if self.curr_song is None:
                self.curr_song = new_song

    def deleteSong(self, songID: int):
        if songID in self.song_map:
            node = self.song_map[songID]

            if self.curr_song == node:
                if node.next != self.tail:
                    self.curr_song = self.curr_song.next
                elif node.prev != self.head:
                    self.curr_song = self.curr_song.prev
                else:
                    self.curr_song = None

            prev_song = node.prev
            next_song = node.next

            prev_song.next = next_song
            next_song.prev = prev_song
            del self.song_map[songID]
            self.total_song -= 1
            return True
        return False

    def moveNext(self):
        if self.curr_song and self.curr_song.next != self.tail:
            self.curr_song = self.curr_song.next
        return self.curr_song

    def moveBack(self):
        if self.curr_song and self.curr_song.prev != self.head:
            self.curr_song = self.curr_song.prev

        return self.curr_song

    def moveByKPositions(self, k: int):
        if self.total_song == 0 or self.curr_song is None:
            return None

        while k > 0:
            if self.curr_song.next != self.tail:
                self.curr_song = self.curr_song.next
            else:
                break
            k -= 1

        while k < 0:
            if self.curr_song.prev != self.head:
                self.curr_song = self.curr_song.prev
            else:
                break
            k += 1
        return self.curr_song
