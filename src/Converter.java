package converter;
import java.util.Scanner;

public class Converter
{   
    public int JSON = 100;
    public int XML = 200;

    public Converter()
    {
        Scanner scanner= new Scanner(System.in);
        String input = scanner.nextLine().trim();
        String convertedInput;

        if(JSONOrXML(input) == JSON)
        {
            convertedInput = ToXML(input);
        }
        else
        {
            convertedInput = ToJson(input);
        }
        System.out.println(ToJson(input));

    }

    public String ToJson(String input)
    {
        String resultString = new String("");
        int indexFirst = 1;
        int indexSecond = input.indexOf(">");
        int indexThird = input.lastIndexOf("<");
        int indexFourth = input.length() - 1;

        char ForComparison = input.charAt(indexSecond-1);

        if(ForComparison == '/')
        {
            resultString =      "{\"" + input.substring(indexFirst, indexSecond-1)
                                + "\":null}"; 

        }
        else
        {
            resultString =      "{\"" + input.substring(indexFirst, indexSecond) 
                                + "\":\"" 
                                + input.substring(indexSecond + 1,indexThird) 
                                + "\"}";
        }
        return resultString;
    }

    public String ToXML(String input)
    {   
        // String resultString = new String("");


    }

    public int JSONOrXML(String input)
    {
        if(input.contains("}") || input.contains("{"))
        {
            return JSON;
        } 
        else
        {
            return XML;
        }

    }



    public static void main(String[] args)
    {
        new Converter();
    }
}