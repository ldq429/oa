package com.imooc.oa.mapper;

import com.imooc.oa.entity.Node;
import com.imooc.oa.utils.MybatisUtils;

import java.util.List;

public class RbacMapper {
    public List<Node> selectNodeByUserId(Long userId) {
        List<Node> list = (List) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectList("rbacMapper.selectNodeByUserId", userId));
        return list;
    }
}
