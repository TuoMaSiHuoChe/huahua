package com.huahua.qa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: admin
 * @Date: 2019/4/12 16:09
 * @Description:
 */
@Entity
@Table(name = "tb_pl")
@Data
public class Pl implements Serializable {
    @Id
    private String problemid;
    @Id
    private String lableid;

}
