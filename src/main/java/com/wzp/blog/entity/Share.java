package com.wzp.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/18 17:50
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Share {
    private String  cn_share_id;
    private String  cn_share_title;
    private String  cn_share_body;
    private String  cn_note_id;
}
