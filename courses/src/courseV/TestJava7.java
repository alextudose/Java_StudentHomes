package courseV;

import java.lang.*;

public class TestJava7
{
    public static void main(String[] args)
    {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Hello ... Is it meat you looking for ? ");
            }
        };

        //runnable.run();

        Runnable myRunnable = () -> System.out.println("Na na!Just a Lambda expression!");
        //myRunnable.run();

        //new TestJava7().show();

        String s = "I said what what, in the butt!";
        print(() -> s.length()); //lambda expresie data ca parametru
        print(s::length);  // metoda ref


    }

    private void show()
    {
        int b = 2; // can be accesed from mock/functional interface if is final , cannot be modified

        Fun fun = (x) -> {
            int c = b;
            //b++;
            return x + 1;
        };
        int y = fun.exec(2);
        System.out.println(y);

        Runnable r = new Runnable()
        {
            public int a;

            @Override
            public void run()
            {
                this.a = 1;
                int c = b;
                System.out.println(String.format("%s potato for you, $s freddy for me",a,c));
            }
        };

        r.run();


    }

    public static <T> void print(MySupplier<T> supplier)
    {
        System.out.println(supplier.get());
    }
}
