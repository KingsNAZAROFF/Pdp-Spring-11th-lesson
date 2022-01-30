package uz.pdp.pdpspring11thlesson.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Attachment;
import uz.pdp.pdpspring11thlesson.paylaod.entity.AttachmentContent;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.AttachmentContentRepository;
import uz.pdp.pdpspring11thlesson.repository.AttachmentRepository;

import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {


    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("Fayl saqlandi",true,savedAttachment.getId());


    }

    public Page<Attachment> getALl(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return attachmentRepository.findAll(pageable);
    }

    @SneakyThrows
    public Result editAttachment(Integer id,MultipartHttpServletRequest request){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty()){
            return new Result("Bunday attachment topilmadi",false);
        }

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = optionalAttachment.get();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        AttachmentContent byAttachmentId = attachmentContentRepository.findByAttachmentId(id);
        attachmentContentRepository.deleteById(byAttachmentId.getId());
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("Fayl saqlandi",true,savedAttachment.getId());

    }

    public Result deleteAttachment(Integer id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            attachmentRepository.deleteById(id);
            AttachmentContent byAttachmentId = attachmentContentRepository.findByAttachmentId(id);
            attachmentContentRepository.deleteById(byAttachmentId.getId());
            return new Result("Attachment o'chirildi",true);
        }
        return  new Result("Bunday attachment topilmadi",false);
    }
}
