package com.batch.restapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service(value = "AppService")
@Primary
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AppService {

	private static final List<RequestDTO> listPools = new ArrayList<>();
	
	static 
	{
		listPools.addAll(Arrays.asList(
				new RequestDTO(0001, Arrays.asList(1,2,3,4,5)),
				new RequestDTO(0002, Arrays.asList(5,6,7,8,9)),
				new RequestDTO(0003, Arrays.asList(1,2,3,4,5)),
				new RequestDTO(0004, Arrays.asList(5,6,7,8,9))
				));
	}

	/**
	 * 
	 * @param requestDTO
	 * @return
	 * @throws Exception 
	 */
	public String addPoolValues(final RequestDTO requestDTO) 
	{
		final Integer poolIdInForm = requestDTO.getPoolId();
		
		if (Objects.isNull(poolIdInForm)) 
		{
			throw new NullPointerException("poolId can not be null");
		}
		
		final List<Integer> ids = listPools.stream().map(x -> x.getPoolId()).collect(Collectors.toList());
		if (!ids.contains(requestDTO.getPoolId())) 
		{	
			listPools.add(requestDTO);
			return Constant.INSERTED;
		}
		
		for (RequestDTO request : listPools) 
		{
			final Integer rootPoolId = request.getPoolId();
			if (poolIdInForm.equals(rootPoolId)) 
			{
				final List<Integer> rootPoolValues = request.getPoolValues();
				final List<Integer> poolValuesInForm = requestDTO.getPoolValues();
				
				final List<Integer> collect = poolValuesInForm.stream()
					.filter(x -> !rootPoolValues.contains(x))
					.collect(Collectors.toList());
				
				request.getPoolValues().addAll(collect);
				requestDTO.setPoolValues(request.getPoolValues());
				
				break;
			}
		}
		
		return Constant.APPENDED;
	}

	/**
	 * 
	 * @param poolId
	 * @return
	 */
	public ResponseTotalCountDTO totalCount(final Integer poolId) 
	{
		if (Objects.isNull(poolId)) 
		{
			throw new NullPointerException("poolId can not be null");
		}
		
		final List<Integer> ids = listPools.stream().map(x -> x.getPoolId()).collect(Collectors.toList());
		if (!ids.contains(poolId)) 
		{
			throw new NullPointerException("poolId not existing in the pools");
		}
		
		final ResponseTotalCountDTO responseTotalCount = new ResponseTotalCountDTO();
		long totalCount = listPools.stream()
			.filter(x -> poolId.equals(x.getPoolId()))
			.findFirst()
			.get().getPoolValues().stream()
			.mapToInt(x -> x)
			.count();
		
		responseTotalCount.setPoolId(poolId);
		responseTotalCount.setPercentile((int) totalCount);
		
		return responseTotalCount;
	}
}
