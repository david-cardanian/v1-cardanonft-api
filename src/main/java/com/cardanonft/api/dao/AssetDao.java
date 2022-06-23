package com.cardanonft.api.dao;

import com.cardanonft.api.request.MapListRequest;
import com.cardanonft.api.request.MapSearchRequest;
import com.cardanonft.api.request.VillageListRequest;
import com.cardanonft.api.response.VillageListResponse;
import com.cardanonft.api.vo.collection.AuctionCollectionVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssetDao {

	@Autowired
	SqlSession sqlSession;

	// village 리스트 조회
	public List<VillageListResponse> getVillageList(VillageListRequest villageListRequest) throws Exception {
		return sqlSession.selectList("asset.getVillageList", villageListRequest);
	}
	// map 리스트 조회
	public List<VillageListResponse> getMapList(MapListRequest mapListRequest) throws Exception {
		return sqlSession.selectList("asset.getMapList", mapListRequest);
	}
}
