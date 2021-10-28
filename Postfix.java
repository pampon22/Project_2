// CS321 Project 2
// Coded by Jackie Himel, reviewed by Philip

// reads a set of infix expressions from a file 
// then writes infix and postfix expressions into another file

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class Postfix {

	// (higher val goes to higher operator of higher precedence)
	// static int Supererior(char ch){

	public static boolean correctChar (char ch){

		if(ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch == '^'){

			return true;
		}

		System.out.println("Invalid input. Please try again");
		return false;
	}

	static int higherValOperator(char ch){

		switch(ch){

			case '^':
				return 3;
			
			case '*':
			case'/':
				return 2;

			case '-':
			case'+':
				return 1; 
		}
		return -1;
	}

	// static int higherValue (char ch){

	//         switch(ch){

	//         case '-':
	//         case '+':
	//             return 1;

	//         case '/':
	//         case '*':
	//             return 2;
			
	//         case '^':
	//             return 3;
	//     }  

	//     return -1; 
	// }

	// static String infixToPostfix (String input){
	//     String postfix = new String("");

	//     LinkedListStack<String> stack = new LinkedListStack<String>();

	//     try {

	//         /*
	//         Uses BufferedReader(and FileReader) to 
	//         read the file in one line at a time
	//         */
	//         FileReader reader = new FileReader(filename);
	//         BufferedReader buffered = new BufferedReader(reader);

	//         //String that holds each line one at a time
	//         String token = buffered.read();

	//         while (!(token.equals(";"))) {

	//             if (token.equals(")")) {
	//                 String right = stack.pop();
	//                 String oper = stack.pop();
	//                 String left = stack.pop();
	//                 stack.push(left + right + oper);
	//             }
	//             else {
	//                 if (!(token.equals("("))) {
	//                     stack.push(token);
	//                 }
	//             }
	//             token = buffered.read();
	//         }
			


	//         buffered.close();
	//     }

	//     //Catches errors
	//     catch (FileNotFoundException e) {
	//         System.out.println("Unable to open file " + filename);
	//     }
	
	//     catch(IOException ex) {
	//         System.out.println("Error reading file " + filename);
	//     }

		

	//     for(int i=0; i < input.length(); ++i){

	//         char charr = input.charAt(i);

	//         if(correctChar(input.charAt(i))){

	//             postfix += charr;
	//         }

	//         else if( charr == '('){
	//             stack.push(charr);
	//         }

	//         else if(charr == ')'){

	//             while(!stack.isEmpty() && stack.peek() != '('){

	//                 postfix += stack.pop();
	//                 stack.pop();
	//             }
	//         }

	//         else{

	//             while(!stack.isEmpty() && higherValOperator(charr) <= higherValOperator(stack.peek())){

	//                 postfix += stack.pop();
	//             }
				
	//             stack.push(charr);
	//         }

	//         while(!stack.isEmpty()){

	//             if(stack.peek() == '('){

	//                 return "Invalid input.";
	//             }

	//             return postfix;
	//         }
	//     }
	// }

			//     int popper1 = stack.pop();
			//     int popper2 = stack.pop();

			//     switch(input.charAt(i)){

			//         case '+': 
			//             stack.push(popper2 + popper1);
					
			//         case '-': 
			//             stack.push(popper2 - popper1);
					
			//         case '/':
			//             stack.push(popper2 / popper1);
					
			//         case '*':
			//             stack.push(popper2 * popper1);

			// //     }

			//     else if(charr == '('){
			//         stack.push(charr);
			//     }
			
			//     else if(charr == ')'){

			//         while(!stack.isEmpty() && stack.peek() != '('){

			//             postfix += stack.pop();

			//             stack.pop();
			//         }
			//     }

			//     else{
			//         postfix += stack.pop();
			//         stack.push(charr);
			//     }
		   
			// }

	
	// while (!stack.isEmpty()){

	//     if(stack.peek() == '('){

	//         return "Input Not Accepted";
	//     }

	//     postfix += stack.pop();
	// }

	// return postfix;

	public static String infixToPostfix(String expr) {
		// create llStack
		LinkedListStack<String> postStack = new LinkedListStack<String>();
		String [] tokens = expr.split(" ");
		
		for (int i=0; i < tokens.length-1; i++) {	

			// while (!(tokens[i].equals(";"))) {

				if (tokens[i].equals(")")) {
					String right = postStack.pop();
					String oper = postStack.pop();
					String left = postStack.pop();
					postStack.push( left + " " + right + " " + oper);
				}
				else {
					if (!(tokens[i].equals("("))) {
						postStack.push(tokens[i]);
					}
				}
			// }
		}

		// Top of stack is a postfix expression
		return postStack.peek();
		
	}


	public static void writeToFile(String filename, String newfile) {
		try {
			// read file
			FileReader reader = new FileReader(filename);
			BufferedReader buffered = new BufferedReader(reader);

			String expr = buffered.readLine();

			// file writing 
			FileWriter writer = new FileWriter(newfile);            

			while(expr!=null) {
				String infixToWrite = "Infix Expression : " + expr + "\n";
				String postToWrite = "Postfix Expression : " + infixToPostfix(expr) + " ;" + "\n";
				writer.write(infixToWrite + postToWrite);
				expr = buffered.readLine();
			}
			buffered.close();
			writer.close();
		}
			
		// error catching
		catch(FileNotFoundException e) {
			System.out.println("Unable to open file " + filename);
		}

		catch(IOException ex) {
			System.out.println("Error reading file " + filename);
		}
	}
	
	// int evaluate(String right, String t, String left) {
	// 	if(t.equals ('+')){
	// 		return(left + right);
	// 	}
	
	// 	else if(t.equals('-')){
	// 		return(left - right);
	// 	}
	
	// 	else if(t.equals('*')){
	// 		return(left * right);
	// 	}
	
	// 	else if(t.equals( '/')){
	// 		return(left/right);
	// 	}
	
	
	// 	if(t.equals('=')){
	// 		//
	// 	}
	// }




	public static void main(String[] args){
		String input = "( AX + ( B * C ) ) ;";
		String ex = "( ( A + ( B * C ) ) / ( D - E ) ) ;";
		String infix = "( ( H * ( ( ( ( A + ( ( B + C ) * D ) ) * F ) * G ) * E ) ) + J ) ;";
		String ex1 = "( ( AX + ( BY * C ) ) / ( D4 - E ) ) ;";
		System.out.println(infixToPostfix(input));
		System.out.println(infixToPostfix(infix));
		System.out.println(infixToPostfix(ex));
		System.out.println(infixToPostfix(ex1));


		writeToFile("data3-1.txt", "postfix-file.txt");
	}
}