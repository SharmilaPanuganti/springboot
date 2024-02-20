package cgg.smartcontact.smartcontactmanager.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionManager {

  public void removeVerificationMessageFromSession() {
    try {
      HttpServletRequest request =
        (
          (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()
        ).getRequest();
      HttpSession session = request.getSession();
      session.removeAttribute("msg");
    } catch (RuntimeException ex) {
      ex.printStackTrace();
    }
  }
}
