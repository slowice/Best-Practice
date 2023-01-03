package xb.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * User主要信息
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    private String idUser;

    private String pid;

    private String name;

    private Date birthday;

    private int age;

    private String mobilePhone;

    private int type;
}