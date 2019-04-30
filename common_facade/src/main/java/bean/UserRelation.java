package bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRelation implements Serializable {
    private String userId;
    private String addressId;
}
