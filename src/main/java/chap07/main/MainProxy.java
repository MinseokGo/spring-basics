package chap07.main;

import chap07.ExeTimeCalculator;
import chap07.ImpeCalculator;
import chap07.RecCalculator;

public class MainProxy {
    public static void main(String[] args) {
        //핵심 기능(팩토리얼)을 수행하는 객체를 의존 주입하고
        //부가 기능(시간 측정)을 수행하도록 프록시 객체 생성
        //공통 구현 기능과 핵심 구현 기능을 분리하는 것이 AOP의 핵심
        ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
        System.out.println(ttCal1.factorial(20));

        ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new RecCalculator());
        System.out.println(ttCal2.factorial(20));
    }
}