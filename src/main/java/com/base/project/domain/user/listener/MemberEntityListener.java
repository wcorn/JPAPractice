package com.base.project.domain.user.listener;


import com.base.project.domain.user.entity.Member;
import com.base.project.domain.user.entity.MemberHistory;
import com.base.project.domain.user.repository.MemberHistoryRepository;
import com.base.project.global.utils.BeanUtils;


import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class MemberEntityListener {
    @PostPersist
    @PostUpdate
    public void postPersistAndPostUpdate(Object o){
        MemberHistoryRepository memberHistoryRepository = BeanUtils.getBean(MemberHistoryRepository.class);
        Member member = (Member)o;
        MemberHistory memberHistory = MemberHistory.builder()
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .password(member.getPassword())
                .member(member)
                .build();
        memberHistoryRepository.save(memberHistory);
    }
}
