package com.wuyunbin.merchant.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-10
 */
@Data
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    private String id;

    /**
     * 标签内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 最后更新时间
     */
    private Date updatedAt;

    /**
     * 删除时间
     */
    private Integer deletedAt;
}
