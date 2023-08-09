package xb.entity;

import lombok.Data;

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
@Table(name = "xb_user")
public class User implements Serializable {

    @Id
    private String idUser;

    private String pid;

    private String name;


}