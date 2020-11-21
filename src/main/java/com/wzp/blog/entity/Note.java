package com.wzp.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/13 21:23
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private String cn_note_id;
    private String cn_notebook_id;
    private String cn_user_id;
    private String cn_note_status_id;
    private String cn_note_type_id;
    private String cn_note_title;
    private String cn_note_body;
    private Long   cn_note_create_time;
    private Long   cn_note_last_modify_time;

}
