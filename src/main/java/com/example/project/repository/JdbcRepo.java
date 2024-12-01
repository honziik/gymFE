package com.example.project.repository;

import com.example.project.repository.entities.Role;
import com.example.project.repository.entities.Users;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class JdbcRepo implements OneRepo{

    private final JdbcTemplate jdbcTemplate;
    private final RoleRepository roleRepository;

    public JdbcRepo(JdbcTemplate jdbcTemplate, RoleRepository roleRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.roleRepository = roleRepository;
    }


    private final RowMapper<Users> userRowMapper = (rs, rowNum) -> {
        Users user = new Users();
        user.setId(rs.getLong("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        //Role role = roleRepository.findByName("user");
        //user.setRole(role);
        return user;
    };

    public Users findByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, login);
    }

    public List<Users> findByRoleId(Long roleId) {
        String sql = "SELECT * FROM users WHERE role_id = ?";
        return jdbcTemplate.query(sql, userRowMapper, roleId);
    }

    public Users findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, email);
    }

    public void save(Users user) {
        String sql = "INSERT INTO users (login, password, email, role_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getLogin(), user.getPassword(), user.getEmail(),
                user.getRole() != null ? user.getRole().getId() : null);
    }

    public void update(Users user) {
        String sql = "UPDATE users SET login = ?, password = ?, email = ?, role_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getLogin(), user.getPassword(), user.getEmail(),
                user.getRole() != null ? user.getRole().getId() : null, user.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Users> findAll() {
        String sql = "SELECT * FROM users";
        RowMapper<Users> userRowMapper = (rs, rowNum) -> {
            Users user = new Users();
            user.setId(rs.getLong("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            Role role = roleRepository.findByName("user");
            user.setRole(role);
            return user;
        };
        return jdbcTemplate.query(sql, userRowMapper);
    }
}
