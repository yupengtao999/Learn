package com.krry.dao;

import com.krry.entity.AlarmDetail;
import com.krry.entity.User;
import com.mongodb.WriteResult;

import java.util.List;

/**
 * 
 * @author
 */
public interface IUserDao {
	
    /**
     * 添加
     * @param user
     */
    public void addUser(User user);


    /**
     * 删除
     * @param id
     */
    public void removeUser(String id);


    /**
     * 保存或修改
     * @param User
     */
    public void saveOrUpdateUser(User User);


    /**
     * 根据id查询单个
     * @param id
     * @return
     */
    public User findById(String id);
    
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    public User findByUsername(String username);


    /**
     * 查询所有
     * @return
     */
    public List<User> findAll();

    /**
     *删除
     */
    public WriteResult delete(String username);

    /**
     * 修改
     */
    public boolean update(String username);

    /**
     * 登录日志
     */
    public void save(User user);

    /**
     * 聚合查询
     */
    public List<AlarmDetail> select(Integer page, Integer size);
}

