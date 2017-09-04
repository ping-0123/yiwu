package com.yinzhiwu.yiwu.web.taglib;

import java.util.Collection;

import org.springframework.util.CollectionUtils;

import com.yinzhiwu.yiwu.entity.sys.Resource;
import com.yinzhiwu.yiwu.entity.sys.Role;
import com.yinzhiwu.yiwu.entity.yzw.DepartmentYzw;
import com.yinzhiwu.yiwu.service.DepartmentYzwService;
import com.yinzhiwu.yiwu.service.ResourceService;
import com.yinzhiwu.yiwu.service.RoleService;
import com.yinzhiwu.yiwu.shiro.spring.SpringUtils;

/**
 * @author ping
 * @version 2017年9月4日上午11:58:06
 *
 */
public class Functions {

    public static boolean in(Iterable<?> iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }

    public static String organizationName(Integer deptId) {
        DepartmentYzw organization = getDepartmentService().get(deptId);
        if(organization == null) {
            return "";
        }
        return organization.getName();
    }

    public static String departmentNames(Collection<Integer> deptIds) {
        if(CollectionUtils.isEmpty(deptIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Integer id : deptIds) {
            DepartmentYzw dept = getDepartmentService().get(id);
            if(dept == null) {
                return "";
            }
            s.append(dept.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
    public static String roleName(Integer roleId) {
        Role role = getRoleService().get(roleId);
        if(role == null) {
            return "";
        }
        return role.getDescription();
    }

    public static String roleNames(Collection<Integer> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Integer roleId : roleIds) {
            Role role = getRoleService().get(roleId);
            if(role == null) {
                return "";
            }
            s.append(role.getDescription());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
    public static String resourceName(Integer resourceId) {
        Resource resource = getResourceService().get(resourceId);
        if(resource == null) {
            return "";
        }
        return resource.getName();
    }
    public static String resourceNames(Collection<Integer> resourceIds) {
        if(CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Integer resourceId : resourceIds) {
            Resource resource = getResourceService().get(resourceId);
            if(resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }

    private static DepartmentYzwService departmentService;
    private static RoleService roleService;
    private static ResourceService resourceService;

    public static DepartmentYzwService getDepartmentService() {
        if(departmentService == null) {
            departmentService = SpringUtils.getBean(DepartmentYzwService.class);
        }
        return departmentService;
    }

    public static RoleService getRoleService() {
        if(roleService == null) {
            roleService = SpringUtils.getBean(RoleService.class);
        }
        return roleService;
    }

    public static ResourceService getResourceService() {
        if(resourceService == null) {
            resourceService = SpringUtils.getBean(ResourceService.class);
        }
        return resourceService;
    }
}

