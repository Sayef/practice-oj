package algorithm.string.suffixtree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by sayef on 5/23/17.
 */
public class Main {
    /**
     * Unit tests the {@code SuffixArray} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        long timeStartedAt = System.currentTimeMillis();
        String paragraph = "";
        try
        {
            File file = new File("/home/sayef/Dev/Projects/Java/Practice/src/algorithm/string/suffixtree/in.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            paragraph = br.readLine();
            br.close();
            //test:
            //System.out.println(paragraph);
        } catch (IOException e)
        {
            // Something went wrong, eg: file not found
            e.printStackTrace();
            System.exit(0);
        }

        /*paragraph = capsFirst(paragraph);
        paragraph = paragraph.replaceAll(" ", "");
        System.out.println(paragraph);
        */




        //paragraph = paragraph.replaceAll("\\s+", " ").trim();
        SuffixArray suffix = new SuffixArray(paragraph);

        // StdOut.println("rank(" + args[0] + ") = " + suffix.rank(args[0]));

        System.out.println("  i ind lcp rnk select");
        System.out.println("---------------------------");

        /*for (int i = 0; i < s.length(); i++) {
            int index = suffix.index(i);
            String ith = "\"" + s.substring(index, Math.min(index + 50, s.length())) + "\"";
            assert s.substring(index).equals(suffix.select(i));
            int rank = suffix.rank(s.substring(index));
            if (i == 0) {
                System.out.printf("%3d %3d %3s %3d %s\n", i, index, "-", rank, ith);
            }
            else {
                int lcp = suffix.lcp(i);
                System.out.printf("%3d %3d %3d %3d %s\n", i, index, lcp, rank, ith);
            }
        }*/
        String[] patterns = {"anal sex", "anus", "arse", "arsebadger", "arsehole", "ass", "ass-hat", "assbag", "asses", "assface", "assfuck", "assfucker", "asshole", "asslick", "ass-sucker", "asswipe", "assload", "are you nuts", "bastard", "bimbo", "booty", "bitch", "bitches", "bitchy", "bitchout", "bitching", "bitch please", "bitch tits", "bitch slap", "bitchtits", "bloody hell", "blooming hell", "blow me", "blow hard", "blowjob", "blow job", "bollocks", "bollox", "boobs", "boobjob", "boobies", "booby", "boner", "bonker", "bullshit", "bumblefuck", "bust a load", "buttplug", "buttfuck", "butt-fucker", "butthole", "chink", "clit", "clitface", "cock", "cockass", "cockbite", "cockface", "cockfucker", "cockhead", "creampie", "cum", "cumming", "cum sucker", "cumtart", "cunt", "cuntface", "cunthole", "cunthole", "cum freak", "daft cow", "damn", "damn you", "dammit", "dick", "dickbag", "dickhole", "dickface", "dickfuck", "dickhead", "dickjuive", "dickmilk", "dickslap", "dicksucker", "dicksucking", "dickweed", "dildo", "dipshit", "do you spit or swallow", "douchebag", "douchface", "dry hump", "dumass", "dumb ass", "dum arse", "dumbass", "dumbo", "dumbshit", "dumfuck", "dumbfuck", "dumb blonde", "fag", "fagbag", "fagfucker", "faggit", "faggot", "fatass", "f you", "fuck", "fuck yes", "fuck no", "fuck off", "fuck it", "fuck this", "fuck this shit", "fuck that", "fuckface", "fuckbag", "fat fuck", "fucking ass", "fuck you", "fucker", "fuckhead", "fuckhole", "fucking", "fuck off", "fat fuck", "fucktard", "fuckup", "fuckwitt", "gay", "gayass", "gayboy", "gayfuck", "gaylord", "gartard", "gaytad", "git", "goddam", "god dammit", "god darn", "god darn it", "go to hell", "have you gone nuts", "have you lost your shit", "handjob", "hard on", "hell", "heck", "ho", "hoe", "hold yout shit together", "holy shit", "holy fuck", "homo", "humping", "incest", "idiot", "jackass", "jack arse", "jackshit", "jackhole", "jack off", "jerk off", "jerkass", "jizz", "kunt", "killer's ass", "kiss my ass", "lameass", "lesbian", "lesbo", "lick my tit", "lick my balls", "lick by dick", "lick my froth", "manwhore", "mothafucka", "mothafuckin", "mothefucker", "motherfucking", "moron", "negro", "naked", "nigga", "nigger", "niggers", "niglet", "nutsuck", "nutsack", "nutter", "nudity", "nude", "booty", "idiot", "moron", "orgasm", "orgasmic", "orgy", "paki", "penis", "penisbanger", "penisfucker", "piss", "pissed", "piss off", "pissed off", "piss it", "porno", "porn", "pornography", "porn addict", "prick", "punk", "punk face", "pussy", "pussies", "pussylicking", "queef", "queer", "queerhole", "rat's ass", "rimjob", "ruski", "sadist", "sandnigger", "sex", "sex slave", "sex addict", "shag", "shit", "shitass", "shitbag", "shitbanger", "shitface", "shitfaced", "shithole", "shit brains", "shit cunt", "shitty", "shithead", "shithole", "shitspiter", "shitter", "shittiest", "shitting", "shiz", "shut your cunt", "skullfuck", "stinkhole", "stinker", "slut", "slutbag", "sod off", "sodden", "sucker", "suck", "suck it", "suck my dick", "suck my tit", "suck hard", "sucka", "suckbag", "suckass", "swag", "swallow my cum", "swine", "take a load", "take a dump", "testicle", "the hell", "tit", "titfuck", "tits", "titty", "tittyfuck", "tithole", "twat", "twit", "twatnut", "vaj", "vajayjay", "vagina", "wank", "wankjob", "wanker", "wetback", "wet pussy", "whore", "whorebag", "whoreface", "wtf", "what the fuck"};
        for(String pattern: patterns) {
            searchPattern(pattern, paragraph, suffix);
        }
        long timeFinisheddAt = System.currentTimeMillis();

        System.out.print((timeFinisheddAt-timeStartedAt));

    }

    public static int searchPattern(String pattern, String text, SuffixArray suffix)
    {
        pattern = " " + pattern + " ";
        int m = pattern.length();
        int n = text.length();
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l)/2;
            int index = suffix.index(mid);
            String midString  = text.substring(index, Math.min(index+m, n));
            int res = pattern.compareTo(midString);
            if (res == 0) {
                System.out.println("Pattern " + pattern + " found at index " + suffix.index(mid));
                return suffix.index(mid);
            }

            if (res < 0) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }

    static String capsFirst(String str) {
        String[] words = str.split(" ");
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            ret.append(Character.toUpperCase(words[i].charAt(0)));
            ret.append(words[i].substring(1));
            if(i < words.length - 1) {
                ret.append(' ');
            }
        }
        return ret.toString();
    }

}
