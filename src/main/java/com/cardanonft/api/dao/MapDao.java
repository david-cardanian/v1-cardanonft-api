package com.cardanonft.api.dao;

import com.cardanonft.api.request.MapParcelSearchRequest;
import com.cardanonft.api.request.MapSearchRequest;
import com.cardanonft.api.response.MapParcelSearchResponse;
import com.cardanonft.api.response.MapSearchResponse;
import com.cardanonft.api.vo.collection.AuctionCollectionVO;
import com.cardanonft.api.vo.collection.CollectionAddressVO;
import com.cardanonft.api.vo.collection.CollectionHistoryVO;
import com.cardanonft.api.vo.collection.CollectionListVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MapDao {

	@Autowired
	SqlSession sqlSession;

	// map 리스트 조회
	public List<MapSearchResponse> get3DParcelList(MapSearchRequest mapSearchRequest) throws Exception {
		return sqlSession.selectList("map.get3DParcelList", mapSearchRequest);
	}
	// map parcel 조회
	public MapParcelSearchResponse getParcelInfo(MapParcelSearchRequest mapParcelSearchRequest) throws Exception {
		return sqlSession.selectOne("map.getParcelInfo", mapParcelSearchRequest);
	}
}
