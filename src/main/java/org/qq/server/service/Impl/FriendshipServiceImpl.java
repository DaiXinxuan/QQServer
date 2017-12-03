package org.qq.server.service.Impl;

import org.qq.server.dao.FriendshipDao;
import org.qq.server.entity.FriendshipEntity;
import org.qq.server.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dxx on 2017/11/10.
 */
@Service
public class FriendshipServiceImpl implements FriendshipService {
    @Autowired
    FriendshipDao friendshipDao;

    public Boolean friendshipIsExisted(int fUserId, int sUserId) {
        Boolean result = friendshipDao.selectShipByUserIds(fUserId, sUserId) != null;
        return result;
    }

    public int makeFriendShip(FriendshipEntity friendshipEntity) {
        return friendshipDao.addFriendship(friendshipEntity);
    }

    public int cancelFriendship(FriendshipEntity friendshipEntity) {
        return friendshipDao.deleteShipById(friendshipEntity.getFriendshipId());
    }

    public FriendshipEntity getFriendship(int fUserId, int sUserId) {
        return friendshipDao.selectShipByUserIds(fUserId, sUserId);
    }

    public List<FriendshipEntity> getFriendsList(int userId) {
        return friendshipDao.getFriendsList(userId);
    }
}
