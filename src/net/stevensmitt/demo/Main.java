package net.stevensmitt.demo;

import java.io.*;
//import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("ВНИМАНИЕ! Калькулятор поддерживает оперции *, /, +, -. Работает с числами от 1 до 10\n" +
                "Введите операцию в виде а + b: ");
        //Scanner input = new Scanner(System.in);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String strLine = null;
        try {
            strLine = input.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
            e.printStackTrace();
            System.exit(1);
        }
        try {
            if (strLine.isEmpty()) throw new InvalidInputException("Введенная строка пуста");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
        strLine = strLine.toUpperCase();
        try {
            Integer.parseInt(Character.toString(strLine.charAt(0)));
            integerWorker(strLine);
        } catch (NumberFormatException ex) {
            rintWorker(strLine);
        }

        System.exit(0);
    }
    static void integerWorker(String line){
        int p = line.indexOf(" ");
        int a = 0;
        try{
            a = Integer.parseInt(line.substring(0,p));
            if(a>10)
                throw new NumberFormatException("Неверный ввод, убедитесь что числа входят в диапазон от 0 до 10 и разделены с операндом пробелом");
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        char op = line.charAt(p+1);
        try{
            if(line.charAt(p+2)!=' ')
                throw new InvalidInputException("Неверный ввод, убедитесь что числа входят в диапазон от 0 до 10 и разделены с операндом пробелом");
        }catch (InvalidInputException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        p = line.indexOf(" ",p+1);
        int b=0;
        try{
            b = Integer.parseInt(line.substring(p+1));
            if (b>10) throw new NumberFormatException("Неверный ввод, убедитесь что числа входят в диапазон от 0 до 10 и разделены с операндом пробелом");
        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        a = operate (a,b,op);
        System.out.print (line + " = " + a);
    }
    static void rintWorker(String line){
        int p = line.indexOf(" ");
        try {
            if (! RInt.isRInt(line.substring(0,p)))
                throw new InvalidInputException("Неверный ввод, убедитесь что числа входят в диапазон от I до X и разделены с операндом пробелом");
        }catch (InvalidInputException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        RInt a = new RInt(line.substring(0,p));
        char op = line.charAt(p+1);
        try{
            if(line.charAt(p+2)!=' ')
                throw new InvalidInputException("Неверный ввод, убедитесь что числа входят в диапазон от 0 до 10 и разделены с операндом пробелом");
        }catch (InvalidInputException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        p = line.indexOf(" ",p+1);
        try {
            if (!RInt.isRInt(line.substring(p+1)))
                throw new InvalidInputException("Неверный ввод, убедитесь что числа входят в диапазон от I до X и разделены с операндом пробелом");
        }catch (InvalidInputException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        RInt b = new RInt(line.substring(p+1));
        a = new RInt(operate(a.getArabic(), b.getArabic(), op));
        System.out.print (line+" = " + a.toString());
    }
    static int operate (int a, int b, char operation){
        int res;
        switch (operation) {
            case '+' -> {
                res = a + b;
            }
            case '-' -> {
                res = a - b;
            }
            case '*' -> {
                res = a * b;
            }
            case '/' -> {
                res = a / b;
            }
            default -> {
                res = 0;
                try {
                    throw new InvalidInputException("Операция не поддерживается");
                } catch (InvalidInputException ex) {
                    System.out.println(ex.getMessage());
                    System.exit(1);
                }
            }
        }
        return res;
    }
}
