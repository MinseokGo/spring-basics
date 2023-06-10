package chap07;

public class ExeTimeCalculator implements Calculator {
    private Calculator delegate;

    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    //공통 기능 구현
    @Override
    public long factorial(long num) {
        long start = System.nanoTime();
        long result = delegate.factorial(num);
        long end = System.nanoTime();

        System.out.printf("%s.factorial(%d) 실행 시간 = %d\n",
                            delegate.getClass(), num, end-start);

        return  result;
    }
}
