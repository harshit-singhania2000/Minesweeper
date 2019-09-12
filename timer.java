class timer implements Runnable
{
    app2 temp;
    int ctr;
    Thread t;
    timer(app2 obj)
    {
        ctr=0;
        temp=obj;
        t=new Thread(this);

    }
    void contTimer()
    {
        t.run();
    }
    Thread getThread()
    {return t;}
    public void run()
    {
        try
        {
            t.sleep(1000);
            ctr++;
            temp.showStatus("time:"+ctr+"sec");
        }catch(InterruptedException e){};
    }
    String getTime()
    {
        return (""+ctr);
    }
}
