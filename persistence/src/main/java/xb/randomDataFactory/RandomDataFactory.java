package xb.randomDataFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface RandomDataFactory<T> {
   T create() throws IllegalAccessException, InstantiationException, InvocationTargetException;

   List<T> create(int count) throws IllegalAccessException, InstantiationException, InvocationTargetException;
}
