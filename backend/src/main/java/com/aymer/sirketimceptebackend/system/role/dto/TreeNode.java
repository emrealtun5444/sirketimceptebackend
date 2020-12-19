package com.aymer.sirketimceptebackend.system.role.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TreeNode<T> {
    private T data;
    private String label;
    private List<TreeNode<T>> children = new ArrayList<>();
    private TreeNode<T> parent;
    private String expandedIcon = "pi pi-folder-open";
    private String collapsedIcon = "pi pi-folder";

    public TreeNode(T data, String label) {
        this.data = data;
        this.label = label;
    }
}
