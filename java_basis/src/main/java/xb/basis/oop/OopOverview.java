package xb.basis.oop;

/**
 * 面向对象就是java利用封装，继承，多态的特点，把事物建立成一种基于对象的层次结构，来模拟公共行为。
 * 封装即把事务的状态和行为都归纳到一个类中。
 * 继承是对父类的具体化和拓展。
 * 多态则是处理业务逻辑时，根据输入条件的不同，可以给出不同的结果，有重写和重载两种方式。
 */
public class OopOverview {
    private double i1;
    private String i2;
    private boolean i3;

    private void test(){
        System.out.println("123");
    }

    public static void main(String[] args) {
        OopOverview o = new OopOverview();
        o.test();
        Child c = new Child();
        c.test();
    }
}
