package algorithm.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by sayef on 5/23/17.
 */
public class Main {
    static String paragraph = "";
    static String[] patterns = {"anal sex", "anus", "arse", "arsebadger", "arsehole", "ass", "ass-hat", "assbag", "asses", "assface", "assfuck", "assfucker", "asshole", "asslick", "ass-sucker", "asswipe", "assload", "are you nuts", "bastard", "bimbo", "booty", "bitch", "bitches", "bitchy", "bitchout", "bitching", "bitch please", "bitch tits", "bitch slap", "bitchtits", "bloody hell", "blooming hell", "blow me", "blow hard", "blowjob", "blow job", "bollocks", "bollox", "boobs", "boobjob", "boobies", "booby", "boner", "bonker", "bullshit", "bumblefuck", "bust a load", "buttplug", "buttfuck", "butt-fucker", "butthole", "chink", "clit", "clitface", "cock", "cockass", "cockbite", "cockface", "cockfucker", "cockhead", "creampie", "cum", "cumming", "cum sucker", "cumtart", "cunt", "cuntface", "cunthole", "cunthole", "cum freak", "daft cow", "damn", "damn you", "dammit", "dick", "dickbag", "dickhole", "dickface", "dickfuck", "dickhead", "dickjuive", "dickmilk", "dickslap", "dicksucker", "dicksucking", "dickweed", "dildo", "dipshit", "do you spit or swallow", "douchebag", "douchface", "dry hump", "dumass", "dumb ass", "dum arse", "dumbass", "dumbo", "dumbshit", "dumfuck", "dumbfuck", "dumb blonde", "fag", "fagbag", "fagfucker", "faggit", "faggot", "fatass", "f you", "fuck", "fuck yes", "fuck no", "fuck off", "fuck it", "fuck this", "fuck this shit", "fuck that", "fuckface", "fuckbag", "fat fuck", "fucking ass", "fuck you", "fucker", "fuckhead", "fuckhole", "fucking", "fuck off", "fat fuck", "fucktard", "fuckup", "fuckwitt", "gay", "gayass", "gayboy", "gayfuck", "gaylord", "gartard", "gaytad", "git", "goddam", "god dammit", "god darn", "god darn it", "go to hell", "have you gone nuts", "have you lost your shit", "handjob", "hard on", "hell", "heck", "ho", "hoe", "hold yout shit together", "holy shit", "holy fuck", "homo", "humping", "incest", "idiot", "jackass", "jack arse", "jackshit", "jackhole", "jack off", "jerk off", "jerkass", "jizz", "kunt", "killer's ass", "kiss my ass", "lameass", "lesbian", "lesbo", "lick my tit", "lick my balls", "lick by dick", "lick my froth", "manwhore", "mothafucka", "mothafuckin", "mothefucker", "motherfucking", "moron", "negro", "naked", "nigga", "nigger", "niggers", "niglet", "nutsuck", "nutsack", "nutter", "nudity", "nude", "booty", "idiot", "moron", "orgasm", "orgasmic", "orgy", "paki", "penis", "penisbanger", "penisfucker", "piss", "pissed", "piss off", "pissed off", "piss it", "porno", "porn", "pornography", "porn addict", "prick", "punk", "punk face", "pussy", "pussies", "pussylicking", "queef", "queer", "queerhole", "rat's ass", "rimjob", "ruski", "sadist", "sandnigger", "sex", "sex slave", "sex addict", "shag", "shit", "shitass", "shitbag", "shitbanger", "shitface", "shitfaced", "shithole", "shit brains", "shit cunt", "shitty", "shithead", "shithole", "shitspiter", "shitter", "shittiest", "shitting", "shiz", "shut your cunt", "skullfuck", "stinkhole", "stinker", "slut", "slutbag", "sod off", "sodden", "sucker", "suck", "suck it", "suck my dick", "suck my tit", "suck hard", "sucka", "suckbag", "suckass", "swag", "swallow my cum", "swine", "take a load", "take a dump", "testicle", "the hell", "tit", "titfuck", "tits", "titty", "tittyfuck", "tithole", "twat", "twit", "twatnut", "vaj", "vajayjay", "vagina", "wank", "wankjob", "wanker", "wetback", "wet pussy", "whore", "whorebag", "whoreface", "wtf", "what the fuck"};
    static BoyerMoore[] boyerMoores = new BoyerMoore[patterns.length+5];

    public static void main(String[] args) {
        int i = 0;
        for (String pattern: patterns){
            boyerMoores[i++] = new BoyerMoore(" " + pattern + " ");
        }
        for (i=0; i<3; i++) {
            try {
                File file = new File("src/algorithm/string/in.txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                paragraph = br.readLine();
                br.close();

                testJavaContains();
                testSuffixArray();
                testBoyerMoore();
                testKMP();


            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

    }

    public static void testJavaContains(){
        long timeStartedAt = System.currentTimeMillis();
        System.out.println("TESTING JAVA CONTAINS: ");

        for (String pattern: patterns){
            if(paragraph.contains(" " + pattern + " ")) {
                System.out.println(pattern + " found!");
            }
        }

        long timeFinishedAt = System.currentTimeMillis();
        System.out.println((timeFinishedAt-timeStartedAt));
    }

    public static void testBoyerMoore(){
        long timeStartedAt = System.currentTimeMillis();
        System.out.println("TESTING BOYER MOORE: ");

        int len = paragraph.length();
        int index;
        int i = 0;

        for(i = 0; i < patterns.length; i++){
            if((index = boyerMoores[i].search(paragraph)) < len){
                System.out.println(boyerMoores[i].getPattern() + " found at index " + index);
            }
        }


        /*for(String pattern: patterns){
            BoyerMoore boyerMoore = new BoyerMoore(" " + pattern + " ");
            if((index = boyerMoore.search(paragraph)) < len){
                System.out.println(pattern + " found at index " + index);
            }
        }*/

        long timeFinishedAt = System.currentTimeMillis();
        System.out.println((timeFinishedAt-timeStartedAt));
    }

    public static void testKMP(){
        long timeStartedAt = System.currentTimeMillis();
        System.out.println("TESTING KMP: ");

        int len = paragraph.length();
        int index;
        for(String pattern: patterns){
            KMP kmp = new KMP(" " + pattern + " ");
            if((index = kmp.search(paragraph)) < len){
                System.out.println(pattern + " found at index " + index);
            }
        }
        long timeFinishedAt = System.currentTimeMillis();
        System.out.println((timeFinishedAt-timeStartedAt));
    }

    public static void testSuffixArray(){
        long timeStartedAt = System.currentTimeMillis();
        System.out.println("TESTING SUFFIX ARRAY: ");

        SuffixArray suffix = new SuffixArray(paragraph);
        int index;
        for(String pattern: patterns) {
            if((index = suffix.search(" " + pattern + " ")) >= 0){
                System.out.println(pattern + " found at index " + index);
            }
        }

        long timeFinishedAt = System.currentTimeMillis();
        System.out.println((timeFinishedAt-timeStartedAt));
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
