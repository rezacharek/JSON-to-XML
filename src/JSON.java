public class JSON
{
    public String myString;

    public JSON()
    {
        myString  = "something";
    }
    public JSON(String anotherString)
    {
        myString = anotherString;
    }

    public void show()
    {
        System.out.println(myString);
    }

    public static void main(String[] args)
    {
        new JSON();
    }
}