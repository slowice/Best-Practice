package bean;

import lombok.Data;

import java.io.Serializable;

@Data
/**
 * User关联信息
 */
public class UserRelation implements Serializable {
    private String userId;
    private String addressId;
}
