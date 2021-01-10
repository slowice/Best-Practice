package xb.generics;

/**
 * java泛型
 * 泛型编程分为三大类：泛型类，泛型接口，泛型方法
 */
public class GenericsOverview {
    public static void main(String[] args) {
        testGenericsClass();
    }

    /*1.泛型类
        在实例化泛型类时，必须指定泛型类的类型
     */
    static class SimpleGenericsClass <T>{
        private T t;

        public SimpleGenericsClass(T t){
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }
    public static void testGenericsClass(){
        SimpleGenericsClass<String> genericsClass = new SimpleGenericsClass<>("ABG");
        System.out.println(genericsClass.getT());
    }
    //2.泛型接口
    public interface SimpleGenericsInterface<T>{
        T work();
    }
    //3.泛型方法
    public <T> T simpleGenericsMethod(T tClass){
        return tClass;
    }
}
