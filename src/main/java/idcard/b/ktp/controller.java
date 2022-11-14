/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idcard.b.ktp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lenovo
 */
@Controller
public class controller {
    @RequestMapping("/getData")
    public String getData(@RequestParam("name") String text,
                            @RequestParam("born") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                            Model model, @RequestParam("image")MultipartFile file) throws IOException
    {
       SimpleDateFormat born=new SimpleDateFormat("EEEE,dd-MMMM-yyyy");
       String newTanggal=born.format(date);
       
       String blob=Base64.encodeBase64String(file.getBytes());
       String gambar="data:image/jpeg;base64,".concat(blob);
       
       model.addAttribute("nm",text);
       model.addAttribute("brn",newTanggal);
       model.addAttribute("img",gambar);
       
       return"view";
       
    }
    
}
