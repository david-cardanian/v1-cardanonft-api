package com.cardanonft.api.dao;

import com.cardanonft.api.vo.collection.CollectionAddressVO;
import com.cardanonft.api.vo.collection.CollectionHistoryVO;
import com.cardanonft.api.vo.collection.AuctionCollectionVO;
import com.cardanonft.api.vo.collection.CollectionListVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollectionDao {

	@Autowired
	SqlSession sqlSession;

	// 옥션 타입 컬렉션 리스트 조회
	public List<AuctionCollectionVO> getAuctionList(AuctionCollectionVO auctionCollectionVO) throws Exception {
		return sqlSession.selectList("collection.getAuctionList", auctionCollectionVO);
	}

	// nft 옥션 히스토리 조회
	public List<CollectionHistoryVO> getAuctionHistory(CollectionHistoryVO collectionHistoryVO) throws Exception {
		return sqlSession.selectList("collection.getAuctionHistory", collectionHistoryVO);
	}

	// nft 검색 조회
	public List<CollectionListVO> getListSearch(CollectionListVO collectionListVO) throws Exception {
		return sqlSession.selectList("collection.getListSearch", collectionListVO);
	}

	// 랜덤 주소 발행
	public List<CollectionListVO> getRandomAddress(CollectionAddressVO collectionAddressVO) throws Exception {
		return sqlSession.selectList("collection.getRandomAddress", collectionAddressVO);
	}
}
