package converter;
import java.util.Scanner;

public class Converter
{   
    public int JSON = 100;
    public int XML = 200;

    public Converter()
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        String convertedInput;
        input = input.replaceAll("\\s+","");

        System.out.println(input);

        if(JSONOrXML(input) == JSON)
        {
            convertedInput = ToXML(input);
        }
        else
        {
            convertedInput = ToJson(input);
        }
        System.out.println(convertedInput);

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
        String resultString = new String("");
        // int indexFirst = input.indexOf("\"") + 1;
        // int indexSecond = input.indexOf()


        if(input.contains("null"))
        {
            int firstIndex = input.indexOf("\"") + 1;
            int secondIndex = input.lastIndexOf("\"");
            resultString = "<" + input.substring(firstIndex, secondIndex) + "/>";
        }
        else
        {
            int firstIndex = 2;
            int secondIndex = input.indexOf(":") - 1;
            int thirdIndex = secondIndex + 3;
            int fourthIndex = input.length() - 2;

            resultString =      "<" + input.substring(firstIndex, secondIndex)
                                + ">" 
                                + input.substring(thirdIndex, fourthIndex)
                                + "</"
                                + input.substring(firstIndex, secondIndex)
                                + ">";
                
        }
        return resultString;
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