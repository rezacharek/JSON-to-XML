public class another extends JSON
{
    public another()
    {
        System.out.println("I don't do shit");
    }

    public static void main(String []args)
    {
        new another();
        JSON mine = new JSON("blyat");
        mine.show();
    }
}