package org.melchor.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.melchor.admin.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
