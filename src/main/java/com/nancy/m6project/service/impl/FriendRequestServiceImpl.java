package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.friendRequest.FriendRequest;
import com.nancy.m6project.repositories.friendRequest.FriendRequestRepositories;
import com.nancy.m6project.service.friendRequest.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    @Autowired
    private FriendRequestRepositories friendRequestRepositories;

    @Override
    public boolean isFriend(Long accountSendId1, Long accountReceiveId2, Integer status) {
        FriendRequest friendRequest = friendRequestRepositories.findByAccountSendIdAndAccountReceiveIdAndStatus(accountSendId1, accountReceiveId2, status);
        FriendRequest friendRequestReverse = friendRequestRepositories.findByAccountSendIdAndAccountReceiveIdAndStatus(accountReceiveId2, accountSendId1, status);
        if (friendRequest != null || friendRequestReverse != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FriendRequest save(FriendRequest model) {
        return friendRequestRepositories.save(model);
    }

    @Override
    public Boolean existsByAccountSendIdOrAccountReceiveId(Long accountSent, Long accountReceive) {
        return friendRequestRepositories.existsByAccountSendIdOrAccountReceiveId(accountSent,accountReceive);
    }

    @Override
    public Boolean existsFriendRequestByAccountReceive_IdAndAccountSend_Id(Long sentId, Long receiveId) {
        return friendRequestRepositories.existsFriendRequestByAccountReceive_IdAndAccountSend_Id(sentId,receiveId);
    }
}