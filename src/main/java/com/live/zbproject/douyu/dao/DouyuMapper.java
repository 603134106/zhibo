package com.live.zbproject.douyu.dao;

import com.live.zbproject.douyu.entity.DyUserRoom;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

@Mapper
public interface DouyuMapper {

    int deleteByRid(@Param("rid") Integer rid);

    int insert(@Param("dyUserRoom") DyUserRoom dyUserRoom);

}
