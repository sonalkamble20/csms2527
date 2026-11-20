//Name: Sonal Kamble

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.NaN;

class PostFixExpression
{
    public static void main(String args[])
    {
        Scanner sc;

        File file = new File("/home/sonalkamble/Desktop/Sonal/Data Structures -  Kequin LI/Assigments/Assignment5/work/in.dat");

        try{
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Hello! This is a postfix expression calculator.");
        System.out.println();

        int i = 1;
        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            Double result = token(line);
            System.out.println(+i + ". The value of " +line+ " is " +result);
            System.out.println();
            i++;
        }

        System.out.println("Bye-Bye!");
    }

    public static Double token(String str)
    {
        Stack<Double> numbers = new Stack<>();

        Pattern pattern = Pattern.compile("\\d+\\.\\d+|\\d+|[+\\-*/]|[_#^]");

        Matcher matcher = pattern.matcher(str);

        List<String> tokens = new ArrayList<>();
        while(matcher.find())
        {
            tokens.add(matcher.group());
        }

        if(numbers.empty())
        {
            for(String token : tokens)
            {
                switch (token)
                {
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                    case "^": if(!numbers.empty())
                        numbers.push(calc(token, numbers.pop(), numbers.pop()));
                        break;
                    case "_": if(!numbers.empty())
                        numbers.push(calc(token, numbers.pop()));
                    break;
                    case "#":if(!numbers.empty())
                        numbers.push(calc(token, numbers.pop()));
                    break;


                    default: numbers.push(Double.parseDouble(token));
                        break;
                }
            }
        }

        return numbers.pop();
    }

    private static double calc(String token, Double num2, Double num1)
    {
        double result = NaN;
        switch (token)
        {
            case "+": result = num2 + num1;
            break;
            case "-": result = num1 - num2;
            break;
            case "*": result = num2 * num1;
                break;
            case "/": result = num1 / num2;
                break;
            case "_": result = num2 * -1;
                break;
            case "#": result = Math.sqrt(num2);
                break;
            case "^": result = Math.pow(num1,num2);
                break;

        }
        return result;
    }

    private static double calc(String token, Double num1)
    {
        double result = NaN;
        switch (token)
        {
            case "_": result = num1 * -1;
                break;
            case "#": result = Math.sqrt(num1);
                break;

        }
        return result;
    }
}