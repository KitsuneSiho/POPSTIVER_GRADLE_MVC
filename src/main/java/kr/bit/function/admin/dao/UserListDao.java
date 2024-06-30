package kr.bit.function.admin.dao;

import kr.bit.function.member.entity.MemberEntity;

import java.util.List;

public interface UserListDao {
    List<MemberEntity> getUsers(int offset, int limit);
    int getTotalUsers();
    int getNewSignups();
    int getTotalUsersFromPreviousWeek();
}
