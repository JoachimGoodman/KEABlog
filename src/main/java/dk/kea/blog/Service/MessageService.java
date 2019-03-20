package dk.kea.blog.Service;

import dk.kea.blog.Models.Friendship;
import dk.kea.blog.Models.Message;
import dk.kea.blog.Repositories.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    Database db;

    public List<Message> getMessages(int sender, int receiver) {
        List<Message> messages = new ArrayList<>();
        ResultSet rs = db.receiveMessages(sender, receiver);
        try {
            while (rs.next()) {
                Message message = new Message();
                message.setMessage(rs.getString("message"));
                message.setSenderUser(rs.getInt("fk_senderUser"));
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public void insertMessage(Message message, int sender, int receiver) {
        db.sendMsg(message, sender, receiver);
    }

    public List<Friendship> getFriend(int id) {
        List<Friendship> friend = new ArrayList<>();
        ResultSet rs = db.findFriendById(id);
        try {
            while(rs.next()) {
                Friendship friendship = new Friendship();
                friendship.setUser1(rs.getInt("fk_userIdTwo"));
                friend.add(friendship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friend;
    }
}
