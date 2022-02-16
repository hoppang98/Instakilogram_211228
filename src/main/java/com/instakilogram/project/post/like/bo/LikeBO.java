package com.instakilogram.project.post.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instakilogram.project.post.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	// 좋아요 추가
	// 리턴 : 좋아요 : true 좋아요 취소 : false
	public boolean like (int postId, int userId) {
		
		// 전달받은 데이터를 기반으로 좋아요 상태면 좋아요 취소 - delete
		// 좋아요가 아닌 상태면 좋아요 - insert
		// 지금상태가 무슨 상태인지 알아야 한다. - likeBO의 isLike를 활용
		
		if(this.isLike(postId, userId)) { // 좋아요 상태
			likeDAO.deleteLike(postId, userId);
			return false;
		} else { // 좋아요가 아닌 상태
			likeDAO.insertLike(postId, userId);
			return true;
		}
	}
	
	// 좋아요 삭제 - 위의 like로 구현
		//public int deleteLike (int postId, int userId) {
		//	return likeDAO.deleteLike(postId, userId);
		//}
	
	// postId로 좋아요 개수 조회
	public int getLikeCount(int postId) {
		return likeDAO.selectLikeCount(postId);
	}
	
	//postId와 userId로 좋아요를 눌렀는지 확인
	public boolean isLike(int postId, int userId) {
		
		int count = likeDAO.selectLikeCountByUserId(postId, userId);
		if(count == 0) {
			return false;
		} else {
			return true;
		}
		
		// return !(count == 0); --> 위와 같은 뜻
		// return !(likeDAO.selectLikeCountByUserId(postId, userId) == 0); --> 셋이 다 같은 뜻으로 문법만 다르다.
		
	}
	
	
}
