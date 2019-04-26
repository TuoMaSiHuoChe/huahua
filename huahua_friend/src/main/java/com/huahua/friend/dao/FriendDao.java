package com.huahua.friend.dao;

import com.huahua.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: admin
 * @Date: 2019/4/24 15:35
 * @Description:
 */
public interface FriendDao extends JpaRepository<Friend, String> {

    /**
     * 修改好友关系
     * @param userid
     * @param friendid
     * @param islike
     */
    @Modifying
    @Query("update Friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
    void updateLike(String userid,String friendid,String islike);

    /**
     * 查询是否存在
     * @param userid
     * @param friendid
     * @return
     */
    @Query("select count(f) from Friend f where f.userid=?1 and f.friendid=?2")
    int selectCount(String userid, String friendid);

    /**
     * 删除喜欢
     * @param userid
     * @param friendid
     */
    @Modifying
    @Query("delete from Friend f where f.userid=?1 and f.friendid=?2")
    void deleteFriend(String userid, String friendid);
}
