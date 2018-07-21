package com.wordpress.ayo218.javalibrary;

import java.util.Random;

public class JokeTelling {
//    private String[] jokes;
//    private Random random;
//
//    public JokeTelling(){
//        jokes = new String[3];
//        jokes[0] = "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"";
//        jokes[1] = "Q: How many prolog programmers does it take to change a lightbulb? A: Yes.";
//        jokes[2] = "Why do Java developers wear glasses? Because they can't C#";
//        random = new Random();
//    }
//
//    public String[] getJokes() {
//        return jokes;
//    }
//
//    public String getRandomJokes(){
//        return jokes[random.nextInt(jokes.length)];
//    }

        static Random rand = new Random();
        private static String[] jokes = {"A boolean and a float walk into the Mos Eisley Cantina.\n\nThe bartender throws them out, saying, \"We don't serve your types here!\"",
                "An Intent walks into a bar.\n\nThe bartender scowls and says, \"All right pal, I'll serve you, but don't start anything.\"",
                "Luke Skywalker has been having a terrible time trying to figure out GitHub to get the example projects for his Nanodegree set up on his computer.\n\nHis mentor Obi-Wan says, \"Use the forks, Luke.\"",
                "The \"Also known as:\" label TextView from the Sandwich Club starter code walks into a bar.\n\nThe bartender says, \"Sorry, but I can't serve you, because you don't have an ID.\"",
                "The bartender says, \"We don't serve AsyncTasks in here.\"\n\nAn AsyncTask walks into a bar.",
                "A student walks into the Grow_with_Google_Nanodegrees Slack #night_owls bar after a three-day weekend, and says, \"I'll have my usual.\"\n\nThe bartender says, \"Sorry, your workspace is over its storage limit. Please upgrade to a paid plan if you want us to be able to remember anything that happened more than a couple days ago.\"",
                "A student posts to the Grow_with_Google_Nanodegrees Slack #github channel, \"Can anyone help me understand how to use 'Git Bash'? Is it supposed to be like 'Hulk Smash!', or do you only do a little bit of bashing?\"",
                "Android, where ProgressBars go around in circles, and Spinners don't spin.",
                "Spiders are the only web developers who enjoy finding bugs.",
        "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"",
        "Why do Java developers wear glasses? Because they can't C#"};

        public static String getJoke() {
            return jokes[rand.nextInt(jokes.length)];
        }
}
