package pers.dlx.selfchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pers.dlx.selfchat.config.OSSConfig;
import pers.dlx.selfchat.model.core.Response;
import pers.dlx.selfchat.utils.OSSUtil;
import pers.dlx.selfchat.utils.SnowflakeIdWorker;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class UploadController {

    @Autowired
    private OSSConfig ossConfig;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    Response uploadImg(@RequestParam("file") MultipartFile file,
                       HttpServletRequest request) throws IOException {
        String fileName = SnowflakeIdWorker.getNextId() + "_" + file.getOriginalFilename();
        String key = OSSUtil.uploadPic(file, fileName);
        if (key == null) {
            return null;
        }
        Response response = new Response();
        response.setContent("https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + fileName);
        return response;
    }
}
