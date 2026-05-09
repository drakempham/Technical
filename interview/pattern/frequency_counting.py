class BullsAndCows:
    def getHint(self, secret: str, guess: str) -> str:
        dup_chr = 0
        count_secret = {}
        count_guess = {}
        for s, g in zip(secret, guess):
            if s == g:
                dup_chr += 1
            else:
                count_secret[s] = count_secret.get(s, 0) + 1
                count_guess[g] = count_guess.get(g, 0) + 1

        total_cows = 0
        for i, ch in enumerate(count_secret):
            total_cows += min(count_secret.get(ch, 0), count_guess.get(ch, 0))

        return "".join([str(dup_chr), "A", str(total_cows), "B"])

      def getHint_optimizer(self, secret: str, guess: str) -> str:
          count =[0] * 10
          bulls = 0
          cows = 0
          for s, g in zip( secret, guess):
              if s == g:
                  bulls += 1
                  continue
              
              s_int = ord(s) - ord('0')
              g_int = ord(g) - ord('0')
              
              # s has character which g has before
              if count[s_int] < 0:
                  cows += 1
              # g has character which s has before
              if count[g_int] > 0:
                  cows += 1

              
              count[s_int] += 1
              count[g_int] -= 1
          return f"{bulls}A{cows}B"
        


sol = BullsAndCows()
print(sol.getHint("1123", "0111"))
