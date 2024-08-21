package ru.otus.pro.kovaleva.mappers;

import ru.otus.pro.kovaleva.dtos.MemberDto;
import ru.otus.pro.kovaleva.entities.Member;

public class MemberMapper {

    public MemberDto toDto(Member member) {
        return new MemberDto(member.getFirstName(),
                member.getHandleId(),
                member.getImagePath(),
                member.getLastName(),
                member.getMiddleName(),
                member.getPhoneNumber(),
                member.getService(),
                member.getThumbPath());
    }
}
