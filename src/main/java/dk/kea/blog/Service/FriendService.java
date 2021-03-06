package dk.kea.blog.Service;

import dk.kea.blog.Models.Friendship;
import dk.kea.blog.Repositories.Database;
import dk.kea.blog.Repositories.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {

    @Autowired
    FriendRepository friendRepository;

    public void acceptFriendRequest(int id) {
        friendRepository.acceptFriendRequest(id);
    }

    public void deleteFriendRequest(int id) {
        friendRepository.deleteFriendRequest(id);
    }

    public void addFriend(Friendship friendship) {
        if (friendship.getUser1() > 0) {
            friendRepository.addFriend(friendship);
        }
    }

    public List<Friendship> getFriendsRequest(int id) {
        List<Friendship> friendsRequest = new ArrayList<>();
        ResultSet rs = friendRepository.getFriendRequests(id);
        try {
            while (rs.next()) {
                Friendship friendship = new Friendship();
                friendship.setId(rs.getInt("friends.id"));
                friendship.setUsername1(rs.getString("firstname"));
                friendsRequest.add(friendship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendsRequest;
    }

    public List<Friendship> getFriends(int id) {
        List<Friendship> friends = new ArrayList<>();
        ResultSet rs = friendRepository.getFriends(id);
        try {
            while (rs.next()) {
                Friendship friendship = new Friendship();
                if (rs.getInt("u1.id") == id) {
                    friendship.setUsername1(rs.getString("u2.firstname"));
                    friendship.setId(rs.getInt("u2.id"));
                } else {
                    friendship.setUsername1(rs.getString("u1.firstname"));
                    friendship.setId(rs.getInt("u1.id"));
                }
                friends.add(friendship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public boolean isFriends(int id, Integer sid) {
        if (sid == null) {
            sid = 0;
        }
        ResultSet rs = friendRepository.checkForFriends(id, sid);
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
