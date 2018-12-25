package org.fage.querydsltest.repository;

import org.fage.querydsltest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author Caizhf
 * @version 1.0
 * @date 2018/10/29 17:57
 * @description
 **/
public interface UserRepository extends JpaRepository<User, Integer>, QueryDslPredicateExecutor<User> {
}
