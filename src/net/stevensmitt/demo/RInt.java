package net.stevensmitt.demo;

public class RInt {
    enum ROnes{un,I,II,III,IV,V,VI,VII,VIII,IX}
    enum RDecs{un,X,XX,XXX,XL,L,LX,LXX,LXXX,XC}
    ROnes o;
    RDecs d;
    int arabic;
    public RInt(int arabicInt)  {
        char[] cAInt = Integer.toString(arabicInt).toCharArray();
        try {
            if (arabicInt > 100) throw new Exception("Неверное число для преобразования в римское представление. Допускаются числа от 1 до 100 включительно");
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            System.exit(1);
        }
        arabic = arabicInt;
        switch (cAInt.length) {
            case 1 -> {
                o = ROnes.values()[Character.getNumericValue(cAInt[0])];
                d = RDecs.un;
            }
            case 2 -> {
                d = RDecs.values()[Character.getNumericValue(cAInt[0])];
                o = ROnes.values()[Character.getNumericValue(cAInt[1])];
            }
            case 3 -> {
                o = ROnes.un;
                d = RDecs.un;
            }
        }
    }
    public RInt(String romanInt){
                if (romanInt.toUpperCase().equals("X")){
            arabic = 10;
            d = RDecs.X;
            o = ROnes.un;
        }
        else{
            try{
                o = ROnes.valueOf(romanInt.toUpperCase());
                arabic = o.ordinal();
            }catch(IllegalArgumentException ex){
                System.out.print("Входное значение римского числа не может быть больше \"X\"");
            }
        }
    }
    public int getArabic(){
        return arabic;
    }
    public String toString(){
        if(d==RDecs.un)
            if (o==ROnes.un) return "C";
            else return o.toString();
        else {
            if (o == ROnes.un) return d.toString();
            return d.toString() + o.toString();
        }
    }
    public static boolean isRInt(String value){
        try{
            if (value.equalsIgnoreCase("un")) throw new IllegalArgumentException();
            if (value.equalsIgnoreCase("x")) return true;
            ROnes.valueOf(value);
            return true;
        }catch (IllegalArgumentException ex){
            return false;
        }
    }
}
