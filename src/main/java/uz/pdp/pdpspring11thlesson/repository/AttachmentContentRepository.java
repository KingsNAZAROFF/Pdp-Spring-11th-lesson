package uz.pdp.pdpspring11thlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.pdpspring11thlesson.paylaod.entity.AttachmentContent;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
    AttachmentContent findByAttachmentId(Integer attachment_id);
}
