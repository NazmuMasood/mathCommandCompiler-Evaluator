mathCommandCompiler-Evaluator ~ Java Compiler And Evaluator
=================================================

## Introduction
mathCommandCompiler-Evaluator is a mathematical command compiler and expression evaluator for java that checks for compilation error in java mathematical commands and also calculates expression. Demonstrates an object-oriented approach to parsing and handling expressions. The program creates postfix notation of the given infix expression and performs postfix expression evaluation. Handles both integer and double values.

## General view
```
 INPUT:
 int a=3, b=5,c=2;
 int d=a/b;
 b=b-1;
 double n1=13.2;
 double val=b^3+(17%a-5)*4-(b/c+2.5*(n1-b));
 
 OUTPUT:
 a=3
 b=4
 c=2
 d=0
 n1=13.2
 val=27.0

```
## Details about the program
 The program takes java input commands from a text(commandInput.txt) file. In the inputs, several mathematical variables
 i.e. 'integer' or 'double' are initialized and updated. The program returns the final value of all the 
 legitimate/recognized variables. If unknown/illegal input statement is found, it shows respective 
 compilation error. In case of error found in the expression of a variable, it designates "Compilation error"
 to that variable's value. Some sample inputs and outputs are given below for better understanding:
``` 
 INPUT:
 int a=3, b=5,c=2;
 int d=a*(b-c)^2;
 int e=a/b;
 
 b=b-1;
 double n1=13.2;
 double val=b^3+(17%a-5)*4-(b/c+2.5*(n1-b));
 
 OUTPUT:
 a=3 
 b=4 
 c=2 
 d=27 
 e=0 
 n1=13.2 
 val=27.0 
 ------
 INPUT:
 int a=3, b=5,c=2;
 
 b=b-1+u;
 double n1=13.2;
 double val=b^3+(17%a-5)*4-(b/c+2.5*(n1-b));
 
 OUTPUT:
 a=3 
 b=Compilation error 
 c=2 
 n1=13.2 
 val=Compilation error
 ------
 INPUT:
 int a=3, b=5,c=2;
 int a=10;
 
 OUTPUT:
 Duplicate variable found !!! Please try again...
 ------
 INPUT:
 int a=3, b=5,c=2;
 u=10;
 
 OUTPUT:
 Uninitialized variable found !!! Please try again... 
``` 
### Supported Operators
<table>
  <tr><th>Mathematical Operators</th></tr>
  <tr><th>Operator</th><th>Description</th></tr>
  <tr><td>+</td><td>Additive operator</td></tr>
  <tr><td>-</td><td>Subtraction</td></tr>
  <tr><td>*</td><td>Multiplication operator</td></tr>
  <tr><td>/</td><td>Division operator</td></tr>
  <tr><td>%</td><td>Modulus operator</td></tr>
  <tr><td>^</td><td>Power operator</td></tr>
</table>

## About used algorithm
 *The approach explained :*
 Initially variables' names,data types and values are stored in three different arrays who have 1-to-1
 between themselves. If values aren't directly given in input, then assumes the value to be '0'. Then whenever
 there's an expression found for the variable or a variable is updated, it adds the variable's index number
 to the 'work sequence'. It also saves the expression in the 'expression sequence'. The 'work sequence' and 
 the 'expression sequence' has 1-to-1 mapping between them. Finally, the program executes the work sequence
 one by one and the final values of variables are updated.
 <br>Input statements~>
```
 int a=3, b=5,c=2;
 int d=a*(b-c)^2;
 int e=a/b; 
 b=b-1;
 double n1=13.2;
 double val=b^3+(17%a-5)*4-(b/c+2.5*(n1-b));
```
<br>Visualization of the approach~>
``` 
 Identifiers :  
 a         b          c             d             e             n1                 val                     
 int       int        int           int           int           double             double                   
 3         5          2             0             0             13.2               0.0                     
 work sequence : 3  4  1  6   
 expression sequence : a*(b-c)^2  a/b  b-1  b^3+(17%a-5)*4-(b/c+2.5*(n1-b))
``` 
 *The algorithm used for getting postfix notation from infix notation :*
 <br>When a infix expression is given -->
 <br>1. Examine the next element in the input.
 <br>2. If it is operand, output it.
 <br>3.  If it is opening parenthesis, push it on stack.
 <br>4.  If it is an operator, then
  <br>  &nbsp;i. If stack is empty, push operator on stack.
  <br>  &nbsp;ii. If the top of stack is opening parenthesis, push operator on stack
  <br>  &nbsp;iii. If it has higher priority than the top of stack, push operator on stack.
  <br>  &nbsp;iv. Else pop the operator from the stack and output it, repeat step 4 
 <br>5.  If it is a closing parenthesis, pop operators from stack and output them until 
  an opening parenthesis is encountered. pop and discard the opening parenthesis.
 <br>6.  If there is more input go to step 1
 <br>7.  If there is no more input, pop the remaining operators to output.
 
  *The algorithm used for calculating postfix expressions :*
   <br>When a postfix expression is given -->
 - get the next element in the input
 - if it is an operand push it onto the stack
 - else if it is an operator
 - pop the stack for the right hand operand
 - pop the stack for the left hand operand
 - apply the operator to the two operands
 - push the result onto the stack
 - when the expression has been exhausted the result is the top (and only element) of the stack

### Notes
```
!!!The program supports only 'int' and 'double' data types
```

### Project Layout
The program was created and tested using Java 10.0.2.

## About author
 NAZMUDDIN AL MASOOD 
 <br> Contact: nazmumasood96@gmail.com

