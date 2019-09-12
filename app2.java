//appletviewer -J-Djava.security.policy=mypol apphtml.html
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.security.*;
public class app2 extends Applet implements Runnable,MouseListener
{
	int x,y,width=1000,height=1000,mouseX,mouseY,f,arr[][],click[][],end,inc;
	timer time;
	Thread t;
	String ft[];
	boolean ff;
	public void init()
	{
		ff=true;
		addMouseListener(this);
		t=new Thread(this);
		end=0;
		t.start();
	}
	public void run()
	{
		proc.begin();
		arr=proc.arr;
		click=proc.click;
		x=proc.x;
		y=proc.y;
		inc=width/x;
		f=1;
		repaint();
		time=new timer(this);
		while(end==0)
			time.contTimer();
	}
	void fin()
	{
		try{
			t.join();
		}catch(InterruptedException e){};
		if(end==1)
			showStatus("You Lost, click to continue");
		else if(end==2)
			showStatus("You won, click to continue");
	}
	public void mouseClicked(MouseEvent m)
	{
		if(end==2)
		{
			end=3;
			repaint();
			return;
		}
		mouseX=m.getX();
		mouseY=m.getY();
		int cellcoord[]={mouseX,mouseY};
		proc.getCell(cellcoord,height,width);
		proc.game(arr,click,cellcoord[0],cellcoord[1],0);
		System.out.println(proc.finish(arr,click));
		if(proc.finish(arr,click))
		{
			end=2;
			fin();
			showStatus("You Won!, click to continue");
		}
		repaint();
	}
	public void mouseEntered(MouseEvent m){}
	public void mouseExited(MouseEvent m){}
	public void mousePressed(MouseEvent m){}
	public void mouseReleased(MouseEvent m){}
	public void paint(Graphics g)
	{
		if(end==3 && ff==true)
		{
			AccessController.doPrivileged(new PrivilegedAction()
			{
				public Object run()
				{
					try
					{
						hs obj=new hs("test.txt");
						obj.writeToFile(time.getTime()+" seconds "+x+"x"+x+" grid, difficulty:"+proc.getdif());
						ft=new String[obj.getNumbOfLines()];
						System.out.println(obj.getNumbOfLines());
						obj.readFile(ft);
						System.out.println("printing file:");
						for(int i=0;i<ft.length;i++)
							g.drawString(ft[i],2*width/3,i/ft.length*height-100);
						ff=false;
						//obj.printFile();
					}catch(Exception e){e.printStackTrace();};
					return null;
				}
			});
		}
		if(ft!=null)
		{
			g.setFont(new Font("Arial",Font.BOLD,12));
			g.drawString("PREVIOUS SCORES",50,20);
			for(int i=0;i<ft.length;i++)
			{
				g.drawString(ft[i],100,(i+1)*50);
				System.out.println("app"+ft[i]);
			}
		}
		if(x!=0)
			g.setFont(new Font("Arial",Font.BOLD,200/x+3));
		else
			g.setFont(new Font("Arial",Font.BOLD,20));
		if(end==1)
		{
			g.setColor(Color.red);
			g.drawString("You LOST!",width/2,height/2);
			g.setColor(Color.black);
			return;
		}
		else if(end==3)
		{
			g.drawString("YOU WON!!, completed in "+time.getTime()+" seconds "+x+"x"+x+" grid, difficulty:"+proc.getdif(),width/2-100,height/2);
			return;
		}
		double temp;
		for(int i=0;i<y;i++)
		{
			temp=i*width/y;
			g.drawLine((int)temp,0,(int)temp,height);
		}
		for(int i=0;i<x;i++)
		{
			temp=i*height/x;
			g.drawLine(0,(int)temp,width,(int)temp);
		}
		if(f==0)
		{
			g.drawString("Enter input in the terminal",width/2-100,height/2);
		}
		g.drawLine(0,0,width,0);
		g.drawLine(width-0,0,width-0,height);
		g.drawLine(width-0,height-0,0,height-0);
		g.drawLine(0,height,0,0);
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				if(click[i][j]==1)
				{
					if(arr[i][j]!=-1)
					{
						g.setColor(Color.blue);
						g.fillRect(j*width/y+2,i*height/x+2,inc-4,inc-4);
						g.setColor(Color.black);
						g.drawString(""+arr[i][j],j*width/y+width/(y*2)-10,i*height/x+height/(x*2));
					}
					else
					{
						g.setColor(Color.red);
						g.fillRect(j*width/y,i*height/x,inc,inc);
						g.setColor(Color.black);
						g.drawString("M",j*width/y+width/(y*2)-10,i*height/x+height/(x*2));
						showStatus("You Lost!, click to continue");
						end=1;
						fin();
					}
				}
			}
		}
	}
}
