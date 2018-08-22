package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;


//    @Override
//    public User findById(int id) {
//        User user = null;
//        try {
//            Connection connection = dataSource.getConnection();
//            String sql = "select * from User where id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                user = new User();
//                user.setUser_name(rs.getString("user_name"));
//                user.setId(rs.getInt("id"));
//                user.setStatus(rs.getByte("status"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    @Override
//    public User findByUsername(String userName) {
//        User user = null;
//        try {
//            Connection connection = dataSource.getConnection();
//            String sql = "select * from User where user_name = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, userName);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                user = new User();
//                user.setUser_name(rs.getString("user_name"));
//                user.setId(rs.getInt("id"));
//                user.setStatus(rs.getByte("status"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return user;
//    }

    @Override
    public String findByName(String userName){
        try{
            String sql = "SELECT user_name FROM users WHERE user_name = ?";

            String name = jdbcTemplate.queryForObject(sql, new Object[]{userName}, String.class);
            return name;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAllByName(String name) {
        name.toLowerCase();
        try {
            String sql = "SELECT * FROM users WHERE user_name LIKE ?";
            List<User> users = jdbcTemplate.query(sql, new String[]{"%" + name + "%"}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setUser_name(rs.getString("user_name"));
                    user.setId(rs.getInt("id"));
                    user.setStatus(rs.getByte("status"));
                    return user;
                }
            });
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean save(User user) {
        try {
            String sql = "INSERT INTO users(user_name) VALUES(?)";
            jdbcTemplate.update(sql, user.getUser_name());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
