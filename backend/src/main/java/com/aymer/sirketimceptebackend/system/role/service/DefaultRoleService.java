package com.aymer.sirketimceptebackend.system.role.service;

import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.aymer.sirketimceptebackend.system.role.dto.RoleInput;
import com.aymer.sirketimceptebackend.system.role.dto.TreeNode;
import com.aymer.sirketimceptebackend.system.role.mapper.RoleMapper;
import com.aymer.sirketimceptebackend.system.role.model.Authorization;
import com.aymer.sirketimceptebackend.system.role.model.Role;
import com.aymer.sirketimceptebackend.system.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DefaultRoleService implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public DefaultRoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleInput> listRoles() {
        return roleMapper.toInputs(roleRepository.findAll());
    }

    @Override
    public RoleInput addRole(RoleInput input) {
        Role role = new Role();
        roleMapper.updateFromInput(input, role);
        return roleMapper.toInput(roleRepository.save(role));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RoleInput updateRole(Long id, RoleInput input) {
        Role role = roleRepository.findById(id).get();
        roleMapper.updateFromInput(input, role);
        return roleMapper.toInput(roleRepository.save(role));
    }

    @Override
    public void deleteRole(Long id) {
        //TODO mark as disabled if used?
        roleRepository.deleteById(id);
    }

    @Override
    public RoleInput getRole(Long id) {
        return roleMapper.toInput(roleRepository.findById(id)
            .orElseThrow(() -> new ServiceException("No role entity found with id: " + id)));
    }

    @Override
    public List<Authorization> getAuthorizations() {
        return Arrays.asList(Authorization.values());
    }

    public List<TreeNode<String>> getTreeOfAuthorizations() {
        List<Authorization> authorizationList = this.getAuthorizations();
        return createTree(authorizationList);
    }

    private List<TreeNode<String>> createTree(List<Authorization> input) {
        Map<String, TreeNode<String>> hm = new LinkedHashMap<>();
        TreeNode<String> child = null;
        TreeNode<String> mmdParent = null;

        for (Authorization item : input) {
            // ------ Process child ----
            if (!hm.containsKey(item.name())) {
                hm.put(item.name(), new TreeNode<String>(item.name(), item.getLabel()));
            }
            child = hm.get(item.name());

            // ------ Process Parent ----
            if (item.getParent() != null) {
                child.setParent(new TreeNode<>(item.getParent().name(), item.getParent().getLabel()));
                if (hm.containsKey(item.getParent().name())) {
                    mmdParent = hm.get(item.getParent().name());
                    mmdParent.getChildren().add(child);
                }
            }

        }

        List<TreeNode<String>> DX = new ArrayList<TreeNode<String>>();
        for (TreeNode<String> mmd : hm.values()) {
            if (mmd.getParent() == null)
                DX.add(mmd);
        }
        return DX;
    }

}
