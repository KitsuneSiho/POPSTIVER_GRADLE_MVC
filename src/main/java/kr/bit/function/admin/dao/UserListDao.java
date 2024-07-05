package kr.bit.function.admin.dao;

import kr.bit.function.member.entity.MemberEntity;

import java.util.List;

public interface UserListDao {
    List<MemberEntity> getUsers(int offset, int limit);
    int getTotalUsers();
    int getNewSignups();
    int getTotalUsersFromPreviousWeek();
    int getNewUsersLast30Days(); // 신규 가입자 수를 조회하는 메서드 추가
}