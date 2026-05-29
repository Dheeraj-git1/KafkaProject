public class test2 {
    
    private test2(){}

    private static class helper{
        private static final test2 INSTANCE = new test2();

    }

    public static test2 getInstance(){
        return helper.INSTANCE;
    }
}
