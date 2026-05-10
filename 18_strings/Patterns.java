import java.util.*;

public class Patterns {

    // Pattern 1: Clean palindrome check.
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            char a = Character.toLowerCase(s.charAt(left));
            char b = Character.toLowerCase(s.charAt(right));
            if (a != b) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // Pattern 2: Palindrome after deleting at most one character.
    public static boolean validPalindromeAfterOneDelete(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindromeRange(s, left + 1, right)
                    || isPalindromeRange(s, left, right - 1);
            }
            left++;
            right--;
        }

        return true;
    }

    private static boolean isPalindromeRange(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Pattern 3: Longest substring without repeated characters.
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int left = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1;
            }

            lastSeen.put(c, right);
            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    // Pattern 4: Minimum window containing all characters of target.
    public static String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int have = 0;
        int needKinds = need.size();
        int bestStart = 0;
        int bestLen = Integer.MAX_VALUE;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                have++;
            }

            while (have == needKinds) {
                int len = right - left + 1;
                if (len < bestLen) {
                    bestLen = len;
                    bestStart = left;
                }

                char remove = s.charAt(left);
                window.put(remove, window.get(remove) - 1);
                if (need.containsKey(remove) && window.get(remove) < need.get(remove)) {
                    have--;
                }
                left++;
            }
        }

        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }

    // Pattern 5: Find all anagram start indices using a fixed-size window.
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (p.length() > s.length()) {
            return result;
        }

        int[] need = new int[26];
        int[] window = new int[26];

        for (char c : p.toCharArray()) {
            need[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            window[s.charAt(i) - 'a']++;

            if (i >= p.length()) {
                window[s.charAt(i - p.length()) - 'a']--;
            }

            if (Arrays.equals(need, window)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }

    // Pattern 6: Simplify Unix-style path using stack.
    public static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else {
                stack.addLast(part);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append('/').append(dir);
        }
        return result.toString();
    }

    // Pattern 7: Decode nested strings like "3[a2[c]]" -> "accaccacc".
    public static String decodeString(String s) {
        Deque<Integer> counts = new ArrayDeque<>();
        Deque<StringBuilder> builders = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        int number = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '[') {
                counts.push(number);
                builders.push(current);
                current = new StringBuilder();
                number = 0;
            } else if (c == ']') {
                int repeat = counts.pop();
                StringBuilder parent = builders.pop();
                for (int i = 0; i < repeat; i++) {
                    parent.append(current);
                }
                current = parent;
            } else {
                current.append(c);
            }
        }

        return current.toString();
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(validPalindromeAfterOneDelete("abca"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(simplifyPath("/a/./b/../../c/"));
        System.out.println(decodeString("3[a2[c]]"));
    }
}
