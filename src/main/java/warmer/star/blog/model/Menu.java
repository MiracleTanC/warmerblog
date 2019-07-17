package warmer.star.blog.model;

import lombok.Data;

import java.util.Date;

@Data
public class Menu {
    private int id;
    private String code;
    private String name;
    private int pid;
    private String url;
    private int type;
    private String icon;
    private int sort;
    private int level;
    private int status;
    private Date createon;
    private Date updateon;
}
