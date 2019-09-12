import java.util.Scanner;
import java.util.Random;
class proc
{
	static int x,y,dif;
	static int arr[][],click[][];
	static void begin()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of rows and columns");
		y=x=sc.nextInt();
		System.out.println("Enter The difficulty, 1(winning depends only on luck)-20(easiest)");
		dif=sc.nextInt();
		arr=new int[x][y];
		click=new int[x][y];
		initgrid(arr);
	}
	static String getdif()
	{
		return (dif+"");
	}
	static void getCell(int cellarr[],int height,int width)
	{
		int mx=cellarr[0],my=cellarr[1],retx=0,rety=0;
		for(int i=1;i<=x;i++)
		{
			for(int j=1;j<=y;j++)
			{
				if(mx<j*width/y &&  my<i*height/x)
				{
					rety=j-1;
					break;
				}
			}
		}
		for(int i=1;i<=y;i++)
		{
			for(int j=1;j<=x;j++)
			{
				if(mx<i*width/y &&  my<j*height/x)
				{
					retx=j-1;
					break;
				}
			}
		}
		cellarr[0]=retx;
		cellarr[1]=rety;
		System.out.println("Mouse:"+cellarr[0]+","+cellarr[1]);
	}	
	static void game(int board[][],int view[][],int i,int j,int f)
	{
		if(i>=x || j>=y || i<0 || j<0)
			return;
		if(view[i][j]==1)
			return;
		if(f==1)
		{
			view[i][j]=1;
			if(board[i][j]==0)
			{
				game(board,view,i+1,j+1,1);
				game(board,view,i+1,j,1);
				game(board,view,i+1,j-1,1);
				game(board,view,i,j+1,1);
				game(board,view,i,j-1,1);
				game(board,view,i-1,j+1,1);
				game(board,view,i-1,j,1);
				game(board,view,i-1,j+1,1);
			}
		}
		else if(view[i][j]==0)
		{
			view[i][j]=1;
			if(board[i][j]==0)
			{
				game(board,view,i+1,j+1,1);
				game(board,view,i+1,j,1);
				game(board,view,i+1,j-1,1);
				game(board,view,i,j+1,1);
				game(board,view,i,j-1,1);
				game(board,view,i-1,j+1,1);
				game(board,view,i-1,j,1);
				game(board,view,i-1,j+1,1);
			}
		}
	}
	static boolean finish(int board[][],int view[][])
	{
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				if(board[i][j]!=-1 && view[i][j]==0)
					return false;
			}
		}
		return true;
	}
	static void initgrid(int grid[][])
	{
		int l=x,w=y,num_mines=0;
		Random r=new Random();
		int total=l*w;
		if(dif!=1)
			num_mines=total/dif;
		else
			num_mines=total-1;
		System.out.println(num_mines);
		int a=1;
		for(int i=0,j=0;i<l && j<w;j++)
		{
			click[i][j]=0;
			if(j==w-1)
			{
				j=-1;
				i++;
			}
		}
		System.out.println(num_mines);
		while(a<=num_mines)
		{
			int ran=r.nextInt(total);
			int i=ran/l,j=ran%w;
			if(grid[i][j]!=-1)
			{
				grid[i][j]=-1;
				a++;
			}
		}
		System.out.println(num_mines);
		int count=0;
		for(int i=0;i<l;i++)
		{
			for(int j=0;j<w;j++)
			{
				if(i==0 && j==0)
				{
					if(grid[i][j+1]==-1)
						count++;
					if(grid[i+1][j]==-1)
						count++;
					if(grid[i+1][j+1]==-1)
						count++;
				}
				else if(i==l-1 && j==w-1)
				{
					if(grid[i][j-1]==-1)
						count++;
					if(grid[i-1][j-1]==-1)
						count++;
					if(grid[i-1][j]==-1)
						count++;
				}
				else if(i==0 && j==w-1)
				{
					if(grid[i+1][j]==-1)
						count++;
					if(grid[i][j-1]==-1)
						count++;
					if(grid[i+1][j-1]==-1)
						count++;
				}
				else if(i==l-1 && j==0)
				{
					if(grid[i][j+1]==-1)
						count++;
					if(grid[i-1][j+1]==-1)
						count++;
					if(grid[i-1][j]==-1)
						count++;
				}
				else if(i==0)
				{
					if(grid[i][j+1]==-1)
						count++;
					if(grid[i+1][j]==-1)
						count++;
					if(grid[i+1][j+1]==-1)
						count++;
					if(grid[i][j-1]==-1)
						count++;
					if(grid[i+1][j-1]==-1)
						count++;
				}
				else if(j==0)
				{
					if(grid[i][j+1]==-1)
						count++;
					if(grid[i-1][j+1]==-1)
						count++;
					if(grid[i-1][j]==-1)
						count++;
					if(grid[i+1][j]==-1)
						count++;
					if(grid[i+1][j+1]==-1)
						count++;
				}
				else if(i==l-1)
				{
					if(grid[i][j-1]==-1)
						count++;
					if(grid[i-1][j-1]==-1)
						count++;
					if(grid[i-1][j]==-1)
						count++;
					if(grid[i][j+1]==-1)
						count++;
					if(grid[i-1][j+1]==-1)
						count++;
				}
				else if(j==w-1)
				{
					if(grid[i+1][j]==-1)
						count++;
					if(grid[i-1][j-1]==-1)
						count++;
					if(grid[i][j-1]==-1)
						count++;
					if(grid[i+1][j-1]==-1)
						count++;
					if(grid[i-1][j]==-1)
						count++;
				}
				else
				{
					if(grid[i+1][j]==-1)
						count++;
					if(grid[i-1][j-1]==-1)
						count++;
					if(grid[i][j-1]==-1)
						count++;
					if(grid[i+1][j-1]==-1)
						count++;
					if(grid[i-1][j]==-1)
						count++;
					if(grid[i][j+1]==-1)
						count++;
					if(grid[i-1][j+1]==-1)
						count++;
					if(grid[i+1][j+1]==-1)
						count++;
				}
				if(grid[i][j]!=-1)
					grid[i][j]=count;
				count=0;
			}
		}
	}
}
