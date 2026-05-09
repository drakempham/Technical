import string
import random


class Codec:
    def __init__(self, n):
        self.random_strs = string.ascii_letters + string.digits
        self.length = n
        self.url_to_rep = {}
        self.rep_to_url = {}

    def encode(self, longurl: str) -> str:
        if longurl in self.url_to_rep:
            return self.url_to_rep[longurl]

        rep = "http://tinyurl.com/"

        while True:
            random_rep = rep + \
                "".join([random.choice(self.random_strs) for _ in range(6)])
            if random_rep not in self.rep_to_url:
                rep = random_rep
                break

        self.url_to_rep[longurl] = rep
        self.rep_to_url[rep] = longurl
        return rep

    def decode(self, shorturl: str) -> str:
        return self.rep_to_url[shorturl]

# more simple


class Codec1:
    def __init__(self):
        self.urls = []

    def encode(self, longUrl):
        self.urls.append(longUrl)
        return "https://tinyurl.com/" + str(len(self.urls)-1)

    def decode(self, shortUrl):
        return self.urls[int(shortUrl.split('/')[-1])]


sol = Codec1()

print(string.ascii_letters)
print(string.digits)
short_url = sol.encode("https://leetcode.com/problems/design-tinyurl")
print("short_url: " + short_url)
long_url = sol.decode(short_url)
print("long_url: " + long_url)
