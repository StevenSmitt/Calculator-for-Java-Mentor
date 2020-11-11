package net.stevensmitt.demo;

public class InvalidInputException extends Exception{
    public InvalidInputException(){
        super("Неверные входные данные");
    }
    public InvalidInputException(String message){
        super(message);
    }
}
