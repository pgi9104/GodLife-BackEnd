package com.gen.script.api.ddl.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gen.script.api.ddl.domain.DdlEntity;
import com.gen.script.api.ddl.domain.pk.DdlPk;

@Repository("ddlRepository")
public interface DdlRepository extends JpaRepository<DdlEntity, DdlPk>{
	List<DdlEntity> findByProjectCodeAndTableNameAndTableCol(String projectCode, String tableName, String tableCol);
	List<DdlEntity> findAllByOrderBySortSeq();
}
