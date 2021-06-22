package com.example.springsecurityjpa.user;

import org.springframework.data.jpa.repository.JpaRepository;

// ke thua class jpaRepository de su dung nhung ham truy van duoc dung san
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
}