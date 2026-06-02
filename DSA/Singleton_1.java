package DSA;
public class Singleton_1 {
    
    private Singleton_1(){}

    private static class helper{
        private static final Singleton_1 INSTANCE = new Singleton_1();

    }

    public static Singleton_1 getInstance(){
        return helper.INSTANCE;
    }
}
