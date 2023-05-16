import java.io.*;
import java.util.*;

public class App {

    public static boolean isVowel(char c) {
        // FOR LOWER CASE VOWELS ONLY
        char[] vowelList = { 'a', 'e', 'i', 'o', 'u' };
        for (int i = 0; i < 5; i++) {
            if (c == vowelList[i]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String combined = "";
        Boolean first = true;
        Boolean prev_dash = false, cur_dash = false;

        // task 1: string processing
        while (true) {
            String line = r.readLine();
            // check termination
            if (line.length() > 6) {
                String s7 = line.substring(0, 7);
                if (s7.equals("......."))
                    break;
            }

            // check end dash
            String last_char = line.substring(line.length() - 1);
            if (last_char.equals("-")) {
                line = line.substring(0, line.length() - 1);
                cur_dash = true;
            } else
                cur_dash = false;

            // combine
            if (first || prev_dash) {
                combined += line;
                first = false;
            } else
                combined = combined + " " + line;

            prev_dash = cur_dash;
        }
        pw.println(combined);

        // Task 2: String Searching
        // THIS IS WHERE YOU CHANGE THE SEARCHED STRING: !!!!!!!!!!!!!!!!!!!!!!!!!!
        String p = "wahhhhhhhhhh";
        ArrayList<Integer> instances = new ArrayList<>();
        int fromindex = 0;
        String open = "{";
        String close = "}";
        boolean hasinstance = true;
        boolean islast = false;

        while (true) {
            int pos = combined.indexOf(p, fromindex);
            if (pos == -1) {
                break;
            }
            instances.add(pos);
            fromindex = pos + p.length();
            if (fromindex > combined.length()) {
                break;
            }
        }
        // pw.println(instances);
        // non-conforming to the prompt, see code below

        // returns -1
        if (instances.size() == 0) {
            pw.print(open);
            pw.print(-1);
            pw.print(close);
            hasinstance = false;
        }

        // returns instances with curly brackets
        if (hasinstance == true) {
            pw.print(open);
            for (int i = 0; i < instances.size(); i++) {
                if (i == instances.size() - 1) {
                    islast = true;
                }

            }
            pw.print(close);
        }

        // task 3: analyzing strings
        combined = combined.toLowerCase();
        int digits = 0, vowels = 0, letters = 0;
        for (int i = 0; i < combined.length(); i++) {
            char c = combined.charAt(i);
            if (isVowel(c)) vowels++;
            if (Character.isDigit(c)) digits++;
            if (Character.isLetter(c)) letters++;
        }
        pw.println(" ");
        pw.println(digits + " digits " + vowels + " vowels " + (letters - vowels) + " consonants");

        // task 4: we bring back string tokenizer!
        ArrayList<String> tokens = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(combined, " .");

        while (st.hasMoreTokens()) tokens.add(st.nextToken());
        Collections.sort(tokens);
        pw.println(tokens);

        // task 5: treemap/hashmap
        TreeMap<String, Integer> freq = new TreeMap<>();
        for (String str : tokens){
            if (freq.containsKey(str)) freq.put(str, freq.get(str) + 1);
            else freq.put(str, 1);
        }
        pw.println(freq);
        int mostoccuring = 0;
        String mostoccuringterm = "";
        for (String key: freq.keySet()){
            if (freq.get(key) > mostoccuring){
                mostoccuring = freq.get(key);
                mostoccuringterm = key;
            }
        }
        pw.printf("%s has the highest occurence of %d \n", mostoccuringterm, mostoccuring);

        // task 6: length :) (why is this the easiest thing? why is it last? i don't know)
        String lastline = r.readLine();
        pw.println(lastline.length());
        pw.close();

    }
}
