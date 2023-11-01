package com.company;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        ArrayList<Type> variables = new ArrayList<Type>();      //ArrayList to store all the variables created by user
        File Language = new File("/Users/dhruvsusheelkar/Documents/Code/Java/Compiler/src/com/company/Compiler.txt");
        Scanner reader = new Scanner(Language);     //creating scanner to input language through java compiler
        Scanner IN = new Scanner(System.in);//creating scanner to input user input
        int code_ender = 0;     //will check to end code if there is an error
        while(reader.hasNextLine() && code_ender == 0) {        //loops until there are no lines left
            String keyboard_input = reader.next();//stores first part of line to decide what to do
            if(keyboard_input.equals("integer")) {         //when line is declaring integer value
                String variable_name = reader.next();
                String equal_sign = reader.next();
                if(!(equal_sign.equals("="))) {          //checks if coder used correct sign
                    System.out.println();
                    System.out.println("Does not compile");
                    code_ender = 1;
                    break;
                }
                int variable_val = reader.nextInt();
                Type a = new Type(variable_name, variable_val);
                variables.add(a);               //adds variable created to the list
            }
            else if(keyboard_input.equals("str")) {         //when line is declaring String value
                String variable_name = reader.next();
                String equal_sign = reader.next();
                if(!(equal_sign.equals("="))) {          //checks if coder used correct sign
                    System.out.println();
                    System.out.println("Does not compile");
                    code_ender = 1;
                    break;
                }
                String variable_val = reader.nextLine();
                Type a = new Type(variable_name, variable_val);
                variables.add(a);           //adds variable created to the list
            }
            else if(keyboard_input.equals("MathComp:")) { //checks for addition, subtraction, multiplication, division, and remainder
                String variable_name = reader.next();
                String equal_sign = reader.next();
                if(!(equal_sign.equals("="))) {     //checks if coder used correct sign
                    System.out.println();
                    System.out.println("Does not compile");
                    code_ender = 1;
                    break;
                }
                int val1 = reader.nextInt();
                String operator = reader.next();
                int val2 = reader.nextInt();
                for(int i = 0; i < variables.size(); ++i) {     //loop to check for variable name
                    if(variables.get(i).getName().equals(variable_name)) {      //checks if variable exists
                        if(variables.get(i).getPhrase() != null) {      //checks to make sure it is not a string variable
                            System.out.println();
                            System.out.println("Does not compile");
                            code_ender = 1;
                            break;
                        }
                        else if(operator.equals("+")) {     //operation for addition
                            variables.get(i).setNumber(val1 + val2);
                            break;
                        }
                        else if(operator.equals("-")) {      //operation for subtraction
                            variables.get(i).setNumber(val1 - val2);
                            break;
                        }
                        else if(operator.equals("*")) {      //operation for multiplication
                            variables.get(i).setNumber(val1 * val2);
                            break;
                        }
                        else if(operator.equals("/")) {      //operation for division
                            variables.get(i).setNumber(val1 / val2);
                            break;
                        }
                        else if(operator.equals("%")) {      //operation to find remainder
                            variables.get(i).setNumber(val1 % val2);
                            break;
                        }
                        else {      //if operator is none of the above, code will exit compilation
                            System.out.println();
                            System.out.println("Does not compile");
                            code_ender = 1;
                            break;
                        }
                    }
                    else if(i == variables.size()-1) {      //if variable name does not match, will exit
                        System.out.println();
                        System.out.println("Does not compile");
                        code_ender = 1;
                        break;
                    }
                }
            }
            else if(keyboard_input.equals("##")) {      //checks for comments
                reader.nextLine();
            }
            else if(keyboard_input.equals("input")) {         //checks for user inputs
                String correct_sign = reader.next();
                if(!(correct_sign.equals(">>"))) {      //checks for correct signs
                    System.out.println();
                    System.out.println("Does not compile");
                    code_ender = 1;
                    break;
                }
                String var_type = reader.next();
                String var_name = reader.next();
                if(var_type.equals("integer")) {        //checks for integer
                    int var_val = IN.nextInt();
                    Type a = new Type(var_name, var_val);
                    variables.add(a);               //adds variable created to the list
                }
                else if(var_type.equals("str")) {       //checks for string
                    String var_val = IN.nextLine();
                    Type a = new Type(var_name, var_val);
                    variables.add(a);               //adds variable created to the list
                }
                else {      //if none of types does not compile
                    System.out.println();
                    System.out.println("Does not compile");
                    code_ender = 1;
                    break;
                }
            }
            else if(keyboard_input.equals("print:")) {      //checks for print statements
                String var_name = reader.next();
                for(int i = 0; i < variables.size(); ++i) {
                    if (variables.get(i).getName().equals(var_name)) {
                        if (variables.get(i).getPhrase() != null) {
                            System.out.println(variables.get(i).getPhrase());
                            break;
                        }
                        else {
                            System.out.println(variables.get(i).getNumber());
                            break;
                        }
                    }
                    else if(i == variables.size()-1) {
                        System.out.println();
                        System.out.println("Does not compile");
                        code_ender = 1;
                        break;
                    }
                }
            }
            else if(keyboard_input.equals("if")) {
                String var_name = reader.next();
                String operator = reader.next();
                for(int i = 0; i < variables.size(); ++i) {     //loop to check for variable name
                    if (variables.get(i).getName().equals(var_name)) {      //checks if variable exists
                        if (variables.get(i).getPhrase() != null) {      //checks to make sure it is not a string variable
                            String comp_string = reader.nextLine();
                            if(operator.equals("==")) {             //checks if operator is an equal comparison
                                if(!(variables.get(i).getPhrase().equals(comp_string))) {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals("!=")) {        //checks if operator is does not equal comparison
                                if((variables.get(i).getPhrase().equals(comp_string))) {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else {      //code will not compile if errors above not listed
                                System.out.println();
                                System.out.println("Does not compile");
                                code_ender = 1;
                                break;
                            }


                        }
                        else {      //code for comparing integers
                            int comp_int = reader.nextInt();
                            reader.nextLine();
                            if(operator.equals("==")) {         //checks if operator is an equal comparison
                                if(!(variables.get(i).getNumber() == comp_int)) {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals("!=")) {         //checks if operator is does not equal comparison
                                if((variables.get(i).getNumber() == comp_int)) {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals(">")) {         //checks if operator is greater than
                                if(!(variables.get(i).getNumber() > comp_int)) {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals(">=")) {         //checks if operator is greater than or equal to
                                if(!(variables.get(i).getNumber() >= comp_int)) {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals("<=")) {         //checks if operator is less than or equal to
                                if(!(variables.get(i).getNumber() <= comp_int)) {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals("<")) {         //checks if operator is less than
                                if(!(variables.get(i).getNumber() < comp_int)) {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else {      //code will not compile if errors above not listed
                                System.out.println();
                                System.out.println("Does not compile");
                                code_ender = 1;
                                break;
                            }
                        }
                    }
                    else if(i == variables.size()-1) {
                        System.out.println();
                        System.out.println("Does not compile");
                        code_ender = 1;
                        break;
                    }
                }
            }
            else if(keyboard_input.equals("}")) {       //check end of condition or loop
                reader.nextLine();
            }
            else if(keyboard_input.equals("++")) {
                String var_name = reader.next();
                for(int i = 0; i < variables.size(); ++i) {     //loop to check for variable name
                    if (variables.get(i).getName().equals(var_name)) {      //checks if variable exists
                        if (variables.get(i).getPhrase() != null) {      //checks to make sure it is not a string variable
                            System.out.println();
                            System.out.println("Does not compile");
                            code_ender = 1;
                            break;
                        } else {
                            variables.get(i).setNumber(variables.get(i).getNumber() + 1);
                            break;
                        }
                    } else if (i == variables.size() - 1) {
                        System.out.println();
                        System.out.println("Does not compile");
                        code_ender = 1;
                        break;
                    }
                }
            }
            else if(keyboard_input.equals("--")) {
                String var_name = reader.next();
                for(int i = 0; i < variables.size(); ++i) {     //loop to check for variable name
                    if (variables.get(i).getName().equals(var_name)) {      //checks if variable exists
                        if (variables.get(i).getPhrase() != null) {      //checks to make sure it is not a string variable
                            System.out.println();
                            System.out.println("Does not compile");
                            code_ender = 1;
                            break;
                        } else {
                            variables.get(i).setNumber(variables.get(i).getNumber() - 1);
                            break;
                        }
                    } else if (i == variables.size() - 1) {
                        System.out.println();
                        System.out.println("Does not compile");
                        code_ender = 1;
                        break;
                    }
                }
            }
            else if(keyboard_input.equals("while")) {
                String crementer = reader.next(); //checks if ++ or --
                String var_name = reader.next();
                String operator = reader.next();
                for(int i = 0; i < variables.size(); ++i) {//loop to check for variable name
                    if (variables.get(i).getName().equals(var_name)) {      //checks if variable exists
                        if (variables.get(i).getPhrase() != null) {      //checks to make sure it is not a string variable
                            System.out.println();
                            System.out.println("Does not compile");
                            code_ender = 1;
                            break;
                        }
                        else {      //code for comparing integers
                            int comp_int = reader.nextInt();
                            reader.nextLine();
                            if(operator.equals("==")) {         //checks if operator is an equal comparison
                                if((variables.get(i).getNumber() == comp_int)) {
                                    String print_checker = reader.next();
                                    String print_var_name = reader.next();
                                    int location = 0;
                                    for(int j = 0; j < variables.size(); ++j) {
                                        if (variables.get(j).getName().equals(print_var_name)) {
                                            location = j;
                                            break;
                                        }
                                        else if (j == variables.size() - 1) {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    if(print_checker.equals("print:")) {
                                        if(crementer.equals("++")) {        //checks if user wants to increment
                                           while(variables.get(i).getNumber() == comp_int) {       //creates user loop
                                               if (variables.get(location).getPhrase() != null) {
                                                   System.out.println(variables.get(location).getPhrase());
                                               }
                                               else {
                                                   System.out.println(variables.get(location).getNumber());
                                               }
                                               variables.get(i).setNumber(variables.get(i).getNumber()+1);
                                           }
                                           break;
                                        }
                                        else if(crementer.equals("--")) {       //checks if user wants to increment
                                            while(variables.get(i).getNumber() == comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()-1);
                                            }
                                            break;
                                        }
                                        else {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.println();
                                        System.out.println("Does not compile");
                                        code_ender = 1;
                                        break;
                                    }
                                }
                                else {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals("!=")) {         //checks if operator is does not equal comparison
                                if((variables.get(i).getNumber() != comp_int)) {
                                    String print_checker = reader.next();
                                    String print_var_name = reader.next();
                                    int location = 0;
                                    for(int j = 0; j < variables.size(); ++j) {
                                        if (variables.get(j).getName().equals(print_var_name)) {
                                            location = j;
                                            break;
                                        }
                                        else if (j == variables.size() - 1) {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    if(print_checker.equals("print:")) {
                                        if(crementer.equals("++")) {
                                            while(variables.get(i).getNumber() != comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()+1);
                                            }
                                            break;
                                        }
                                        else if(crementer.equals("--")) {
                                            while(variables.get(i).getNumber() != comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()-1);
                                            }
                                            break;
                                        }
                                        else {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.println();
                                        System.out.println("Does not compile");
                                        code_ender = 1;
                                        break;
                                    }
                                }
                                else {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals(">=")) {//checks if operator is greater than or equal to
                                if((variables.get(i).getNumber() >= comp_int)) {
                                    String print_checker = reader.next();
                                    String print_var_name = reader.next();
                                    int location = 0;
                                    for(int j = 0; j < variables.size(); ++j) {
                                        if (variables.get(j).getName().equals(print_var_name)) {
                                            location = j;
                                            break;
                                        }
                                        else if (j == variables.size() - 1) {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    if(print_checker.equals("print:")) {
                                        if(crementer.equals("++")) {
                                            while(variables.get(i).getNumber() >= comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()+1);
                                            }
                                            break;
                                        }
                                        else if(crementer.equals("--")) {
                                            while(variables.get(i).getNumber() >= comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()-1);
                                            }
                                            break;
                                        }
                                        else {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.println();
                                        System.out.println("Does not compile");
                                        code_ender = 1;
                                        break;
                                    }
                                }
                                else {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals("<=")) {         //checks if operator is less than or equal to
                                if((variables.get(i).getNumber() <= comp_int)) {
                                    String print_checker = reader.next();
                                    String print_var_name = reader.next();
                                    int location = 0;
                                    for(int j = 0; j < variables.size(); ++j) {
                                        if (variables.get(j).getName().equals(print_var_name)) {
                                            location = j;
                                            break;
                                        }
                                        else if (j == variables.size() - 1) {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    if(print_checker.equals("print:")) {
                                        if(crementer.equals("++")) {
                                            while(variables.get(i).getNumber() <= comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()+1);
                                            }
                                            break;
                                        }
                                        else if(crementer.equals("--")) {
                                            while(variables.get(i).getNumber() <= comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()-1);
                                            }
                                            break;
                                        }
                                        else {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.println();
                                        System.out.println("Does not compile");
                                        code_ender = 1;
                                        break;
                                    }
                                }
                                else {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals("<")) {         //checks if operator is less than
                                if((variables.get(i).getNumber() < comp_int)) {
                                    String print_checker = reader.next();
                                    String print_var_name = reader.next();
                                    int location = 0;
                                    for(int j = 0; j < variables.size(); ++j) {
                                        if (variables.get(j).getName().equals(print_var_name)) {
                                            location = j;
                                            break;
                                        }
                                        else if (j == variables.size() - 1) {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    if(print_checker.equals("print:")) {
                                        if(crementer.equals("++")) {
                                            while(variables.get(i).getNumber() < comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()+1);
                                            }
                                            break;
                                        }
                                        else if(crementer.equals("--")) {
                                            while(variables.get(i).getNumber() < comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()-1);
                                            }
                                            break;
                                        }
                                        else {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.println();
                                        System.out.println("Does not compile");
                                        code_ender = 1;
                                        break;
                                    }
                                }
                                else {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else if(operator.equals(">")) {
                                if((variables.get(i).getNumber() > comp_int)) {
                                    String print_checker = reader.next();
                                    String print_var_name = reader.next();
                                    int location = 0;
                                    for(int j = 0; j < variables.size(); ++j) {
                                        if (variables.get(j).getName().equals(print_var_name)) {
                                            location = j;
                                            break;
                                        }
                                        else if (j == variables.size() - 1) {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    if(print_checker.equals("print:")) {
                                        if(crementer.equals("++")) {
                                            while(variables.get(i).getNumber() > comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()+1);
                                            }
                                            break;
                                        }
                                        else if(crementer.equals("--")) {
                                            while(variables.get(i).getNumber() > comp_int) {
                                                if (variables.get(location).getPhrase() != null) {
                                                    System.out.println(variables.get(location).getPhrase());
                                                }
                                                else {
                                                    System.out.println(variables.get(location).getNumber());
                                                }
                                                variables.get(i).setNumber(variables.get(i).getNumber()-1);
                                            }
                                            break;
                                        }
                                        else {
                                            System.out.println();
                                            System.out.println("Does not compile");
                                            code_ender = 1;
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.println();
                                        System.out.println("Does not compile");
                                        code_ender = 1;
                                        break;
                                    }
                                }
                                else {
                                    String skipper = reader.nextLine();
                                    while (!(skipper.equals("}"))) {
                                        skipper = reader.nextLine();
                                    }
                                }
                                break;
                            }
                            else {      //code will not compile if errors above not listed
                                System.out.println();
                                System.out.println("Does not compile");
                                code_ender = 1;
                                break;
                            }
                        }
                    }
                    else if(i == variables.size()-1) {
                        System.out.println();
                        System.out.println("Does not compile");
                        code_ender = 1;
                        break;
                    }
                }
            }
            else {
                System.out.println();
                System.out.println("Does not compile");
                code_ender = 1;
                break;
            }
        }
    }
}
