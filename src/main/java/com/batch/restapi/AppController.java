package com.batch.restapi;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "AppController")
@RequestMapping(value = "/api/v1")
public class AppController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

	private final AppService appService;
	
	/**
	 * @param appService
	 */
	public AppController(AppService appService) {
		this.appService = appService;
	}

	/**
	 * 
	 * @param requestDTO
	 * @return
	 */
	@PostMapping(value = "/addPool", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> addPoolValues(final @RequestBody RequestDTO requestDTO) 
	{
		LOGGER.info("-------BEGIN ADD POOLVALUES-------");
		LOGGER.info("-------VARIABLE--RequestDTO: ", requestDTO.toString());
		
		final ResponseDTO responseDTO = new ResponseDTO();
		
		if (Objects.isNull(requestDTO)) 
		{
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);
		}
		
		try 
		{
			final String message = appService.addPoolValues(requestDTO);
			responseDTO.setData(requestDTO);
			responseDTO.setMessage(message);
			responseDTO.setStatus(true);
		} 
		catch (NullPointerException ex) 
		{
			responseDTO.setData(new ArrayList<>());
			responseDTO.setMessage(ex.getMessage());
			responseDTO.setStatus(false);
		}
		
		LOGGER.info("-------END ADD POOLVALUES-------");
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param poolId
	 * @return
	 */
	@PostMapping(value = "/totalCount", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> totalCountInPool(@RequestBody Map<String, Integer> poolId) {
		LOGGER.info("-------BEGIN TOTAL COUNT IN POOL-------");
		LOGGER.info("-------VARIABLE--PoolId: ", poolId);
		
		final ResponseDTO responseDTO = new ResponseDTO();
		
		if (Objects.isNull(poolId)) 
		{
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);
		}
		
		try 
		{
			final ResponseTotalCountDTO responseTotalCount = appService.totalCount(poolId.get("poolId"));
			responseDTO.setData(responseTotalCount);
			responseDTO.setMessage(Constant.SUCCESS);
			responseDTO.setStatus(true);
		} 
		catch (NullPointerException ex) 
		{
			responseDTO.setData(new ArrayList<>());
			responseDTO.setMessage(ex.getMessage());
			responseDTO.setStatus(false);
		}
		
		LOGGER.info("-------END TOTAL COUNT IN POOL-------");
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);	
	}
}
