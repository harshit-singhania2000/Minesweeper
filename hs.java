import java.io.*;
class hs
{
    String fileName;
    File f;
    hs(String fileNameT) throws FileNotFoundException,IOException
    {
        fileName=fileNameT;
        f=new File(fileName);
    }
    int getNumbOfLines() throws IOException
    {
        int ctr=0;
        BufferedReader br=new BufferedReader(new FileReader(f));
        while(br.readLine()!=null)
            ctr++;
        return ctr;
    }
    void readFile(String store[]) throws IOException
    {
        String temp;
        int i=0;
        BufferedReader br=new BufferedReader(new FileReader(f));
        while((temp=br.readLine())!=null)
        {
            store[i]=temp;
            System.out.println("line "+i+":"+store[i]);
            i++;
        }
    }
    void writeToFile(String data) throws Exception
    {
        int nl=getNumbOfLines();
        String temp[]=new String[nl];
        readFile(temp);
        PrintStream ps=new PrintStream(new File(fileName));
        for(int i=0;i<nl;i++)
            ps.println(temp[i]);
        ps.println(data);
        //fw.close();
    }
    void printFile() throws IOException
    {
        String t[]=new String[getNumbOfLines()];
        System.out.println(getNumbOfLines());
        readFile(t);
        System.out.println("printing file:");
        for(int i=0;i<t.length;i++)
            System.out.println(t[i]);
    }
    /*public static void main(String args[])
    {
        try
        {
            hs obj=new hs("test.txt");
            System.out.println("1");
            obj.writeToFile("abc1");
            obj.writeToFile("abc2");
            obj.writeToFile("abc3");
            System.out.println("2");
            obj.printFile();
        }catch(Exception e){e.printStackTrace();System.out.println("Exception");};
    }*/
}
