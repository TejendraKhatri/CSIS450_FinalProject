//Author : Tejendra Khatri
//Date : 5/15/2019
//Final Project
//Description : This program works as a Lexical Analyzer.

import java.io.*;
import java.util.*;
import java.io.IOException; 
public class Front
{
    //INITIALIZING THE CONSTANTS
   private static final int LETTER=0;
   private static final int DIGIT=1;
   private static final int UNKNOWN=99;
   private static final int EOF=-1;
   private static final int INT_LIT=10;
   private static final int IDENT=11;
   private static final int ASSIGN_OP=20;
   private static final int ADD_OP=21;
   private static final int SUB_OP=22;
   private static final int MULT_OP=23;
   private static final int DIV_OP=24;
   private static final int LEFT_PAREN=25;
   private static final int RIGHT_PAREN=26;
   private static final int PERCENTAGE_OP=27;
   private static final int LESSTHAN_OP=28;
   private static final int GREATERTHAN_OP=29;
   private static final int SIGNED_LEFT_SHIFT_OP=30;
   private static final int SIGNED_RIGHT_SHIFT_OP=31;
   private static final int UNSIGNED_RIGHT_SHIFT_OP=32;
   private static final int LESSTHAN_EQUAL_OP = 33;
   private static final int EQUAL_OP=34;
   private static final int NOTEQUAL_OP=35;
   private static final int GREATERTHAN_EQUAL_OP=36;
   private static final int AND_OP=37;
   private static final int OR_OP=38;
   private static final int PLUS_EQUAL_OP=39;
   private static final int SUB_EQUAL_OP=40;
   private static final int MUL_EQUAL_OP=41;
   private static final int DIV_EQUAL_OP=42;
   


   private static int charClass;
   private static char lexeme[]=new char[100];   //an array to store char 
   private static char nextChar;
   private static int lexLen;
   private static int nextToken=0;
   private static File in_fp;
   private static FileInputStream fopen;
   
   public static void main(String args[])
   {
       
       
       in_fp = new File("front.in");              //OPening the file
       
       if (!in_fp.exists())        //Check to see if the file exists
       {
             System.out.println( "front.in does not exist.");
             return;
       }
       
       try
       {
             fopen = new FileInputStream(in_fp);
             getChar();
              do
	      {
                 lex();
                 expr();
	      }
              while((nextToken!= EOF));
                
       }
       catch (IOException e)
       {
             e.printStackTrace();
       }
   }
   
   //Description : this function checks to see the character type and 
   //               assigns next token its value.
   //Preconditions : none
   //Postconditions : nextToken has been populated
   //Method input : char 
   //Method output  : none
   public static int lookup(char ch)   //check to see what the character is and return the nextToken
   {
       switch (ch)
       {
        case '(':
            addChar();
            nextToken = LEFT_PAREN;
            break;
        case ')':
            addChar();
            nextToken = RIGHT_PAREN;
            break;
        case '+':
            addChar();
            nextToken = ADD_OP;
            break;
        case '-':
            addChar();
            nextToken = SUB_OP;
            break;
        case '*':
            addChar();
            nextToken = MULT_OP;
            break;
        case '/':
            addChar();
            nextToken = DIV_OP;
            break;
        case '%':
            addChar();
            nextToken = PERCENTAGE_OP;
            break;
        default:
            addChar();
            //System.out.println("here in lookup");
            nextToken = EOF;
            break;
       }
       return nextToken;
   }
   
   //Description : adds the character to the lexeme array
   //Preconditions : none
   //Postconditions : lexeme array has been populated
   //Method input : none 
   //Method output  : none
   public static void addChar() 
   {
        if (lexLen <= 98)
        {
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
        }
        else
           System.out.println("Error -lexeme is too long\n");
   }
   
   //Description : reads the next character from the file
   //               and populates the charClass
   //Preconditions : none
   //Postconditions : charClass has been populated
   //Method input : none 
   //Method output  : none
   public static void getChar()    //reads the char from the file and updates charClass
   {
       try
       {
       if(fopen.available()>0)      //check to see if it is the end of file
       {
           nextChar=(char)fopen.read();   
           if(Character.isLetter(nextChar))   //check to see if the char is a letter
               charClass=LETTER;
           else if(Character.isDigit(nextChar))  //check to see if the char is a letter
               charClass=DIGIT;
           else if(nextChar=='<')  
               charClass=LESSTHAN_OP;
           else if(nextChar =='=')
                charClass = EQUAL_OP;
           else if(nextChar =='!')
                charClass = NOTEQUAL_OP;
           else if(nextChar =='>')
                charClass = GREATERTHAN_OP;
           else if(nextChar =='&')
                charClass = AND_OP;
           else if(nextChar =='|')
                charClass = OR_OP;
           else if(nextChar =='+')
                charClass = ADD_OP;
           else if(nextChar =='-')
                charClass = SUB_OP;
           else if(nextChar =='*')
                charClass = MULT_OP;
           else if(nextChar =='/')
                charClass = DIV_OP; 
           else
               charClass=UNKNOWN;
       }
       else
           {charClass=EOF; 
           //System.out.println("EOF");
           }
       }
       catch(IOException e)
       {
           e.printStackTrace();
       }
   }
   
   //Description : reads characters until a nonblank char is read
   //Preconditions : none
   //Postconditions : none
   //Method input : none 
   //Method output  : none
   public static void getNonBlank()  //ignores whitespace and newlines to read form the textfile
   {
   try{
       while(Character.isSpaceChar(nextChar) || (nextChar == '\n')){
           if(fopen.available()>0){
           getChar();}
           else {
           charClass = EOF;
           //System.out.println("in nonBlank");
           break;}
           }
   }
   catch (IOException e){e.printStackTrace();}
   }
   
   //Description : function to keep track of entering and exiting expr
   //Preconditions : none
   //Postconditions : none
   //Method input : none 
   //Method output  : none
   public static void expr()
   {
        System.out.print("Enter <expr>\n");
        term();
        while((nextToken == ADD_OP || nextToken == SUB_OP /* || nextToken == LESSTHAN_EQUAL_OP) ||
        (nextToken == GREATERTHAN_EQUAL_OP || nextToken == EQUAL_OP || nextToken == NOTEQUAL_OP || nextToken == GREATERTHAN_OP)||
        (nextToken == LESSTHAN_OP || nextToken == SIGNED_LEFT_SHIFT_OP || nextToken == SIGNED_RIGHT_SHIFT_OP || nextToken == UNSIGNED_RIGHT_SHIFT_OP  */))
        {
            lex();
            term();
        }
        System.out.printf("Exit <expr>\n");
   }
   

   //Description : function to keep track of entering and exiting term
   //Preconditions : none
   //Postconditions : none
   //Method input : none 
   //Method output  : none
   public static void term()
   {
        System.out.print("Enter <term>\n");
        factor();
        while ((nextToken == MULT_OP || nextToken == DIV_OP || nextToken == PERCENTAGE_OP ))
        {
            lex();
            factor();
        }
        System.out.print("Exit <term>\n");
   }
   
   //Description : function to keep track of entering and exiting factor
   //Preconditions : none
   //Postconditions : none
   //Method input : none 
   //Method output  : none
   public static void factor()
   {
        System.out.print("Enter <factor>\n");
        if(nextToken==IDENT || nextToken == INT_LIT)
        {lex();}
        else
        {
            if(nextToken == LEFT_PAREN)
            {
                lex();
                expr();
                if(nextToken == RIGHT_PAREN)
                {lex();}
                else
                {System.out.println("ERROR 1");}
            }
            else
            {System.out.println("ERROR 2");}
        }
        System.out.print("Exit <factor>\n");
   }
   
   //Description : lexical analyzer
   //Preconditions : none
   //Postconditions : none
   //Method input : none 
   //Method output  : none
   public static int lex()
   {
        lexLen = 0;
        getNonBlank();
        //System.out.println("charclass: "+charClass);
        switch (charClass)
        {
            case EOF:
               //System.out.print("here in eof");
               nextToken = EOF;
               lexLen=4;
               lexeme[0] = 'E';
               lexeme[1] = 'O';
               lexeme[2] = 'F';
               lexeme[3] = 0;
               break;
        
            case LETTER:
                addChar();
                getChar();
                while (charClass == LETTER || charClass == DIGIT)
                {
                   addChar();
                   getChar();
               }
                nextToken = IDENT;
                break;
                
            case DIGIT:
                addChar();
                getChar();
               while(charClass == DIGIT)
               {
                    addChar();
                    getChar();
               }
               nextToken = INT_LIT;
               break;

            case LESSTHAN_OP:
               addChar();
               getChar();
               int ct =1;
               if(charClass==EQUAL_OP)
                   {
                       addChar();
                       getChar();
                       nextToken = LESSTHAN_EQUAL_OP;
                       ct = -1;
                   }
               while(charClass == LESSTHAN_OP)
               {
                   addChar();
                   getChar();
                   ct++;
               }
               if(ct==1)
               {
                    nextToken = LESSTHAN_OP;
               }
               else if(ct == 2)
               {
                   nextToken = SIGNED_LEFT_SHIFT_OP;
               }
               break;
            
            case EQUAL_OP:
               addChar();
               getChar();
               while(charClass == EQUAL_OP)
               {
                   addChar();
                   getChar();
               }
               nextToken = ASSIGN_OP;
               break;

            case NOTEQUAL_OP:
               addChar();
               getChar();
               if(charClass == EQUAL_OP)
               {
                   addChar();
                   getChar();
               }
               nextToken = NOTEQUAL_OP;
               break;

            case GREATERTHAN_OP:
               addChar();
               getChar();
               int ct1 =1;
               if(charClass==EQUAL_OP)
                   {
                       addChar();
                       getChar();
                       nextToken = GREATERTHAN_EQUAL_OP;
                       ct1 = -1;
                   }
               while(charClass == GREATERTHAN_OP)
               {
                   addChar();
                   getChar();
                   ct1++;
               }
               if(ct1==1)
               {nextToken=GREATERTHAN_OP;}
               else if(ct1==2)
               {nextToken=SIGNED_RIGHT_SHIFT_OP;}
               else if(ct1==3)
               {nextToken=UNSIGNED_RIGHT_SHIFT_OP;}
               break;

            case AND_OP:
               addChar();
               getChar();
               while(charClass == AND_OP)
               {
                   addChar();
                   getChar();
               }
               nextToken = AND_OP;
               break;

            case OR_OP:
               addChar();
               getChar();
               while(charClass == OR_OP)
               {
                   addChar();
                   getChar();
               }
               nextToken = OR_OP;
               break;
            
            case ADD_OP:
               addChar();
               getChar();
               nextToken = ADD_OP;
               if(charClass==EQUAL_OP)
               {
                   addChar();getChar();
                   nextToken = PLUS_EQUAL_OP;
               }
               break;
           case SUB_OP:
               addChar();
               getChar();
               nextToken = SUB_OP;
               if(charClass==EQUAL_OP)
               {
                   addChar();getChar();
                   nextToken = SUB_EQUAL_OP;
               }
               break;
           case MULT_OP:
               addChar();
               getChar();
               nextToken = MULT_OP;
               if(charClass==EQUAL_OP)
               {
                   addChar();getChar();
                   nextToken = MUL_EQUAL_OP;
               }
               break;

           case DIV_OP:
               addChar();
               getChar();
               nextToken = DIV_OP;
               if(charClass==EQUAL_OP)
               {
                   addChar();getChar();
                   nextToken = DIV_EQUAL_OP;
               }
               break; 

            case UNKNOWN:
            //System.out.print("here in unknown");
                lookup(nextChar);
                getChar();
                break;
            

       }
      // if(charClass==EOF)
        //  nextToken=EOF;
       if(nextToken==EOF)
            System.out.println("Next token is :"+nextToken+" Next lexeme is "+"EOF");
       else
       {
        System.out.print("Next token is :"+nextToken+" Next lexeme is ");
        for(int i=0;i<lexLen;i++)
           System.out.print(lexeme[i]);
       System.out.println();
       }
       
       
       return nextToken;
   }
   
}
