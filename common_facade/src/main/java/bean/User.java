package bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
/**
 * User表元数据
 */
public class User implements Serializable {
    private String idUser;

    private String pid;

    private String name;

    private Date birthday;

    private int age;

    private String mobilePhone;

    private int type;
}