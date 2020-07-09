import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jdk.jfr.Timestamp;
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
                if (JSONOrXML(line) == JSON)
                {
                    System.out.println(ToXML(line));
                }
                else
                {
                    System.out.println(ToJSON(line));
                }
            }
            reader.close();
        }
        catch(Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", "text.txt");
            e.printStackTrace();
          }
    }

    public boolean hasJSONAttribute(String input)
    {
        int importantIndex = input.indexOf(">");

        if(input.substring(0,importantIndex).contains("="))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toJSONSimple(String input)
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

    // public int numberOfAttributesJSON(String input)
    // {
    //     int counter = 0;
    //     for(int i = 0; i < input.size(); i++)
    //     {
    //         if(input.charAt(i).equals("="))
    //         {
    //             counter += 1;
    //         }
    //     }
    // }

    public String toJSONAttribute(String input)
    {   
        // Starting with attributes

        Matcher elem = Pattern.compile("</.*>");
        String elemPrincipal = new String(
            elem.group(1).substring(1,elem.group(1).size()-2)
            ); 

        // List<String> Attributes = new ArrayList<String>();
        // Matcher m = Pattern.compile("\s.*\s=\s");

        // while(m.find())
        // {
        //     Attributes.add(m.group(1).substring(1,m.group(1).size()-2));
        // }
        // // Now will do the values:

        // List<String> Values = new Values<String>();
        // Matcher mValues = Pattern.compile(".*");
        
        // while(mValues.find())
        // {
        //     Values.add(mValues.group(1).substring(1,m.group(1).size()-2));
        // }

        // Matcher middle = Pattern.compile(">.*<")
        // middle.find();
        // String elementValueMiddle = new String( middle.group(1).substring(1,middle.group(1).size()-2));


        // String stringXML = new String();

        return elemPrincipal;

    }

    public String ToJSON(String input)
    {
        if (hasJSONAttribute(input)){
            return toJSONAttribute(input);
        }
        else
        {
            return toJSONSimple(input);
        }
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
        JSONSimpleTest();
        JSONSimpleTest2();
        new XMLSimpleTest();
        new XMLSimpleTest2();
        new ToJSONAttribute();
    }

    public static void JSONSimpleTest()
    {
        String givenInput = new String("<host>127.0.0.1</host>");
        String givenResult = new String(ToJSON(givenInput));
        String expectedResult = new String("{\"host\":\"127.0.0.1\"}");
        System.out.println( givenResult.equals(expectedResult) );
    }

    public static void JSONSimpleTest2()
    {
        String givenInput = new String("<jdk>1.8.9</jdk>");
        String givenResult = new String(ToJSON(givenInput));
        String expectedResult = new String("{\"jdk\" : \"1.8.9\"}");
        System.out.println( givenResult.equals(expectedResult) );
    }

    public void XMLSimpleTest()
    {
        String givenInput = new String("{\"jdk\" : \"1.8.9\"}");
        String givenResult = new String(ToXML(givenInput));
        String expectedResult = new String("<jdk>1.8.9</jdk>");
        System.out.println( givenResult.equals(expectedResult) );
    }

    public void XMLSimpleTest2()
    {
        String givenInput = new String("{\"host\":\"127.0.0.1\"}");
        String givenResult = new String(ToXML(givenInput));
        String expectedResult = new String("<host>127.0.0.1</host>");
        System.out.println( givenResult.equals(expectedResult) );
    }

    public void ToJSONAttribute()
    {
        String givenInput = new String("<employee department = \"manager\">Garry Smith</employee>");
        String givenResult = new String(ToJSONAttribute(givenInput));
        String expectedResult = new String("employee");
        System.out.println( givenResult.equals(expectedResult) );
    }


}