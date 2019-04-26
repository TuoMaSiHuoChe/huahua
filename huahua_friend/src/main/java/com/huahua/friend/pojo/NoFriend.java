package com.huahua.friend.pojo;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: admin
 * @Date: 2019/4/24 16:03
 * @Description:
 */
@Entity
@Table(name="tb_nofriend")
@IdClass(NoFriend.class)
@Data
public class NoFriend implements Serializable {
    @Id
    private String userid;
    @Id
    private String friendid;


}