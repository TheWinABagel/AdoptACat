package dev.bagel.adopt;

import dev.bagel.adopt.arg.SortParser;

public class Main {

    public static void main(String[] args) {
        SortParser parser = new SortParser("http://localhost:3000/cats", args);
        parser.getParser().saveToFile();
    }

    public static void printHelp() {
        String[] usage = {
                "Usage: java program [options...]",
                "Attempts to connect to server at 'http://localhost:3000/cats'\n",
                "Sort types [<st>], will only print to a file if all conditions match",
                " =          Equals",
                " >          Greater Than",
                " <          Less Than",
                " >=         Greater Than or Equal To",
                " <=         Less Than or Equal To",
                " contains=  Check if value contains passed string (ignoring case)\n",
                "Options",
                " -c:<st> <color>, --color:<st> <color>    Sort against color of cat",
                " -a:<st> <color>, --age:<st> <age>        Sort against age of cat",
                " -n:<st> <color>, --name:<st> <age>       Sort against name of cat"
        };
        for (String str : usage) {
            System.out.println(str);
        }
        System.exit(0);
    }

}