package xb.ioc.myIocContainer;

import lombok.Data;

@Data
public class Foo2 {
    private String id;
    private String name;
    private Foo1 foo1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Foo1 getFoo1() {
        return foo1;
    }

    public void setFoo1(Foo1 foo1) {
        this.foo1 = foo1;
    }
}
