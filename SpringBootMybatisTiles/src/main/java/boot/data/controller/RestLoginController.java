package boot.data.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import boot.data.dto.MemberDto;
import boot.data.service.MemberServiece;

@RestController
public class RestLoginController {
   
   @Autowired
   MemberServiece service;
   
   @GetMapping("/member/login")
   public Map<String, String> loginprocess(@RequestParam String id, @RequestParam String pass,
         HttpSession session){
      
      Map<String, String> map = new HashMap<>();
      
      int result = service.loginPassCheck(id, pass);
      
      if(result==1) {
         session.setMaxInactiveInterval(60*60*8);
         session.setAttribute("myid", id);
         session.setAttribute("loginok", "ok");
         //session.setAttribute("saveok", cbsave);
         
         MemberDto mdto = service.getDataById(id);
         
         session.setAttribute("loginphoto", mdto.getPhoto());
         session.setAttribute("loginname", mdto.getName());
         
      }
      
      map.put("result", result==1?"success":"fail");
      return map;
   }
   
   @GetMapping("/member/logout")
   public void logout(HttpSession session) {
      session.removeAttribute("loginok");
      session.removeAttribute("myid");
   }

}