package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.Users;
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
//    public boolean save(Users users) {
//        try {
//            Connection connection = dataSource.getConnection();
//            String sql = "INSERT INTO Users(user_name) VALUES(?)";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, users.getUser_name());
//            int line = ps.executeUpdate();
//            if (line > 0)
//                return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public Users findById(int id) {
//        Users user = null;
//        try {
//            Connection connection = dataSource.getConnection();
//            String sql = "select * from Users where id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                user = new Users();
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
//    public Users findByUsername(String userName) {
//        Users user = null;
//        try {
//            Connection connection = dataSource.getConnection();
//            String sql = "select * from Users where user_name = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, userName);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                user = new Users();
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
    public List<Users> findAllByName(String name) {
        name.toLowerCase();
        try {
            String sql = "select * from users where user_name like ?";
            List<Users> users = jdbcTemplate.query(sql, new String[]{"%"+name+"%"},new RowMapper<Users>() {
                @Override
                public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Users user = new Users();
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

}
