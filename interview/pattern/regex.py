class Solution:
    # num: sign(opt) + (dec or int) + e + sign(opt) + int
    def isNumber(self, s: str) -> bool:
        s = s.strip()
        n = len(s)
        seen_exp = False
        seen_dot = False
        seen_after_exp = True
        seen_digit = False
        sign = {'+', '-'}
        exponent = {'e', 'E'}

        for i in range(n):
            if s[i].isdigit():
                seen_digit= True
                if not seen_after_exp:
                    seen_after_exp = True
            elif s[i] in sign:
                if i!=0 and (not seen_exp or s[i-1] not in exponent):
                    return False
            elif s[i] in exponent:
                if not seen_digit or seen_exp:
                    return False
                seen_exp = True
                seen_after_exp = False
            elif s[i] == '.':
                if seen_dot or seen_exp:
                    return False
                seen_dot= True
            else:
                return False
        return seen_digit and seen_after_exp
            
sol = Solution()
# "2", "0089",
valid_number = ["2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
for num in valid_number:
    print(sol.isNumber(num))

invalid_number = ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
for num in invalid_number:
    print(sol.isNumber(num))
        