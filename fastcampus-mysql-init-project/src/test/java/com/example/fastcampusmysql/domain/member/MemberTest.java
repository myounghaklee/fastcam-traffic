package com.example.fastcampusmysql.domain.member;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @DisplayName("회원은 닉에임을 변경할 수 있다.")
    @Test
    public void testChangeName(){
         var member = MemberFixtureFactory.create();
         var expected = "pnu";
         member.changeNickname(expected);
        Assertions.assertEquals(expected, member.getNickname());
    }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다..")
    @Test
    public void testNicknameMaxLength(){
        var member = MemberFixtureFactory.create();
        var overmaxLengthName = "pnununununnununununun";
        member.changeNickname(overmaxLengthName);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> member.changeNickname(overmaxLengthName)
        );
    }



}
