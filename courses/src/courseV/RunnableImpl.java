package courseV;

public class RunnableImpl implements Runnable
{
    private String mesaj;

    public RunnableImpl(String mesaj)
    {
            this.mesaj = mesaj;
    }

    @Override
    public void run()
    {
        System.out.println(mesaj);
    }
}
