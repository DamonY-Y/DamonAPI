package com.damon.damonapi.back.common;


import lombok.Data;

import java.io.Serializable;

/**
 * ID请求
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}