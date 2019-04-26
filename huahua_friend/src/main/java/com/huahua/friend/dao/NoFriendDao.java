package com.huahua.friend.dao;

import com.huahua.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: admin
 * @Date: 2019/4/24 16:04
 * @Description:
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    NoFriend findOneByFriendidAndUserid(String friendid,String Userid);
}
