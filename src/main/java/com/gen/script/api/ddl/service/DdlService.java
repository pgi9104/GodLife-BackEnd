package com.gen.script.api.ddl.service;

import java.util.List;

import com.gen.script.api.ddl.domain.DdlDTO;
import com.gen.script.api.ddl.domain.DdlEntity;

public interface DdlService {
	public List<DdlEntity> list(DdlDTO dto) throws Exception;
	public DdlEntity get(DdlDTO dto) throws Exception;
	public void save(DdlDTO dto) throws Exception;
	public String script(DdlDTO dto) throws Exception;
	public String alterScript(DdlDTO dto) throws Exception;
	public List<DdlDTO> selectBox(DdlDTO dto) throws Exception;
}
