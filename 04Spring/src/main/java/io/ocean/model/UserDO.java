package io.ocean.model;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class UserDO implements Serializable {
    private Integer id;

    private String name;

    private String t1;

    private String t2;

    private String t3;

    private static final long serialVersionUID = 1L;
}