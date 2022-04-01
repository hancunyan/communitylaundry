package com.computer.community_laundry.support.utils;

import com.computer.community_laundry.support.entity.system.SysResource;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 菜单树递归查询
 */
public class ResourceTree {
    private List<SysResource> menuList = new ArrayList<SysResource>();
    public ResourceTree(List<SysResource> menuList) {
        this.menuList=menuList;
    }

          //建立树形结构
    public List<SysResource> builTree(){
        List<SysResource> treeMenus =new ArrayList<SysResource>();
        for(SysResource menuNode : getRootNode()) {
            menuNode=buildChilTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private SysResource buildChilTree(SysResource pNode){
        List<SysResource> chilMenus =new  ArrayList<SysResource>();
        for(SysResource menuNode : menuList) {
            if(menuNode.getPid().equals(pNode.getResourceid())) {
                chilMenus.add(buildChilTree(menuNode));
            }
        }
        pNode.setChildren(chilMenus);
        return pNode;
    }

    //获取根节点
    private List<SysResource> getRootNode() {
        List<SysResource> rootMenuLists =new  ArrayList<SysResource>();
        for(SysResource menuNode : menuList) {
            if(menuNode.getPid() == 0) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }
}
