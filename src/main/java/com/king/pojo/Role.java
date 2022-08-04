package com.king.pojo;

import java.io.Serializable;

/**
 * 角色的实体类
 * @author hspcadmin
 */
public class Role implements Serializable {
    private long id;
    private String roleName;
    private String note;

    public Role() {}

    public Role(long id, String roleName, String note){
        this.id = id;
        this.roleName = roleName;
        this.note = note;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}