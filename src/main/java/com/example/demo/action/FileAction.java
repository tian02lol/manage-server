package com.example.demo.action;

import com.example.demo.entity.AjaxResult;
import com.example.demo.utils.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileAction {


    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(@RequestParam(value="file", required = false) MultipartFile file, HttpServletRequest req) throws IOException {
//        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
//        String format = sdf.format(new Date());
        /*String realPath = req.getServletContext().getRealPath("/upload") + format;*/
        File folder = UploadUtils.getImgDirFile();
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        //String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder.getAbsolutePath() + File.separator + oldName));
        String url = "/upload/imgs/" + oldName;
        Map data = new HashMap();
        data.put("url",url);
        return AjaxResult.success(data);
    }
}
