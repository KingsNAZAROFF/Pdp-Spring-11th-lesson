package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Attachment;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.AttachmentService;

@RestController
@RequestMapping(value = "/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result  upload(MultipartHttpServletRequest request){

        Result result = attachmentService.uploadFile(request);
        return result;
    }
    @GetMapping
    public Page<Attachment> getAllController(@RequestParam int page){

        return attachmentService.getALl(page);
    }
    @PutMapping("/{id}")
    public Result editAttachmentController(@PathVariable Integer id, MultipartHttpServletRequest request){
        Result result = attachmentService.editAttachment(id, request);
        return result;
    }
    @DeleteMapping("/{id}")
    public Result deleteAttachmentController(@PathVariable Integer id){
        Result result = attachmentService.deleteAttachment(id);
        return result;
    }

}
