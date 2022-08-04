package com.king.mapper;

import com.king.pojo.Role;
import org.apache.ibatis.annotations.CacheNamespace;


/**
 * @author hspcadmin
 */

public interface RoleMapper {

    public Role getRole(Long id);

    public Role findRole(String roleName);

    public int deleteRole(Long id);

    public int insertRole(Role role);
}
