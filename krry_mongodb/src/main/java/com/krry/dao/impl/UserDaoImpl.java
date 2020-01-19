package com.krry.dao.impl;

import com.krry.dao.IUserDao;
import com.krry.entity.AlarmDetail;
import com.krry.entity.User;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * @author
 */
@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addUser(User user) {
        //1.如果没有指定集合，则默认添加到和对象名称相同的集合中，没有则创建一个
        //2.也可以指定集合 mongoTemplate.save(User, "User_db");
        mongoTemplate.save(user);
    }

    public void removeUser(String id) {
    	
        User User = findById(id);
        mongoTemplate.remove(User);
    }

    public void saveOrUpdateUser(User user) {

        mongoTemplate.save(user,"user");
    }

    public User findById(String id) {

        return mongoTemplate.findById(id, User.class);
    }

    public List<User> findAll() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC,"createTime"));
        return mongoTemplate.find(query,User.class,"user");
    }

	public User findByUsername(String username) {
		//根据username查询
		Query sql = new Query(Criteria.where("username").is(username));
		
		return mongoTemplate.findOne(sql,User.class);
	}

	public WriteResult delete(String username){
        Query sql = new Query(Criteria.where("username").is(username));
        return mongoTemplate.remove(sql,User.class);
    }

    public boolean update(String username) {
	    Query query = new Query(Criteria.where("username").is(username));
        Update update = Update.update("username","哈哈哈");
	    User user = mongoTemplate.findAndModify(query,update,User.class,"user");
	    if(user != null){
	        return true;
        }
        return false;
    }

    public void save(User user) {
        mongoTemplate.save(user,"logger");
    }

    public List<AlarmDetail> select(Integer page, Integer size) {
        Criteria criteria = Criteria.where("operationState").is("交维态");
        Aggregation agg = Aggregation.newAggregation(
                match(criteria),
//                project("state","details","tierCode","operationState","siteTier","level","site")
//                .andExpression("site.strId"),
//                group("site").count().as("count").first("operationState").as("state")
//                        .first("tierCode").as("tierCode"),
                project("site").andExpression("substr(tReport,0,10)").as("date"),
                group("site","date").count().as("count"),
//                project("count").and("site.strId").as("siteId").and("site").as("site"),
                sort(Sort.Direction.ASC,"count"),
//                project("count").and("site").previousOperation(),
                project("count").and("date").as("tierCode"),
                skip((page-1)*size),
                limit(size)
        );
        Aggregation agg1 = Aggregation.newAggregation(
          match(criteria),
                project("state","details","tierCode","operationState","siteTier","level","site"),
                sort(Sort.Direction.ASC,"tierCode"),
                skip((page-1)*size),
                limit(size)
        );
            AggregationResults<AlarmDetail> result = mongoTemplate.aggregate(agg1,"meitainuo_alarm",AlarmDetail.class);
            List<AlarmDetail> list = result.getMappedResults();
        return list;
    }
}




