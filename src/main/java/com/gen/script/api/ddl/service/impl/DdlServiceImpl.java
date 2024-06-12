package com.gen.script.api.ddl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gen.script.api.ddl.domain.DdlDTO;
import com.gen.script.api.ddl.domain.DdlEntity;
import com.gen.script.api.ddl.domain.pk.DdlPk;
import com.gen.script.api.ddl.service.DdlDAO;
import com.gen.script.api.ddl.service.DdlService;
import com.gen.script.api.ddl.service.repository.DdlRepository;

import jakarta.annotation.Resource;

@Transactional
@Service(value="ddlService")
public class DdlServiceImpl implements DdlService {

	@Resource(name="ddlRepository")
	private DdlRepository ddlRepository;
	
	@Resource(name="ddlDAO")
	private DdlDAO ddlDAO;
	
	@Override
	public DdlEntity get(DdlDTO dto) throws Exception {
		return ddlRepository.findById(new DdlPk(dto.getProjectCode(), dto.getTableName(), dto.getTableCol())).get();
	}
	
	@Override
	public List<DdlEntity> list(DdlDTO dto) throws Exception {
		List<DdlEntity> list = ddlDAO.selectDdlList(dto).stream().map((ddl)->{
			return ddl.toEntity();
		}).collect(Collectors.toList());
		return list;
	}
	
	@Override
	public void save(DdlDTO dto) throws Exception {
		ddlDAO.insertBatch(dto);
		ddlDAO.updateBatch(dto);
		ddlDAO.deleteBatch(dto);
	}
	
	private Map<String, Map<String, List<DdlDTO>>> ddlList(DdlDTO dto) throws Exception {
		List<DdlDTO> list = ddlDAO.selectDdlList(dto);
		Map<String, Map<String, List<DdlDTO>>> ddlList = new HashMap<String, Map<String, List<DdlDTO>>>(); 
		
		for(DdlDTO columnInfo: list) {
			String projectCode = columnInfo.getProjectCode();
			String tableName = columnInfo.getTableName();
			
			//project
			if(!ddlList.containsKey(projectCode)) {
				ddlList.put(projectCode, new HashMap<String, List<DdlDTO>>());
			}
			//table
			if(!ddlList.get(projectCode).containsKey(tableName)) {
				ddlList.get(projectCode).put(tableName, new ArrayList<DdlDTO>());
			}
			//column
			ddlList.get(projectCode).get(tableName).add(columnInfo);
		}
		
		return ddlList;
	}
	
	@Override
	public String script(DdlDTO ddl) throws Exception {
		StringBuffer result = new StringBuffer();
		Map<String, Map<String, List<DdlDTO>>> ddlMap = ddlList(ddl);
		String dbType = (ddl.getDbType() == null || "".equals(ddl.getDbType()))? "mysql":ddl.getDbType();

		if("mysql".equals(dbType)) {
			mysqlCreate(result, ddlMap);
		}
		
		return result.toString();
	}
	
	
	@Override
	public List<DdlDTO> selectBox(DdlDTO dto) throws Exception {
		return ddlDAO.selectBox(dto);
	}
	
	@Override
	public String alterScript(DdlDTO ddl) throws Exception {
		StringBuffer result = new StringBuffer();
		Map<String, Map<String, List<DdlDTO>>> ddlMap = ddlList(ddl);
		String dbType = (ddl.getDbType() == null || "".equals(ddl.getDbType()))? "mysql":ddl.getDbType();

		if("mysql".equals(dbType)) {
			mysqlAlter(ddl, result, ddlMap);
		}
		
		return result.toString();
	}
	
	private void mysqlAlter(DdlDTO ddl, StringBuffer result, Map<String, Map<String, List<DdlDTO>>> ddlMap) {
		String alterType = ("".equals(ddl.getAlterType()) || ddl.getAlterType() == null)? "ADD" : ddl.getAlterType();
		for(String project : ddlMap.keySet()) {
			Map<String, List<DdlDTO>> tables = ddlMap.get(project);
			for(String table : tables.keySet()) {
				
				List<DdlDTO> colInfo = tables.get(table);
				List<DdlDTO> pkList = new ArrayList<>();
				for(DdlDTO col : colInfo) {
					String colName = " `"+col.getTableCol()+"`";
					String colType = col.getColType();
					boolean isString = "STRING".equals(colType);
					if(isString) {
						colType = "VARCHAR";
					}
					
					if("INT".equals(colType)) {
						colType = "INT";
					}
					
					if("TIMESTAMP".equals(colType)) {
						colType = "TIMESTAMP";
					}
					
					colType = (col.getColTypeLmt() == null || "".equals(col.getColTypeLmt()))?" "+colType:" "+colType+"("+col.getColTypeLmt()+")";
					String nullable = col.getNullable() == 0? " NOT NULL":" NULL";
					String defaultValue = (col.getDefaultValue() == null || "".equals(col.getDefaultValue()))? "" : isString?"\tDEFAULT '"+col.getDefaultValue()+"'":"\tDEFAULT "+col.getDefaultValue();
					String comment = (col.getComment() == null || "".equals(col.getComment()))? "" : " COMMENT '"+col.getComment()+"'";
					if(col.getColPk() == 1) {
						pkList.add(col);
					}
					
					String alterTable = "ALTER TABLE "+table+" "+alterType+" COLUMN "+colName+colType+nullable+defaultValue+comment;
					result.append(alterTable+";\n");
				}
				
				if(pkList.size()>0) {
					result.append("ALTER TABLE "+table+" DROP PRIMARY KEY;\n");
					result.append("ALTER TABLE "+table+" ADD PRIMARY KEY(");
					String pkStr = "";
					for(DdlDTO pk : pkList) {
						if("".equals(pkStr)) {
							pkStr += pk.getTableCol();
						}else {
							pkStr += ", " + pk.getTableCol();
						}
					}
					result.append(pkStr + ");\n\n");
				}
			}
		}		
	}
	
	private void mysqlCreate(StringBuffer result, Map<String, Map<String, List<DdlDTO>>> ddlMap) {
		for(String project : ddlMap.keySet()) {
			Map<String, List<DdlDTO>> tables = ddlMap.get(project);
			for(String table : tables.keySet()) {
				result.append("CREATE TABLE "+table+"(\n");
				List<DdlDTO> colInfo = tables.get(table);
				List<DdlDTO> pkList = new ArrayList<>();
				for(DdlDTO col : colInfo) {
					String colName = "\t`"+col.getTableCol()+"`";
					String colType = col.getColType();
					boolean isString = "STRING".equals(colType);
					if(isString) {
						colType = "VARCHAR";
					}
					
					if("INT".equals(colType)) {
						colType = "INT";
					}
					
					if("TIMESTAMP".equals(colType)) {
						colType = "TIMESTAMP";
					}
					
					colType = (col.getColTypeLmt() == null || "".equals(col.getColTypeLmt()))?"\t"+colType:"\t"+colType+"("+col.getColTypeLmt()+")";
					String nullable = col.getNullable() == 0? "\tNOT NULL":"\tNULL";
					String defaultValue = (col.getDefaultValue() == null || "".equals(col.getDefaultValue()))? "" : isString?"\tDEFAULT '"+col.getDefaultValue()+"'":"\tDEFAULT "+col.getDefaultValue();
					String comment = (col.getComment() == null || "".equals(col.getComment()))? "" : "\tCOMMENT '"+col.getComment()+"'";
					if(col.getColPk() == 1) {
						pkList.add(col);
					}
					
					result.append(colName+colType+nullable+defaultValue+comment+",\n");
				}
				
				if(pkList.size()>0) {
					result.append("\tPRIMARY KEY (");
					String pkStr = "";
					for(DdlDTO pk : pkList) {
						if("".equals(pkStr)) {
							pkStr += pk.getTableCol();
						}else {
							pkStr += ", " + pk.getTableCol();
						}
					}
					result.append(pkStr + ")\n");
				}
				
				result.append(")ENGINE=INNODB DEFAULT CHARSET=UTF8;\n\n");
			}
		}		
	}
}