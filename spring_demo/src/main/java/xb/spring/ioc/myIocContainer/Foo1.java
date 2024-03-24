package xb.spring.ioc.myIocContainer;

import lombok.Data;

@Data
public class Foo1 {
    private String id;
    private String name;

    public Foo1() {
    }

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
}
