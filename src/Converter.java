package converter;
import java.util.Scanner;
import java.io.*;

public class Converter
{   
    public int JSON = 100;
    public int XML = 200;

    public Converter()
    {
        // Scanner scanner = new Scanner(System.in);
        // String input = scanner.nextLine().trim();
        String convertedInput;
        File file = new File("test.txt");
        String input; 

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("text.txt"));
            String line;
            while((line = reader.readLine()) != null )
            {
                System.out.println(line);
            }
            reader.close();
        }
        catch(Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", "text.txt");
            e.printStackTrace();
          }
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