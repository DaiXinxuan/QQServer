package org.qq.server.dao;

import org.qq.server.entity.FriendshipEntity;

import java.util.List;

/**
 * Created by dxx on 2017/11/10.
 */
public interface FriendshipDao {
    FriendshipEntity selectShipByUserIds(int fUserId, int sUserId);

    FriendshipEntity selectById(int friendshipId);

    int addFriendship(FriendshipEntity friendshipEntity);

    int deleteShipById(int friendshipId);

    List<FriendshipEntity> getFriendsList(int userId);

}
