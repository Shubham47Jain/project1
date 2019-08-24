import java.util.*;
import java.io.*;
class hang_man
  {
  public static void main(String args[]) throws IOException
    {
    Scanner sc=new Scanner(System.in);
    InputStream is=null;
    OutputStream os=null;
    while(true)
      {
      
      System.out.println("\t\t\t\t\t\tGame Menu");
      System.out.println("\t\t\t\t\t\t---------\n");
      System.out.println("\t\t\t\t\t(1.)Add words in File\n");
      System.out.println("\t\t\t\t\t(2.)Play\n");
      System.out.println("\t\t\t\t\t(3.)High Score\n");
      System.out.println("\t\t\t\t\t(4.)How to play\n");
      System.out.println("\t\t\t\t\t(5.)Quit\n");
      System.out.println("Enter your choice");
      int choice=sc.nextInt();
      sc.nextLine();
      if(choice==1)
        {
        os=new FileOutputStream("hangman_file",true);
        while(true)
          {
          System.out.println("Enter a word");
          String wd=sc.nextLine();
          wd=wd+"\n";
          os.write(wd.getBytes());
          System.out.println("Want to add another words (y/n)");
          char ch=sc.nextLine().charAt(0);
          if(ch=='n')break;
          if(ch=='y')continue;
        }
        os.close();
      }//add words choice=1
      if(choice==2) 
        {
        int score=0;
        System.out.println("Enter your name");
        String name=sc.nextLine();
        is=new FileInputStream("hangman_file");
        int count=0;
        while(true)
          {
          int k=is.read();
          if(k==-1)break;
          if((char)k=='\n') count++;
        }
        is.close();
        is=new FileInputStream("hangman_file");
        String q="";
        int i=0;
        String a[]=new String[count];
        while(true)
          {
          int k=is.read();
          if(k==-1)break;
          if((char)k!='\n')q=q+(char)k;
          else
            {
            a[i]=q;
            q="";
            i++;
          }
        }
        int n=(int)(Math.random()*count);
        q=a[n];
        //System.out.println(q);
        //System.out.println(q.length());
        int cch=q.length();
        char ar[]=new char[cch];
        int error=0;
        for(i=0;i<ar.length;i++)
          {
          ar[i]='#';
          System.out.print(ar[i]+" ");
        }
        System.out.println();
        char ch;
        String nm="";
        while(error<7)
          {     
          int f=0;
          ch=sc.nextLine().charAt(0);
          for(i=0;i<q.length();i++)
            {
            if(ch==q.charAt(i))
              {
              ar[i]=ch;
              f++;
            }
          }
          if(f==0)error++;
          System.out.println("You have "+(7-error)+" chance.");
          nm="";
          for(i=0;i<ar.length;i++)
            {
            nm=nm+ar[i];
          }
          System.out.println(nm);
          if(nm.equals(q)) 
            {
            score=(q.length()*20)-error*5;
            System.out.println("\t\t\t"+name+" you won the game.");
            break;
          }
        }//while
        if(error==7)
          {
          score=-35;
          System.out.println("\t\t\t"+name+" you loose the game");  
          System.out.println("\t\t\tWord = "+q);
        }
        System.out.println("\t\t\t"+name+" score = "+score); 
        os=new FileOutputStream("score",true);              //file
        String abc=name+"\t\t"+score+"\n";                  //file
        os.write(abc.getBytes());                           //file
        os.close();                                         //file
      } //play choice=2
      
      if(choice==3)
        {
        System.out.println();
        System.out.println("Name\t\tScore");
        System.out.println("----\t\t-----");
        is=new FileInputStream("score");
        while(true)
          {
          int k=is.read();
          if(k==-1)break;
          System.out.print((char)k);
        }
        is.close();
      }
      
      if(choice==4)
        {
        System.out.print("You have to input a character(a-z) from keyboard and ");
        System.out.print("if the character is present in a word then it will automatically");
        System.out.print("   insert at that place");
        System.out.println(" you have only 6 chance to complete a word. \n");
        System.out.println("\t\t\t\t\t****************************");
        
      }
      if(choice==5) break;
      
    } 
    
  } //main
}   //class

/*
 File name in which name store =  hangman_file
 File name in which score store = score 
*/