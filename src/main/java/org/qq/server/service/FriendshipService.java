package org.qq.server.service;

import org.qq.server.entity.FriendshipEntity;

import java.util.List;

/**
 * Created by dxx on 2017/11/10.
 * 好友查找，添加功能的服务接口
 */
public interface FriendshipService {
    //检查两个用户的好友关系是否已经建立，避免重复创建
    Boolean friendshipIsExisted(int fUserId, int sUserId);

    int makeFriendShip(FriendshipEntity friendshipEntity);

    int cancelFriendship(FriendshipEntity friendshipEntity);

    FriendshipEntity getFriendship(int fUserId, int sUserId);

    List<FriendshipEntity> getFriendsList(int userId);
}
