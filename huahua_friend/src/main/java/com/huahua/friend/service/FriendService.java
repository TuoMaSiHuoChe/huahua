package com.huahua.friend.service;

import com.huahua.friend.client.UserClient;
import com.huahua.friend.dao.FriendDao;
import com.huahua.friend.dao.NoFriendDao;
import com.huahua.friend.pojo.Friend;
import com.huahua.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: admin
 * @Date: 2019/4/24 15:36
 * @Description:
 */
@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;
    @Autowired
    private UserClient userClient;

    @Transactional(rollbackFor = Exception.class)
    public int addFriend(String userid,String friendid){
        if(friendDao.selectCount(userid,friendid)>0){
            return 0;
        }
        Friend friend = new Friend();
        friend.setFriendid(friendid);
        friend.setUserid(userid);
        friend.setIslike("0");
        friendDao.save(friend);
        if(noFriendDao.findOneByFriendidAndUserid(userid,friendid)!=null){
            NoFriend noFriend = new NoFriend();
            noFriend.setUserid(userid);
            noFriend.setFriendid(friendid);
            noFriendDao.delete(noFriend);
        }
        if(friendDao.selectCount(friendid,userid)>0){
            friendDao.updateLike(friendid,userid,"1");
            friendDao.updateLike(userid,friendid,"1");
        }
        //增加自己的关注数
        userClient.incFollowcount(userid,1);
        //增加对方的粉丝数
        userClient.incFanscount(friendid,1);
        return 1;
    }

    /**
     * 向不喜欢列表中添加记录
     * @param userid
     * @param friendid
     */
    public void addNoFriend(String userid,String friendid){
        NoFriend noFriend=new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);

    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteFriend(String userid, String friendid) {
        friendDao.deleteFriend(userid,friendid);
        friendDao.updateLike(friendid,userid,"0");
        //向不喜欢表中添加记录
        addNoFriend(userid,friendid);
        //减少自己的关注数
        userClient.incFollowcount(userid,-1);
        //减少对方的粉丝数
        userClient.incFanscount(friendid,-1);
    }
}
