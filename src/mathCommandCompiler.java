/**Author: NAZMUDDIN AL MASOOD - Contact:nazmumasood96@gmail.com 
  Date started:23/10/2018; Date finished:15/11/2018; Last edited:15/11/2018
  **/

/*                  ----- About the program ----
 The program takes java input commands from a text(commandInput.txt) file. In the inputs, several mathematical variables
 i.e. 'integer' or 'double' are initialized and updated. The program returns the final value of all the 
 legitimate/recognized variables. If unknown/illegal input statement is found, it shows respective 
 compilation error. In case of error found in the expression of a variable, it designates "Compilation error"
 to that variable's value.
 ------
 Input:
 int a=3, b=5,c=2;
 int d=a*(b-c)^2;
 int e=a/b;
 
 b=b-1;
 double n1=13.2;
 double val=b^3+(17%a-5)*4-(b/c+2.5*(n1-b));
 ------
 Output:
 a=3 
 b=4 
 c=2 
 d=27 
 e=0 
 n1=13.2 
 val=27.0 
 ------
 Input:
 int a=3, b=5,c=2;
 
 b=b-1+u;
 double n1=13.2;
 double val=b^3+(17%a-5)*4-(b/c+2.5*(n1-b));
 
 Output:
 a=3 
 b=Compilation error 
 c=2 
 n1=13.2 
 val=Compilation error
 ------
 Input:
 int a=3, b=5,c=2;
 int a=10;
 
 Output:
 Duplicate variable found !!! Please try again...
 ------
 Input:
 int a=3, b=5,c=2;
 u=10;
 
 Output:
 Uninitialized variable found !!! Please try again... 
 */

/*            ----- About used algorithms -----
 The approach explained :
 Initially variables' names,data types and values are stored in three different arrayLists('var','intDoubChk','num') 
 who have 1-to-1 mapping between themselves. If values aren't directly given in input, then assumes the value
 to be '0'. Then whenever there's an expression found for the variable or a variable is updated, it adds the 
 variable's index number to the arrayList 'work sequence'. It also saves the expression in another arrayList 
 'expression sequence'. The 'work sequence' and the 'expression sequence' also has 1-to-1 mapping between them. 
 Finally, the program executes the work sequence one by one i.e. works with the variable and it's given expression
 and the final values of variables are updated.
 ----
 Input statements~~~>
 int a=3, b=5,c=2;
 int d=a*(b-c)^2;
 int e=a/b;
 
 b=b-1;
 double n1=13.2;
 double val=b^3+(17%a-5)*4-(b/c+2.5*(n1-b));
 
 Visualization of the approach~~~>
 -(initially)-
 Identifiers :  
 a         b          c             d             e             n1                 val                     
 int       int        int           int           int           double             double                   
 3         5          2             0             0             13.2               0.0                     
 work sequence : 3  4  1  6   
 expression sequence : a*(b-c)^2  a/b  b-1  b^3+(17%a-5)*4-(b/c+2.5*(n1-b))
 
 -(finally after executing the work sequence and updating values)-
 Identifiers :  
 a         b          c             d             e             n1                 val                     
 int       int        int           int           int           double             double                   
 3         4          2             27            0             13.2               27.0                     
 ----
 
 ***The algorithm used for getting postfix notation from infix notation
 --> when a infix expression is given
 1)Examine the next element in the input.
 2)  If it is operand, output it.
 3)  If it is opening parenthesis, push it on stack.
 4)  If it is an operator, then
 i) If stack is empty, push operator on stack.
 ii) If the top of stack is opening parenthesis, push operator on stack
 iii) If it has higher priority than the top of stack, push operator on stack.
 iv) Else pop the operator from the stack and output it, repeat step 4 
 5)  If it is a closing parenthesis, pop operators from stack and output them until 
 an opening parenthesis is encountered. pop and discard the opening parenthesis.
 6)  If there is more input go to step 1
 7)  If there is no more input, pop the remaining operators to output.
 
 ***The algorithm used for calculating postfix expressions
 --> When a postfix expression is given
 -get the next element in the input
 -if it is an operand push it onto the stack
 -else if it is an operator
 -pop the stack for the right hand operand
 -pop the stack for the left hand operand
 -apply the operator to the two operands
 -push the result onto the stack
 -when the expression has been exhausted the result is the top (and only element) of the stack
 */

/* Methods used : main(), analyzer(), makePostV(), calDoublePV(), calIntPV(), letsWork(), precedenceLevel()*/

import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class mathCommandCompiler{
  
  static String sTotal = "";//Contains all the given inputs in a concatanated string
  static ArrayList<String> eachStrCommand = new ArrayList<String>();//Contains each line of input commands
  
  static int intChk=0,doubChk=0,duplicateVar=0,uninitializedVar=0,notAStatement=0; static String valid="";
  static ArrayList<String> var = new ArrayList<String>();  
  static ArrayList<String> intDoubChk = new ArrayList<String>();
  static ArrayList<String> num = new ArrayList<String>();
  static ArrayList<Integer> workSeq = new ArrayList<Integer>();
  static ArrayList<String> exprSeq = new ArrayList<String>();
  
  public static void main (String[]args) throws FileNotFoundException{
    //Reading the lines of inputs
    File file = new File("commandInput.txt"); 
    Scanner sc = new Scanner(file);   
    ArrayList<String> inputStr = new ArrayList<String>(); 
    while (sc.hasNextLine()){ 
      String l = sc.nextLine();
      l = l.replaceAll("\\s","");
      inputStr.add(l);
    }
    int inputStrLength = inputStr.size();
    for(int i=0;i<inputStrLength;i++){
      sTotal = sTotal+inputStr.get(i);;
    }
    
    //Tokenizing on the basis of ";"
    if(!sTotal.contains(";")){System.out.println("Wrong input ! Please check the inputs again... ");}
    if(sTotal.contains(";")){
      StringTokenizer st = new StringTokenizer(sTotal,";");    
      int t = st.countTokens();
      for (int i=0;i<t;i++){
        String strCommand = st.nextToken();
        eachStrCommand.add(strCommand);
      }
    }
    
//    //Gives visualization about the inputs     
//    for(int i=0;i<eachStrCommand.size();i++){
//      System.out.println(eachStrCommand.get(i)+";");
//    }    
//    for(int i=0;i<3;i++){
//      System.out.println(" ");//Printing gaps
//    }
    
    //Analyzing done : got the variable names,got their data types
    //know whether to directly store values or calculate(work) values from expression
    for(int i=0;i<eachStrCommand.size();i++){
      intChk=0;doubChk=0;
      String s1 = eachStrCommand.get(i);      
      if(s1.contains("int")){intChk=1;s1 = s1.replaceAll("int","");}
      if(s1.contains("double")){doubChk=1;s1 = s1.replaceAll("double","");}
      analyzer(s1);
    }
    
    if(duplicateVar==1){System.out.println("Duplicate variable found !!! Please check the inputs again...");}
    if(uninitializedVar==1){System.out.println("Uninitialized variable found !!! Please check the inputs again...");}
    if(notAStatement==1){System.out.println("Unknown statement found !!! Please check the inputs again...");}
    
    //If no duplicate variable and uninitialized variable found, then complete calculation of expressions
    if(duplicateVar==0&&uninitializedVar==0&&notAStatement==0){
      
//      //Gives visualization about how the code saves values initially and works with expressions   
//      System.out.print("Identifiers : ");
//      System.out.println();
//      for(int i=0;i<var.size();i++){System.out.print(var.get(i)+"                    ");}
//      System.out.println();
//      for(int i=0;i<intDoubChk.size();i++){System.out.print(intDoubChk.get(i)+"                  ");}
//      System.out.println();
//      for(int i=0;i<num.size();i++){System.out.print(num.get(i)+"                    ");}
//      System.out.println();System.out.print("work sequence : ");
//      for(int i=0;i<workSeq.size();i++){System.out.print(workSeq.get(i)+"  ");}
//      System.out.println();System.out.print("expression sequence : ");
//      for(int i=0;i<exprSeq.size();i++){System.out.print(exprSeq.get(i)+"  ");}
//      
//      for(int i=0;i<3;i++){
//        System.out.println(" ");//Printing gaps
//      }
      
      //Executing the work sequence...Finally!!
      letsWork();
      
      //Printing the variable's values (final outputs)
      for(int i=0;i<var.size();i++){
        System.out.println(var.get(i)+"="+num.get(i));
      }
    }    
  }
  
//Analyzes the inputs and retrieves values of variables, identifies their data type, saves their mathematical expressions  
  public static int analyzer(String s){  
    //Checking for Comma
    if(s.contains(",")){
      StringTokenizer st = new StringTokenizer(s,",");    
      int t = st.countTokens();
      for (int i=0;i<t;i++){
        String s1 = st.nextToken();
        analyzer(s1);
      }return 0;
    }    
    
    //Checking for "="
    if(s.contains("=")){
      StringTokenizer st = new StringTokenizer(s,"=");      
      String s1 = st.nextToken();  
      
      int count=0; int index=0;
      for(int i=0;i<var.size();i++){
        if(s1.equals(var.get(i))){count++;index=i;break;}
      }
      if((intChk==1||doubChk==1)&&count==1){duplicateVar=1;return 0;}
      if(count==0){var.add(s1);}
      if(count==1){
        workSeq.add(index);exprSeq.add(st.nextToken());
        return 0;
      }
      
      if(intChk==0&&doubChk==0&&count==0){uninitializedVar=1;var.remove(var.size()-1);return 0;}
      if(intChk==1){
        intDoubChk.add("int");      
      }
      if(doubChk==1){
        intDoubChk.add("double");      
      }
      
      String s2 = st.nextToken();
      if(s2.matches(".*[a-zA-Z].*")||s.matches(".*[-+*/^%].*")){
        workSeq.add(var.size()-1);
        exprSeq.add(s2);
        
        if(intChk==1){
          num.add("0");
        }
        if(doubChk==1){
          num.add("0.0");
        }
      }
      else{
        num.add(s2);
      }
      return 0;
    }
    
    //checking for un-initialized variables
    if(s.matches("[a-zA-Z].*")){
      int count=0; int index=0;
      for(int i=0;i<var.size();i++){
        if(s.equals(var.get(i))){count++;index=i;break;}
      }
      if((intChk==1||doubChk==1)&&count==1){duplicateVar=1;return 0;}
      if(intChk==0&&doubChk==0&&count==0){uninitializedVar=1;return 0;}
      if(intChk==0&&doubChk==0&&count==1){notAStatement=1;return 0;}
      if(count==0){
        if(intChk==1){
          var.add(s);
          intDoubChk.add("int");
          num.add("0");
        }
        if(doubChk==1){
          var.add(s);
          intDoubChk.add("double");
          num.add("0.0");
        }
      }
      return 0;
    }
    
    return 0;      
  }
  
//"makePostV" method produces postfix expression   
  public static ArrayList<String> makePostV(String s){
    String sD = s+"$";
    Stack<String> st1 = new Stack<String>();//Operators and parenthesis are stored temporarily on string stack "st1"
    ArrayList<String> postV = new ArrayList<String>();
    int p=0;//Pointer for each character in the expression; Gets incremented 
    
    while(p<s.length()){
      int count = 0;//Count for making sure only one of the following "if"s are executed in one iteration     
      String s1=Character.toString(sD.charAt(p));
      
      //this portion enables the program succesfully identify variables with length more than 'one' i.e.
      //'abad','temp' instead of 'a','b','t'
      String sNext = Character.toString(sD.charAt(p+1));
      if(Character.toString(s1.charAt(s1.length()-1)).matches("[a-zA-Z].*")||Character.toString(s1.charAt(s1.length()-1)).matches("-?\\d+(\\.\\d+)?")){
        while(!sNext.matches(".*[-+*/^%$()].*")){
          p++;
          s1=s1+sNext;
          sNext=Character.toString(sD.charAt(p+1));
        }
      }
      
      //For identifiers in the expression      
      if((s1.matches("[a-zA-Z].*"))&&count==0){postV.add(s1);count++;}
      
      //For numerical values given directly in the expression
      if(s1.matches("-?\\d+(\\.\\d+)?")&&count==0){postV.add(s1);count++;}
      
      //For parenthesis in the expression
      if((s1.equals("(")||s1.equals(")"))&&count==0){ 
        if(s1.equals("(")){st1.push("(");}
        if(s1.equals(")")){
          while(!st1.peek().equals("(")&&!st1.empty()){String currOp = st1.pop();postV.add(currOp);}
          st1.pop();
        }count++;
      }  
      
      //For operators in the expression
      if(s1.equals("+")||s1.equals("-")||s1.equals("%")||s1.equals("/")||s1.equals("*")||s1.equals("^")){     
        if(st1.empty()&&count==0){st1.push(s1);count++;}
        
        if(!st1.empty()&&count==0){
          if(st1.peek().equals("(")){st1.push(s1);count++;}          
          
          String s2=st1.peek();
          char op1 = s2.charAt(0);
          char op2=s1.charAt(0);       
          
          if(count==0){
            while((precedenceLevel(op1)>=precedenceLevel(op2))&&!st1.empty()){
              String currOp=st1.pop();            
              postV.add(currOp);
              if(!st1.empty()&&st1.peek().equals("(")){break;}
              if(!st1.empty()){s2=st1.peek();op1 = s2.charAt(0);}
            } st1.push(s1);count++;      
          }
          
          if((precedenceLevel(op1)<precedenceLevel(op2))&&count==0){
            st1.push(s1);
            count++;
          }          
        }     
      }      
      p++;  
    }
    
    //At the end pops operators if remained in the stack
    while(!st1.empty()){
      String currOp=st1.pop();
      postV.add(currOp);
    }  
    return postV;//Returns the postfix expression
  }
  
//"calPV" method calculates postfix expression of a "double"
  public static double calDoublePV(ArrayList<String> arL){
    ArrayList<String> postV = new ArrayList<String>();
    postV = arL;
    Stack<String> st2 = new Stack<String>();//Operands are stored temporarily on string stack st2
    double summ=0; //Stores the answer to the expression 
    
    for(int i=0;i<postV.size();i++){
      String s = postV.get(i);
      
      //Deals with the operands (identifiers and numerical values) in the expressions
      //If operand, push them in the stack
      if(s.matches("[a-zA-Z].*")||s.matches("-?\\d+(\\.\\d+)?")){st2.push(s);}
      
      //Deals with the operators in the expression
      //If operator, pop two operands from the stack
      else{
        double n1=0,n2=0;int index1=-1,index2=-1;
        String ss2 = st2.pop();
        String ss1 = st2.pop();
        
        //If the operands are identifiers
        if(!ss1.matches("-?\\d+(\\.\\d+)?")){
          for(int j=0;j<var.size();j++){
            if(ss1.equals(var.get(j))){
              index1=j;
              break;
            }
          }
          if(intDoubChk.get(index1).equals("int")){
            String nTemp= num.get(index1);
            if(nTemp=="Compilation error"){valid="false";return 0;}
            n1=Integer.parseInt(nTemp);
          }
          if(intDoubChk.get(index1).equals("double")){
            String nTemp= num.get(index1);
            if(nTemp=="Compilation error"){valid="false";return 0;}
            n1=Double.parseDouble(nTemp);
          }
        }        
        if(!ss2.matches("-?\\d+(\\.\\d+)?")){
          for(int j=0;j<var.size();j++){
            if(ss2.equals(var.get(j))){
              index2=j;
              break;
            }
          }
          if(intDoubChk.get(index2).equals("int")){
            String nTemp= num.get(index2);
            if(nTemp=="Compilation error"){valid="false";return 0;}
            n2=Integer.parseInt(nTemp);
          }
          if(intDoubChk.get(index2).equals("double")){
            String nTemp= num.get(index2);
            if(nTemp=="Compilation error"){valid="false";return 0;}
            n2=Double.parseDouble(nTemp);}
        }
        
        //If the operands are numerical values
        if(ss1.matches("-?\\d+")){ n1=Integer.parseInt(ss1);}
        if(ss1.matches("-?\\d+(\\.\\d+)?")){ n1=Double.parseDouble(ss1);}
        
        if(ss2.matches("-?\\d+")){ n2=Integer.parseInt(ss2);}
        if(ss2.matches("-?\\d+(\\.\\d+)?")){ n2=Double.parseDouble(ss2);}
        
        //Calculates the operands by the operator
        if(s.equals("+")){summ=n1+n2;st2.push(Double.toString(summ));}
        if(s.equals("-")){summ=n1-n2;st2.push(Double.toString(summ));}
        if(s.equals("/")){summ=n1/n2;st2.push(Double.toString(summ));}
        if(s.equals("*")){summ=n1*n2;st2.push(Double.toString(summ));}
        if(s.equals("%")){summ=n1%n2;st2.push(Double.toString(summ));} 
        if(s.equals("^")){summ=Math.pow(n1, n2);st2.push(Double.toString(summ));}        
      }            
    } 
    
    //For expression like "b=a" or "b=20.5" (later modification after initialization)
    if(!st2.empty()){
      String ss1 = st2.peek();
      if(ss1.matches("[a-zA-Z].*")){
        int index1=-1;
        for(int j=0;j<var.size();j++){
          if(ss1.equals(var.get(j))){
            index1=j;
            break;
          }
        }
        String nTemp= num.get(index1);
        if(nTemp=="Compilation error"){valid="false";return 0;}
        summ=Double.parseDouble(nTemp);
      }
      if(ss1.matches("-?\\d+(\\.\\d+)?")){summ=Double.parseDouble(ss1);}
    }
    
    return summ; //Returns the answer to the expression
  }  
  
//"calPV" method calculates postfix expression of a "int"  
  public static int calIntPV(ArrayList<String> arL){
    ArrayList<String> postV = new ArrayList<String>();
    postV = arL;
    Stack<String> st2 = new Stack<String>();//Operands are stored temporarily on string stack st2
    int summ=0; //Stores the answer to the expression 
    
    for(int i=0;i<postV.size();i++){
      String s = postV.get(i);
      
      //Deals with the operands (identifiers and numerical values) in the expressions
      //If operand, push them in the stack
      if(s.matches("[a-zA-Z].*")||s.matches("-?\\d+(\\.\\d+)?")){st2.push(s);}
      
      //Deals with the operators in the expression
      //If operator, pop two operands from the stack
      else{
        int n1=0,n2=0;int index1=-1,index2=-1;
        String ss2 = st2.pop();
        String ss1 = st2.pop();
        
        //If the operands are identifiers
        if(!ss1.matches("-?\\d+(\\.\\d+)?")){
          for(int j=0;j<var.size();j++){
            if(ss1.equals(var.get(j))){
              index1=j;
              break;
            }
          }
          String nTemp= num.get(index1);
          if(nTemp=="Compilation error"){valid="false";return 0;}
          n1=Integer.parseInt(nTemp);
        }        
        if(!ss2.matches("-?\\d+(\\.\\d+)?")){
          for(int j=0;j<var.size();j++){
            if(ss2.equals(var.get(j))){
              index2=j;
              break;
            }
          }
          String nTemp= num.get(index2);
          if(nTemp=="Compilation error"){valid="false";return 0;}
          n2=Integer.parseInt(nTemp);
        }
        
        //If the operands are numerical values
        if(ss1.matches("-?\\d+(\\.\\d+)?")){ n1=Integer.parseInt(ss1);}
        if(ss2.matches("-?\\d+(\\.\\d+)?")){ n2=Integer.parseInt(ss2);}
        
        //Calculates the operands by the operator
        if(s.equals("+")){summ=n1+n2;st2.push(Integer.toString(summ));}
        if(s.equals("-")){summ=n1-n2;st2.push(Integer.toString(summ));}
        if(s.equals("/")){summ=n1/n2;st2.push(Integer.toString(summ));}
        if(s.equals("*")){summ=n1*n2;st2.push(Integer.toString(summ));}
        if(s.equals("%")){summ=n1%n2;st2.push(Integer.toString(summ));} 
        if(s.equals("^")){summ=(int) Math.pow(n1, n2);st2.push(Integer.toString(summ));}             
      }            
    }
    
    //For expression like "b=a" or "b=20" (later modification after initialization)
    if(!st2.empty()){
      String ss1 = st2.peek();
      if(ss1.matches("[a-zA-Z].*")){
        int index1=-1;
        for(int j=0;j<var.size();j++){
          if(ss1.equals(var.get(j))){
            index1=j;
            break;
          }
        }
        String nTemp= num.get(index1);summ=Integer.parseInt(nTemp);
      }
      if(ss1.matches("-?\\d+")){summ=Integer.parseInt(ss1);}
    }
    
    return summ; //Returns the answer to the expression
  }
  
//Executes the work sequence
  public static void letsWork(){
    for(int i=0;i<workSeq.size();i++){
      int j= workSeq.get(i);
      String tempExpr = exprSeq.get(i);
      
      //Checking for invalid expression format or unknown variable : Compilation error
      String sD = tempExpr+"$";
      int matched = 0; int p = 0; 
      while(p<tempExpr.length()){
        int count = 1; 
        String sTemp=Character.toString(tempExpr.charAt(p));
        
        //this portion enables the program succesfully identify variables with length more than 'one' i.e.
        //'abad','temp' instead of 'a','b','t'
        String sNext = Character.toString(sD.charAt(p+1));
        if(Character.toString(sTemp.charAt(sTemp.length()-1)).matches("[a-zA-Z].*")||Character.toString(sTemp.charAt(sTemp.length()-1)).matches("-?\\d+(\\.\\d+)?")){
          while(!sNext.matches(".*[-+*/^%$()].*")){
            p++;
            sTemp=sTemp+sNext;
            sNext=Character.toString(sD.charAt(p+1));
          }
        }
        
        if(sTemp.matches("[a-zA-Z].*")){
          count=0;          
          for(int k=0;k<var.size();k++){
            if(sTemp.equals(var.get(k))){count=1;}
          }
          if(count==0){matched=0;break;}
          if(count==1){matched=1;}
        }
        if(sTemp.matches("-?\\d+(\\.\\d+)?")){matched=1;}
        if(sTemp.matches(".*[-+*/^$%()].*")){
          if(sTemp.equals(")")){matched=1;}
          else{matched=0;}
        }
        p++;
      }
      valid = "";
      if(matched==0){valid="false";}
      if(matched==1){valid="true";}
      
      ArrayList<String> postV= makePostV(tempExpr);
      if(intDoubChk.get(j).equals("int")&&valid=="true"){
        String n = Integer.toString(calIntPV(postV)); 
        num.set(j,n);
      }
      if(intDoubChk.get(j).equals("double")&&valid=="true"){
        String n = Double.toString(calDoublePV(postV));
        num.set(j,n);
      }
      if(valid=="false"){num.set(j,"Compilation error");}
    }
  }  
  
//"precendenceLevel" method to compare operator's precendence
  static int precedenceLevel(char op) {
    switch (op) {
      case '+':
      case '-':
        return 0;
      case '*':
      case '/':
      case '%':  
        return 1;
      case '^':
        return 2;
      default:
        throw new IllegalArgumentException("Operator unknown: " + op);
    }
  } 
}