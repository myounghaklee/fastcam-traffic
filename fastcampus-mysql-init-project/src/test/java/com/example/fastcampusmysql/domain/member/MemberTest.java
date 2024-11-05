package com.example.fastcampusmysql.domain.member;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class MemberTest {

    @DisplayName("회원은 닉에임을 변경할 수 있다.")
    @Test
    public void testChangeName(){
        LongStream.range(0,10)
                .mapToObj(i -> MemberFixtureFactory.create(i))
                .forEach(member -> {
                    System.out.println(member.getNickname());
                });
    }

}
