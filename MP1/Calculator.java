import java.util.Scanner;



public class Calculator{
  private String name;

  public Float addition(Float A, Float B){
    return A + B;
  }
  public Float subtraction(Float A, Float B){
    return A - B;
  }
  public Float multiplication(Float A, Float B){
    return A * B;
  }
  public void setName(String N){
    this.name = name;
  }
  public String getName(){
    Scanner scan = new Scanner(System.in);
    String name = scan.nextLine();
    return name;
  }




  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);

    Calculator myCalc = new Calculator();

    myCalc.setName("Group 19");

    System.out.println("Welcome to the Calculator designed by " + myCalc.getName());

    System.out.println("Enter A to Add, S to Subtract, M to Multiply, D to Divide, and Q to Quit.");
    String Operation = scan.nextLine();
    while(true){
    if(Operation.equals("Q")){
      break;
    }

    if (!(Operation.equals("A") || Operation.equals("S") || Operation.equals("M"))) {
      System.out.println("Invalid input. Please enter a valid operation.");
      System.out.println("Welcome to the Calculator designed by \"" + myCalc.getName() + "\".");
      System.out.println("Enter A to Add, S to Subtract, M to Multiply, and Q to quit.");
      continue;
  }

      System.out.println("Enter Argument 1:");
      String arg1Str = scan.nextLine();
      Float arg1 = null;
      try{
        arg1 = Float.parseFloat(arg1Str);
      } catch (NumberFormatException e){
        System.out.println("Invalid Argument");
        continue;
      }



      System.out.println("Enter Argument 2:");
      String arg2Str = scan.nextLine();
      Float arg2 = null;
      try{
        arg2 = Float.parseFloat(arg2Str);
      } catch (NumberFormatException e){
        System.out.println("Invalid Argument");
        continue;
      }

      if(Operation.equals("A")){
        Float sum = myCalc.addition(arg1, arg2);
        System.out.println("The sum of " + arg1 + " and " + arg2 +  " is " + sum);
      }
      else if(Operation.equals("S")){
        Float difference = myCalc.subtraction(arg1, arg2);
        System.out.println("The sum of " + arg1 + " and " + arg2 +  " is " + difference);
      }
      else if(Operation.equals("M")){
        Float product = myCalc.multiplication(arg1, arg2);
        System.out.println("The sum of " + arg1 + " and " + arg2 +  " is " + product);
      }



    }





  }
}
