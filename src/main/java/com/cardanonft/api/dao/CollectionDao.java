package com.cardanonft.api.dao;

import com.cardanonft.api.vo.collection.CollectionHistoryVO;
import com.cardanonft.api.vo.collection.CollectionVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollectionDao {

	@Autowired
	SqlSession sqlSession;

	// 옥션 타입 컬렉션 리스트 조회
	public List<CollectionVO> getAuctionList(CollectionVO collectionVO) throws Exception {
		return sqlSession.selectList("collection.getAuctionList", collectionVO);
	}

	// nft 옥션 히스토리 조회
	public List<CollectionHistoryVO> getAuctionHistory(CollectionHistoryVO collectionHistoryVO) throws Exception {
		return sqlSession.selectList("collection.getAuctionHistory", collectionHistoryVO);
	}
}
